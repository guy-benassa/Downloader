package org.example.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DownloaderFileWriter {

    public boolean write(String fileContent, String fileName) {
        BufferedWriter writer = null;
        boolean success = true;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(fileContent);
        } catch (IOException e) {
            System.out.println("could not write to file " + fileName);
            return false;
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("could not close file " + fileName);
                    success = false;
                }
            }

        }
        return success;
    }
}
