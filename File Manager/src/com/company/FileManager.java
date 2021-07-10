package com.company;

import com.company.commands.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

    private static Path currentDirectory;
    private Pattern regex;
    private Scanner scan;
    private Map<String, Command> commands;
    private int numberOfGroups;
    private int pathGroup;
    private int flagGroup;

    public FileManager() {
        init();
        start();
    }

    private void init() {
        scan = new Scanner(System.in);
        regex = Pattern.compile("(?i)(?:(ls)|(cd)|(touch)|(rm)|(tree)|(mkdir)|(help)|(exit))(?:\\s+(-[^ ]{1,2}))?(?:\\s+(.+))?");
        numberOfGroups = 10;
        pathGroup = 10;
        flagGroup = 9;
        try {
            String path = new File(".").getCanonicalPath();
            setCurrentDirectory(Paths.get(path));
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        commands = new HashMap<>();
        commands.put("ls", new LsCommand());
        commands.put("cd", new CdCommand());
        commands.put("touch", new TouchCommand());
        commands.put("mkdir", new MkdirCommand());
        commands.put("tree", new TreeCommand());
        commands.put("rm", new RemoveCommand());
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
    }

    private void start() {
        Matcher matcher;
        while (true) {
            System.out.print(currentDirectory + " ");
            String command = scan.nextLine();
            matcher = regex.matcher(command);
            boolean flag = true;
            if (matcher.find()) {
                for (int i = 1; i < numberOfGroups && flag; i++) {
                    if (matcher.group(i) != null) {
                        Path path = getPath(matcher.group(pathGroup));
                        try {
                            commands.get(matcher.group(i)).execute(path, matcher.group(flagGroup), this );
                        }
                        catch (IOException e) {
                            System.out.println("Can not find path because it doesn't exist");
                        }
                        flag = false;
                    }
                }
            }
            else {
                System.out.println("There is no such command. Enter \"help\" to see possible commands");
            }
        }
    }

    private Path getPath(String path) {
        if (path == null) {
            return currentDirectory;
        }
        if (Paths.get(path).isAbsolute()) {
            return Paths.get(path);
        }
        else {
            return currentDirectory.resolve(path);
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(Path path) {
        currentDirectory = path;
    }

    public static void main(String[] args) {
        new FileManager();
    }
}
