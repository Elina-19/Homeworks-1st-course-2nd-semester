package com.company.commands;

import com.company.FileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MkdirCommand implements Command {

    @Override
    public void execute(Path path, String flag, FileManager fileManager) throws IOException{
        if (flag == null) {
            flag = "null";
        }
        switch (flag){
            case "-p":
                executeP(path, fileManager);
                break;
            case "null":
                executeNull(path);
                break;
            default:
                System.out.println("There is no such flag");
                break;
        }
    }

    public void executeP(Path path, FileManager fileManager) throws IOException{
        Files.createDirectories(path);
    }

    public void executeNull(Path path) throws IOException{
        if (path.getParent().toFile().exists()) {
            Files.createDirectory(path);
        }
        else {
            System.out.println("Can not find parent directory");
        }
    }
}
