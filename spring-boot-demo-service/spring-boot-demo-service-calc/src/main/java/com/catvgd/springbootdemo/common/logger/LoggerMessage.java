package com.catvgd.springbootdemo.common.logger;

import java.io.Serializable;

public class LoggerMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String body;
    private long timestamp;
    private String threadName;
    private String loggerName;
    private String level;

    public LoggerMessage(String body, long timestamp, String threadName, String loggerName, String level) {
        this.body = body;
        this.timestamp = timestamp;
        this.threadName = threadName;
        this.loggerName = loggerName;
        this.level = level;
    }

    public LoggerMessage() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "LoggerMessage [body=" + body + ", timestamp=" + timestamp + ", threadName=" + threadName + ", loggerName=" + loggerName + ", level="
                + level + "]";
    }

}
