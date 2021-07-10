package com.company;

import java.util.Objects;

public class Item {

    String name;
    double price;
    int number;

    public Item(String name, double price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return  Double.compare(item.price, price) == 0 &&
                Objects.equals(name, item.name) &&
                Integer.compare(item.number, number) == 0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }
}
