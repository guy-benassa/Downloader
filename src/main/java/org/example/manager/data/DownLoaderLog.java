package org.example.manager.data;

public class DownLoaderLog {
    private Long timeInMillis;
    private String message;

    public DownLoaderLog() { }

    public Long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(Long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static DownLoaderLog startLog() {
        DownLoaderLog log = new DownLoaderLog();
        log.timeInMillis = System.currentTimeMillis();
        return log;
    }

    public DownLoaderLog endLog(String message) {
        this.timeInMillis = System.currentTimeMillis() - this.timeInMillis;
        this.message = message + this.timeInMillis;
        return this;
    }
}
