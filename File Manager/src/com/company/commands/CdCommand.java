package com.company.commands;

import com.company.FileManager;

import java.io.IOException;
import java.nio.file.Path;

public class CdCommand implements Command {

    @Override
    public void execute(Path path, String flag, FileManager fileManager) throws IOException {
        if (path.endsWith("..")) {
            fileManager.setCurrentDirectory(fileManager.getCurrentDirectory().getParent());
        }
        else {
            if (path.toFile().exists()) {
                fileManager.setCurrentDirectory(path);
            }
            else {
                throw new IOException();
            }
        }
    }
}
