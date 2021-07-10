package com.company.commands;

import com.company.FileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class TouchCommand implements Command {
    @Override
    public void execute(Path path, String flag, FileManager fileManager) throws IOException{
        if (path.toFile().exists()) {
            Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
        }
        else {
            Files.createFile(path);
        }
    }
}
