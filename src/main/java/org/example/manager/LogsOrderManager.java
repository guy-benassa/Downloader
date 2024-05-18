package org.example.manager;

import org.example.manager.data.DownLoaderLog;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LogsOrderManager {

    public void logActivity(List<DownLoaderLog> logs) {
        if(logs == null || logs.isEmpty()) {
            //
            return;
        }

        logs.sort(Comparator.comparing(DownLoaderLog::getTimeInMillis));
        logs.forEach(l -> System.out.println(l.getMessage()));
    }
}
