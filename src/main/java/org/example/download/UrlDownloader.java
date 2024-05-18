package org.example.download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class UrlDownloader {

    public String getUrlString(String urlString, long timeLimit) throws MalformedURLException {
        long startTimeMillis = System.currentTimeMillis();
        URI uri = URI.create(urlString);

        URL website = uri.toURL();
        URLConnection connection = null;
        BufferedReader in = null;
        try {
            connection = website.openConnection();
            in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
        } catch (IOException e) {
            System.out.println("could not open site " + urlString);
            return null;
        }

        StringBuilder response = new StringBuilder();
        String inputLine;

        try {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                if(System.currentTimeMillis() - startTimeMillis > timeLimit) {
                    System.out.println("time out reading url " + urlString);
                    return null;
                }
            }
        } catch (IOException e) {
            System.out.println("could not read from url " + urlString);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("could not close connection from url " + urlString);
            }
        }



        return response.toString();
    }

    public static void main(String[] args) {
        UrlDownloader d = new UrlDownloader();
        try {
            String s  = d.getUrlString("https://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code", 2000);
            System.out.println(s);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
