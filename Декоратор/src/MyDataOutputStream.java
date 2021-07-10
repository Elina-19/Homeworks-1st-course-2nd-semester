package com.company;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyDataOutputStream {
    File file;

    public MyDataOutputStream(String path) {
        file = Paths.get(path).toFile();
    }

    public void writeInt(int num) {
        try(DataOutputStream dos =
                new DataOutputStream(new FileOutputStream(file))) {
            dos.write(num>>24);
            dos.write(num>>16);
            dos.write(num>>8);
            dos.write(num);
            dos.flush();
            dos.close();
        }
        catch (IOException e) {
            System.out.println("There is no such file");
        }
    }

    public void writeChar(char ch) {
        try(DataOutputStream dos =
                new DataOutputStream(new FileOutputStream(file))) {
            dos.write(ch>>8);
            dos.write(ch);
            dos.flush();
            dos.close();
        }
        catch (IOException e) {
            System.out.println("There is no such file");
        }
    }
}
