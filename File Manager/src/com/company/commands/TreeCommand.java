package com.company.commands;

import com.company.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TreeCommand implements Command {

    private final int SPACE = 2;
    private int include = 0;

    @Override
    public void execute(Path path, String flag, FileManager fileManager) throws IOException{
        if (Files.exists(path)) {
            File file = path.toFile();
            if (Files.isDirectory(path)) {
                File[] array = file.listFiles();
                for (File a : array) {
                    if (!a.isHidden()) {
                        for (int j = 0; j < include*(SPACE); j++) {
                            System.out.print("\t");
                        }
                        if (include != 0) {
                            System.out.print("----");
                        }
                        System.out.println(a.getName());
                    }
                    if (a.isDirectory()) {
                        int k = include;
                        include++;
                        execute(a.toPath(), flag, fileManager);
                        include = k;
                    }
                }
            }
        }
        else {
            throw new IOException();
        }
    }
}
