package com.company;

public class AlgorithmKaratcuba {

    public static long algorithmKaratcuba(int a, int b) {
        boolean[] arrayA = toArray(a);
        boolean[] arrayB = toArray(b);
        boolean[] result = multi(arrayA, arrayB);
        return makeNumber(result);
    }

    public static boolean[] toArray(int a) { //переводит число в массив boolean
        int size;
        if (a != 0) {
            size = (int) Math.floor(Math.log(a) / Math.log(2))+1;
        }
        else {
            size = 1;
        }
        boolean[] array = new boolean[size];
        while (a >= 1) {
            if (a % 2 == 1) {
                array[--size] = true;
            }
            else {
                array[--size] = false;
            }
            a = a/2;
        }
        return array;
    }

    public static boolean[] multi(boolean[] a, boolean[] b) {
        a = decrease(a);
        b = decrease(b);
        int size = a.length;
        if (a.length != b.length) { //приводит к одинаковому размеру
            if (a.length > b.length) {
                size = a.length;
                boolean[] newb = new boolean[size];
                System.arraycopy(b, 0, newb, a.length - b.length, b.length);
                b = newb;
            } else {
                size = b.length;
                boolean[] newa = new boolean[size];
                System.arraycopy(a, 0, newa, b.length - a.length, a.length);
                a = newa;
            }
        }
        if (size == 1) { //условие выхода из рекурсии, оба массива длины 1
            boolean[] c = new boolean[1];
            if (a[0] == true && b[0] == true) {
                c[0] = true;
            }
            return c;
        }
        if (size % 2 == 1) {
            size++;
            boolean[] newa = new boolean[size];
            boolean[] newb = new boolean[size];
            System.arraycopy(a, 0, newa, 1, a.length);
            System.arraycopy(b, 0, newb, 1, b.length);
            a = newa;
            b = newb;
        }
        boolean[] a1 = new boolean[size/2];
        boolean[] a2 = new boolean[size/2];
        boolean[] b1 = new boolean[size/2];
        boolean[] b2 = new boolean[size/2];
        System.arraycopy(a, 0, a1, 0, a1.length);
        System.arraycopy(a, a1.length, a2, 0, a2.length);
        System.arraycopy(b, 0, b1, 0, b1.length);
        System.arraycopy(b, b1.length, b2, 0, b2.length);
        boolean[] first = multi(a1, b1);
        boolean[] second = multi(a2, b2);
        boolean[] secondSummand = subtraction(multi(sum(a1, a2), sum(b1, b2)), sum(first, second));
        return  sum(sum(second, increaseArrayEnd(secondSummand, secondSummand.length + size/2)),
                increaseArrayEnd(first, first.length + size));
    }

    public static long makeNumber(boolean[] a) { //возвращает число в 10-ой системе счисления
        long sum = 0;
        int raise = a.length-1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == true) {
                sum += (long)Math.pow(2, raise);
            }
            raise--;
        }
        return sum;
    }

    public static boolean[] subtraction(boolean[] a1, boolean[] b1) { //вычитание
        boolean[] a;
        boolean[] b;
        if (b1.length > a1.length) {
            a = new boolean[b1.length];
            System.arraycopy(a1, 0, a, b1.length-a1.length, a1.length);
            b = b1;
        }
        else {
            b = new boolean[a1.length];
            System.arraycopy(b1, 0, b, a1.length-b1.length, b1.length);
            a = a1;
        }
        boolean[] res = new boolean[a.length];
        for (int i = a.length-1; i >= 0; i--) {
            if (a[i] == false && b[i] == true) {
                int k = i-1;
                while (a[k] == false) {
                    a[k--] = true;
                }
                a[k] = false;
                res[i] = true;
            }
            if (a[i] == true && b[i] == false) {
                res[i] = true;
            }
        }
        return res;
    }

    public static boolean[] decrease(boolean[] a) { //убирает 0(false) впереди
        boolean flag = true;
        int k = 0;
        if (a[0] == false) {
            for (int i = 0; i < a.length && flag; i++) {
                if (a[i] == true || i == a.length - 1) {
                    k = i;
                    flag = false;
                }
            }
            if (k != 0) {
                boolean[] newa = new boolean[a.length - k];
                System.arraycopy(a, k, newa, 0, newa.length);
                a = newa;
            }
        }
        return a;
    }

    public static boolean[] increaseArrayEnd(boolean[] a, int i) { //добавление нужного количества false в конце
        boolean[] newArray = new boolean[i];
        System.arraycopy(a, 0, newArray, 0, a.length);
        return newArray;
    }

    public static boolean[] sum(boolean[] a, boolean[] b) { //сумма чисел
        if (a.length != b.length) {
            if (a.length > b.length) {
                b = increaseArrayStart(b, a.length);
            } else {
                a = increaseArrayStart(a, b.length);
            }
        }
        boolean[] res = new boolean[a.length+1];
        for (int i = a.length-1; i >= 0; i--) { //тк res на 1 больше, то его индекс на 1 больше
            if (a[i] != b[i]) {
                if (res[i+1] == true) {
                    res[i+1] = false;
                    res[i] = true;
                }
                else {
                    res[i+1] = true;
                }
            }
            if (a[i] == b[i] && a[i] == true) { //если они равны, то единица идет дальше
                if (res[i+1] == true) {
                    res[i+1] = true;
                }
                else {
                    res[i+1] = false;
                }
                res[i] = true;
            }
        }
        return res;
    }

    public static boolean[] increaseArrayStart(boolean[] a, int size) { //повышает размер массива до нужного, добавляя вперед false
        boolean[] res = new boolean[size];
        System.arraycopy(a, 0, res, size-a.length, a.length);
        return res;
    }
}
