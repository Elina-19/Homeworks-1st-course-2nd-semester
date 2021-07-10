package weather;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class RequestHandler {

    private final static String townURL = "https://www.metaweather.com/api/location/search/?query=";
    private final static String idURL = "https://www.metaweather.com/api/location/";


    public static ArrayList<Town> getTowns(String town) throws MalformedURLException, IOException {

        town = getCorrectName(town);

        URLConnection conn = new URL(townURL + town).openConnection();
        conn.connect();

        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = r.readLine()) != null) {
            sb.append(line);
        }

        Gson gson = new Gson();
        Type townListType = new TypeToken<ArrayList<Town>>(){}.getType();
        ArrayList<Town> towns = gson.fromJson(sb.toString(), townListType);
        if (towns.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return towns;
    }


    private static String getCorrectName(String town) {
        return town.trim().replace(" ", "%20");
    }


    public static Weather getTodayWeather(String id) throws IOException {

        URLConnection conn = new URL(idURL + id).openConnection();
        conn.connect();

        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        Gson gson = new Gson();
        JsonObject data = gson.fromJson(r, JsonObject.class);
        JsonArray weatherData = data.getAsJsonArray("consolidated_weather");
        Weather todayWeather = gson.fromJson(weatherData.get(0).toString(), Weather.class);
        return todayWeather;
    }


    public static ArrayList<Weather> getWeatherList(String id) throws IOException {

        URLConnection conn = new URL(idURL + id).openConnection();
        conn.connect();

        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        Gson gson = new Gson();
        JsonObject data = gson.fromJson(r, JsonObject.class);
        JsonArray weatherData = data.getAsJsonArray("consolidated_weather");

        ArrayList<Weather> listOfDays = new ArrayList<>();
        for (JsonElement weatherItem : weatherData) {
            Weather weather = gson.fromJson(weatherItem.toString(), Weather.class);
            listOfDays.add(weather);
        }
        return listOfDays;
    }
}
