package com.catvgd.springbootdemo.common.logger;

import java.io.Serializable;

public class LoggerMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private long timestamp;
    private String threadName;
    private String loggerName;
    private String level;
    private String originalMessage;
    private String formattedMessage;

    public LoggerMessage(long timestamp, String threadName, String loggerName, String level, String originalMessage, String formattedMessage) {
        this.timestamp = timestamp;
        this.threadName = threadName;
        this.loggerName = loggerName;
        this.level = level;
        this.originalMessage = originalMessage;
        this.formattedMessage = formattedMessage;
    }

    public LoggerMessage() {
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

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    public String getFormattedMessage() {
        return formattedMessage;
    }

    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

    @Override
    public String toString() {
        return "LoggerMessage [timestamp=" + timestamp + ", threadName=" + threadName + ", loggerName=" + loggerName + ", level=" + level
                + ", originalMessage=" + originalMessage + ", formattedMessage=" + formattedMessage + "]";
    }

}
