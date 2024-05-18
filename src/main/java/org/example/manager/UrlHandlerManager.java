package org.example.manager;

import org.example.download.UrlDownloader;
import org.example.file.DownloaderFileWriter;
import org.example.manager.data.DownLoaderLog;

import java.net.MalformedURLException;

public class UrlHandlerManager {

    public DownLoaderLog handle(String urlString, int timeLimitToReadUrlInSeconds, String pathToWrite) {
        DownLoaderLog log = DownLoaderLog.startLog();
        UrlDownloader d = new UrlDownloader();
        String urlContent = null;
        try {
            urlContent = d.getUrlString(urlString, 1000L * timeLimitToReadUrlInSeconds);

        } catch (MalformedURLException e) {
            return log.endLog("Malformed url " + urlString + " took time in millis ");
        }
        if(urlContent == null) {
            return log.endLog("could not read from url " + urlString + " took time in millis ");
        }

        DownloaderFileWriter fileWriter = new DownloaderFileWriter();
        String fileName = toFileName(urlString, pathToWrite);
        boolean succeed = fileWriter.write(urlContent, fileName);
        if(succeed) {
            return log.endLog("url " + urlString + " was written to file " + fileName +" took time in millis ");
        } else {
            return log.endLog("Could Not write url " + urlString + " to file " + fileName +" took time in millis ");
        }
    }

    private String toFileName(String s, String path) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++) {
            int codePoint = s.codePointAt(i);
            if(Character.isAlphabetic(codePoint) || Character.isDigit(codePoint)) {
                sb.append(s.charAt(i));
            }
        }

        if(!path.endsWith("/")) {
            path += "/";
        }

        return path + sb.toString();
    }

    public static void main(String[] args) {
        String s = "https://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code";
        String p = "C:\\DownloadTool";
        UrlHandlerManager m = null;
//        m = new UrlHandlerManager();
//        m.handle(s, 200, p);

        m = new UrlHandlerManager();
        s= "https://www.baeldung.com/java-write-to-file";
        m.handle(s, 200, p);

        m = new UrlHandlerManager();
        s= "https://mkyong.com/java/convert-java-objects-to-json-with-jackson/";
        m.handle(s, 200, p);

    }
}
