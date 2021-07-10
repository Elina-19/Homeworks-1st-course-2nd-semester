package com.company;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        int n = 0;
        if ((n = o1.getName().compareTo(o2.getName())) != 0) {
            return n;
        }
        else {
            return o1.getPrice()- o2.getPrice();
        }
    }
}
