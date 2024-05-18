package org.example.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigFileReader {

    public String readFile(String filePath) {
        File file = new File(filePath);
        try {
            return new String(Files.readAllBytes(Paths.get(file.toURI())));
        } catch (IOException e) {
            System.out.println("Could Not Read File. exception : " + e.getMessage());
        }
        return null;
    }
}
