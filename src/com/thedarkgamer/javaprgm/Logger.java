package com.thedarkgamer.javaprgm;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static File init(boolean disableLog) {
        SimpleDateFormat fileFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
        Date date = new Date();

        Path filePath = Path.of("C:\\Program Files\\TDGApps\\Java\\Java Prgm\\Logs");
        String logFile = filePath + "\\" + fileFormat.format(date) + ".txt";
        File log = new File(logFile);

        log.setWritable(true);
        log.setReadable(true);

        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath);
            } catch (IOException e){
                System.out.println("Files could not be made.");
            }
        }

        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException e) {
                System.out.println("Log could not be made.");
                e.printStackTrace();
                return null;
            }
        }
        try {
            FileWriter fw = new FileWriter(log);
            fw.write("TheDarkGamer Apps - Java Prgm v0.01\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to write to log " + log);
            e.printStackTrace();
        }

        try {
            FileSystem fileSystem = log.toPath().getFileSystem();
            UserPrincipalLookupService service = fileSystem.getUserPrincipalLookupService();
            UserPrincipal user = service.lookupPrincipalByName(System.getProperty("user.name"));
            Files.setOwner(log.toPath(), user);
            Files.setOwner(filePath, user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }

    public static void Log(String msg, File log, String TYPE) {

        switch (TYPE) {
            case "MAIN": msg = "[MAIN] " + msg + "\n";
                break;
            case "INFO": msg = "[INFO] " + msg + "\n";
                break;
            case "DEBUG": msg = "[DEBUG] " + msg + "\n";
                break;
            case "WARN": msg = "[WARNING] " + msg + "\n";
                break;
            case "ERROR": msg = "[ERROR] " + msg + "\n";
                break;
            default: msg = "[NONE] " + msg + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(msg);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to write to log, process terminating.");
            Main.Crash(-1);
        }
    }
}
