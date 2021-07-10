package com.company.commands;

import com.company.FileManager;

import java.io.IOException;
import java.nio.file.Path;

public class ExitCommand implements Command {

    @Override
    public void execute(Path path, String flag, FileManager fileManager) throws IOException {
        System.exit(0);
    }
}
