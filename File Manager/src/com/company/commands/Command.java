package com.company.commands;

import com.company.FileManager;

import java.io.IOException;
import java.nio.file.Path;

public interface Command {

    void execute(Path path, String flag, FileManager fileManager) throws IOException;
}
