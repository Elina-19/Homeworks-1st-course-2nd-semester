package com.company;

public class Main {

    public static void main(String[] args) {
        long start;
        long end;
        start = System.nanoTime();
        long a = AlgorithmKaratcuba.algorithmKaratcuba(7434, 256);
        System.out.println(a);
        end = System.nanoTime();
        System.out.println(end-start);
        start = System.nanoTime();
        System.out.println((long)7434*256);
        end = System.nanoTime();
        System.out.println(end-start);
    }
}
