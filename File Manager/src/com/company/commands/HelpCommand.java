package com.company.commands;

import com.company.FileManager;

import java.io.IOException;
import java.nio.file.Path;

public class HelpCommand implements Command {
    @Override
    public void execute(Path path, String flag, FileManager fileManager) throws IOException {
        System.out.println("List of available commands:");
        for (String command: fileManager.getCommands().keySet()) {
            System.out.println(command);
        }
    }
}
