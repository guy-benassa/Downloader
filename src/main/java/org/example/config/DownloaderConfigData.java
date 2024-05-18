package org.example.config;

import java.util.Map;

public class DownloaderConfigData {

    private Map<String,Integer> urlsToDownload;
    private String outputDirectory;
    private int maxNumberOfConcurrentDownloads;

    public Map<String, Integer> getUrlsToDownload() {
        return urlsToDownload;
    }

    public void setUrlsToDownload(Map<String, Integer> urlsToDownload) {
        this.urlsToDownload = urlsToDownload;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public int getMaxNumberOfConcurrentDownloads() {
        return maxNumberOfConcurrentDownloads;
    }

    public void setMaxNumberOfConcurrentDownloads(int maxNumberOfConcurrentDownloads) {
        this.maxNumberOfConcurrentDownloads = maxNumberOfConcurrentDownloads;
    }

}
