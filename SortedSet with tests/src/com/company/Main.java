package com.company;

import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Comparator<Product> comparator1 = (o1, o2) -> (o1.getName().compareTo(o2.getName()) == 0 ? o1.getName().compareTo(o2.getName()): o1.getPrice()-o2.getPrice());

        ProductComparator comparator = new ProductComparator();
        Product product = new Product("chicken", 200);
        Product product1 = new Product("chicken", 200);
        ArrayList<Product> t = new ArrayList<>();
        t.add(product);
        t.add(product1);
        ArraySet<Product> s = new ArraySet<>(comparator);
        s.addAll(t);
        System.out.println(s.size());

        ArraySet set1 = new ArraySet<Product>(comparator);
        set1.addAll(Arrays.asList(new Product("milk", 5), new Product("milk", 3), new Product("apple", 10)));
        ArraySet myset = new ArraySet<Product>(comparator);
        myset.addAll(Arrays.asList(new Product("milk", 5), new Product("milk", 3)));
        set1.retainAll(myset);
    }
}
