package com.company;

import java.io.IOException;
import java.io.InputStream;

public class ItemInputStream extends InputStream {

    private InputStream is;

    public ItemInputStream(InputStream is) {
        super();
        this.is = is;
    }

    public Item readItem() throws IOException {

        String name = "";
        double price = -1;
        int number = -1;
        int i = 0;
        boolean flag = true;
        while (flag && (i = is.read()) != -1) {
            char ch = (char)(i);
            if (ch == ' ') {
                flag = false;
            }
            else {
                name += ch;
            }
        }
        if (i == -1) {
            return null;
        }

        flag = true;

        String strPrice = "";
        while (flag && (i = is.read()) != -1) {
            char ch = (char)(i);
            if (ch == ' ') {
                flag = false;
            }
            else {
                strPrice += ch;
            }
        }
        price = Double.valueOf(strPrice);

        flag = true;

        String strNumber = "";
        while (flag && (i = is.read()) != -1) {
            char ch = (char)(i);
            if (ch == '\n') {
                flag = false;
            }
            else {
                strNumber += ch;
            }
        }
        number = Integer.valueOf(strNumber);

        return new Item(name, price, number);
    }

    @Override
    public int read() throws IOException {
        return is.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return is.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return is.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return is.skip(n);
    }

    @Override
    public int available() throws IOException {
        return is.available();
    }

    @Override
    public void close() throws IOException {
        is.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        is.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        is.reset();
    }

    @Override
    public boolean markSupported() {
        return is.markSupported();
    }
}
