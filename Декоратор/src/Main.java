package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Item item1 = new Item("Apple", 100, 5);
        Item item2 = new Item("Juice", 80, 3);
        ItemOutputStream productOutputStream = new ItemOutputStream(new FileOutputStream("just.txt"));
        ItemInputStream productInputStream = new ItemInputStream(new FileInputStream("just.txt"));
        productOutputStream.writeProduct(item1);
        productOutputStream.writeProduct(item2);
        Item product = productInputStream.readItem();
        Item product2 = productInputStream.readItem();
        System.out.println(product.getPrice());
        System.out.println(product.getName());
        System.out.println(product.getNumber());
        System.out.println(product2.getPrice());
        System.out.println(product2.getName());
        System.out.println(product2.getNumber());
    }
}
