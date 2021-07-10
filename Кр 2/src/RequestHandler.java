package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class RequestHandler {

    private static String url = "https://www.amiiboapi.com/api/amiibo/?name=";

    public static ArrayList<String> getImages(String name) throws IOException {

        name = getCorrectName(name);

        URLConnection conn = new URL(url + name).openConnection();
        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(r, JsonObject.class);

        if (jsonObject.has("error")){
            throw new IllegalArgumentException();
        }
        JsonArray array = jsonObject.getAsJsonArray("amiibo");

        ArrayList<String> urlArray = new ArrayList<>();

        for (JsonElement elem: array) {
            urlArray.add(elem.getAsJsonObject().get("image").getAsString());
        }

        return urlArray;
    }

    private static String getCorrectName(String name) {
        return name.replace(" ", "%20");
    }

    public static ArrayList<String> getSeries(String name) throws IOException {

        name = getCorrectName(name);

        URLConnection conn = new URL(url + name).openConnection();
        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(r, JsonObject.class);

        if (jsonObject.has("error")){
            throw new IllegalArgumentException();
        }
        JsonArray array = jsonObject.getAsJsonArray("amiibo");

        ArrayList<String> series = new ArrayList<>();

        for (JsonElement elem: array) {
            JsonObject obj = elem.getAsJsonObject();
            series.add(obj.get("amiiboSeries").getAsString() + "-" + obj.get("character"));
        }

        return series;
    }

    public static ArrayList<String> getReleases(String name) throws IOException{

        getCorrectName(name);

        URLConnection conn = new URL(url + name).openConnection();
        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(r, JsonObject.class);

        if (jsonObject.has("error")){
            throw new IllegalArgumentException();
        }
        JsonArray array = jsonObject.getAsJsonArray("amiibo");

        ArrayList<String> releases = new ArrayList<>();

        for (JsonElement elem: array) {
            JsonObject obj = elem.getAsJsonObject();
            JsonObject obj2 = obj.get("release").getAsJsonObject();
                releases.add("au" + " = " + obj2.get("au").getAsString() + "\n" +
                        "eu" + " = " + obj2.get("eu").getAsString() + "\n" +
                        "jp" + " = " + obj2.get("jp").getAsString() + "\n" +
                        "na" + " = " + obj2.get("na").getAsString());
        }

        return releases;
    }

}
