package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ItemOutputStream extends OutputStream {

    private OutputStream os;

    public ItemOutputStream(OutputStream os) {
        super();
        this.os = os;
    }

    public void writeProduct(Item item) throws IOException{

        write((item.getName() + " ").getBytes(StandardCharsets.UTF_8));
        write((item.getPrice() + " ").getBytes(StandardCharsets.UTF_8));
        write((item.getNumber() + "\n").getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void write(int b) throws IOException {
        os.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        os.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        os.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        os.flush();
    }

    @Override
    public void close() throws IOException {
        os.close();
    }
}
