package com.company;

public class Main {

    public static void main(String[] args) {
	    Parser parser = new Parser("C:\\Users\\Проги\\IdeaProjects\\Ламода поиск\\lamoda.html");
        System.out.println(parser.getInfo());
        //parser.infoWriteToFile();
    }
}
