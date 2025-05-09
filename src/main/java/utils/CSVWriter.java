package main.java.utils;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public static void writeLine(String filePath, String line) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(line).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
