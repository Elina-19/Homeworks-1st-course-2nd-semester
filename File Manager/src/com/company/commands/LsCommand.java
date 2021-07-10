package com.company.commands;

import com.company.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LsCommand implements Command {

    @Override
    public void execute(Path path, String flag, FileManager fileManager) throws IOException {
        if (!path.toFile().exists()) {
            throw new IOException();
        }
        if (flag == null) {
            flag = "null";
        }
        switch (flag){
            case "-l":
                executeL(path, fileManager);
                break;
            case "null":
                executeNull(path);
                break;
            default:
                System.out.println("There is no such flag");
                break;
        }
    }

    private void executeL(Path path, FileManager fileManager) throws IOException{
        for (File file: path.toFile().listFiles()) {
            System.out.print(Files.getOwner(file.toPath()));
            System.out.print(" " + Files.size(file.toPath()) + " ");
            System.out.println(Files.getLastModifiedTime(file.toPath()));
        }
    }

    private void executeNull(Path path) {
        for (String str: path.toFile().list()) {
            System.out.println(str);
        }
    }
}
