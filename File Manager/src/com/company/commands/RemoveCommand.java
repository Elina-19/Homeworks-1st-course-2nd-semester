package com.company.commands;

import com.company.FileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RemoveCommand implements Command {

    @Override
    public void execute(Path path, String flag, FileManager fileManager) throws IOException {
        if (path.toFile().exists()) {
            Files.delete(path);
        }
        else {
            throw new IOException();
        }
    }
}
