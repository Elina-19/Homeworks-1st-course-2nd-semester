package com.company;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        ArrayList<String> toys = null;
        ArrayList<String> series = null;
        ArrayList<String> releases = null;

        try {
            toys = RequestHandler.getImages(str);
            series = RequestHandler.getSeries(str);
            releases = RequestHandler.getReleases(str);
            Thread.sleep(10);
        }
        catch (IOException e) {
            System.out.println("Problems with internet");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Uncorrect name");
            System.exit(1);
        }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        saveImages(toys);

        File file = new File("text.ini");
        try (FileWriter writer = new FileWriter(file, false)) {
            int i = 0;
            for (String st: series) {
                writer.write("[" + st + "]\n");
                writer.write(releases.get(i) + "\n");
                i++;
            }
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Output problem");
        }
    }

    private static void saveImages(ArrayList<String> images) {

        File file = new File("Images");
        try {
            int i = 0;
            for (String image: images) {
                BufferedInputStream in = new BufferedInputStream((new URL(image)).openStream());
                FileOutputStream fos = new FileOutputStream("Images" + String.valueOf(i++) + ".png");
                int n;
                while (-1 != (n = in.read())) {
                    fos.write(n);
                }
                fos.close();
                in.close();
            }
        }
        catch(IOException e) {
            System.out.println("Input problem");
        }
    }
}
