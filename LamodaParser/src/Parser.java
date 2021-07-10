package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private List<String> list;
    private String allStrings;
    private String regex = "products-list-item.+?data-price<\\/span>=\"<span class=\"html-attribute-value\">(\\d+)<.+?href=\"(.+?)\".+?alt(?:</span>=\"<span class=\"html-attribute-value\">|=\")(.+?), цвет.+?Подробнее";


    public Parser(String path) {
        try {
            list = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
        concatStrings();
    }

    public Parser(List<String> list) {
        this.list = list;
        concatStrings();
    }

    public String getInfo() {
        StringBuilder strBuild = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(allStrings);
        while (matcher.find()) {
            strBuild.append(matcher.group(3) + ", ");
            strBuild.append("цена: " + matcher.group(1) + " ");
            strBuild.append(matcher.group(2) + "\n");
        }
        return strBuild.toString();
    }


    private void concatStrings() {
        StringBuilder strBuild = new StringBuilder();
        for (String str: list) {
            strBuild.append(str);
        }
        allStrings = strBuild.toString();
    }

    public void infoWriteToFile() {
        FileWriter record;
        try {
            record = new FileWriter("C:\\Users\\Проги\\IdeaProjects\\Ламода поиск\\infolamoda.txt");
            record.write(getInfo());
            record.close();
        }
        catch(IOException e) {
            System.out.println("There is no such file");
        }
    }
}
