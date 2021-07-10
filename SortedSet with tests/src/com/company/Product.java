package com.company;

import java.util.Comparator;
import java.util.Objects;

public class Product implements Comparable<Product>{

    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return  price == product.price &&
                name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Product o2) {
        Comparator<Product> comparator = Comparator.comparing(product -> product.getName());
        comparator.thenComparing(product -> product.getPrice());
        return comparator.compare(this, o2);
    }
}
