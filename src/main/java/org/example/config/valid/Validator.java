package org.example.config.valid;

import org.example.config.DownloaderConfigData;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

public class Validator {

    public boolean isValid(DownloaderConfigData data) {
        if(stringIsEmpty(data.getOutputDirectory())) {
            System.out.println("outputDirectory is not configured");
            return false;
        }

        for(Map.Entry<String, Integer> entry : data.getUrlsToDownload().entrySet()) {
            if(stringIsEmpty(entry.getKey())) {
                System.out.println("urlsToDownload should not contain empty URLs");
                return false;
            }
            if(!stringIsUrl(entry.getKey())) {
                System.out.println("urlsToDownload should contain only URLs");
                return false;
            }
        }

        return true;
    }

    public boolean stringIsEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public boolean stringIsUrl(String s) {
        try {
            URI uri = URI.create(s);
            uri.toURL();
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
