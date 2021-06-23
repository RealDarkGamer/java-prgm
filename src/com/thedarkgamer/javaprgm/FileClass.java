package com.thedarkgamer.javaprgm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class FileClass {
    public static String read(File readFrom) {
        try {
            FileReader fr = new FileReader(readFrom);

            BufferedReader br = new BufferedReader(fr);

            return br.lines().collect(Collectors.joining());
        } catch (IOException e) {
            System.out.println("Could not read file");
            return null;
        }
    }

    public static String readline(File readFrom, int line) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(readFrom));

            int curLine = 0;

            while (curLine != (line - 1)) {
                curLine++;
                br.readLine();
            }

            return br.readLine();
        } catch (IOException e) {
            System.out.println("Could not read file");
            return null;
        }
    }
}
