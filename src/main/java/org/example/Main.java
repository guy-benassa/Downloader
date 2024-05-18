package org.example;

import org.example.config.DownloaderConfigData;
import org.example.config.DownloaderConfigReader;
import org.example.config.valid.Validator;
import org.example.manager.LogsOrderManager;
import org.example.manager.UrlHandlerManager;
import org.example.manager.data.DownLoaderLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;


public class Main {
    public static void main(String[] args) {
        long startMillis = System.currentTimeMillis();
        if (args == null || args.length < 1 || args[0] == null || args[0].isEmpty()) {
            System.out.println("Please Specify name and path of the config file In the last argument");
            return;
        }

        DownloaderConfigReader reader = new DownloaderConfigReader(args[args.length - 1]);
        DownloaderConfigData config = reader.readConfig();

        if(config == null) {
            System.out.println("Could Not config downloader");
            return;
        }

        Validator v = new Validator();
        if(!v.isValid(config)) {
            System.out.println("config is invalid");
            return;
        }


        try (ExecutorService executorService = Executors.newFixedThreadPool(config.getMaxNumberOfConcurrentDownloads())) {
            List<Callable<DownLoaderLog>> tasks = new ArrayList<>();
            for (final Map.Entry<String,Integer> entry: config.getUrlsToDownload().entrySet()) {
                Callable<DownLoaderLog> c = new Callable<>() {
                    @Override
                    public DownLoaderLog call() throws Exception {
                        UrlHandlerManager manager = new UrlHandlerManager();
                        return manager.handle(entry.getKey(), entry.getValue(), config.getOutputDirectory());
                    }
                };
                tasks.add(c);
            }

            List<DownLoaderLog> logs = null;
            try {
                List<Future<DownLoaderLog>> futures = executorService.invokeAll(tasks);
                logs = futures.stream().map(f -> {
                    long start = System.currentTimeMillis();
                    try {
                        return f.get();
                    } catch (Exception e) {
                        DownLoaderLog log = new DownLoaderLog();
                        log.setTimeInMillis(System.currentTimeMillis() - start);
                        log.setMessage("Could not succeed due to exception " + e.getMessage());
                        return log;
                    }
                }).collect(toList());
            } catch (InterruptedException e) {
                System.out.println("InterruptedException " + e.getMessage());
            }
            executorService.shutdown();

            LogsOrderManager orderManager = new LogsOrderManager();
            orderManager.logActivity(logs);
        }

        System.out.println("Process end in total of millis " + (System.currentTimeMillis() - startMillis));


    }
}