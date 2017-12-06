package com.catvgd.springbootdemo.common.logger;

import java.io.Serializable;

public class LoggerMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private long timestamp;
    private String threadName;
    private String loggerName;
    private String loggerLevel;
    private String originalMessage;
    private String formattedMessage;

    public LoggerMessage(long timestamp, String threadName, String loggerName, String loggerLevel, String originalMessage, String formattedMessage) {
        this.timestamp = timestamp;
        this.threadName = threadName;
        this.loggerName = loggerName;
        this.loggerLevel = loggerLevel;
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

    public String getLoggerLevel() {
        return loggerLevel;
    }

    public void setLoggerLevel(String loggerLevel) {
        this.loggerLevel = loggerLevel;
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
        return "LoggerMessage [timestamp=" + timestamp + ", threadName=" + threadName + ", loggerName=" + loggerName + ", loggerLevel=" + loggerLevel
                + ", originalMessage=" + originalMessage + ", formattedMessage=" + formattedMessage + "]";
    }

}
