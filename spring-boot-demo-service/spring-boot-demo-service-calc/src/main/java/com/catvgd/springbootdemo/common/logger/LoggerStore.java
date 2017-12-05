package com.catvgd.springbootdemo.common.logger;

import java.util.concurrent.atomic.AtomicLong;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class LoggerStore extends NotificationBroadcasterSupport implements LoggerStoreMBean {

    private volatile static LoggerStore instance;
    private AtomicLong seq = new AtomicLong(1);

    private LoggerStore() {
    }

    public static LoggerStore getInstance() {
        if (instance == null) {
            synchronized (LoggerStore.class) {
                if (instance == null) {
                    instance = new LoggerStore();
                }
            }
        }
        return instance;
    }

    public void pushLogger(String message) {
        Notification notification = new Notification("LoggerStore", this, seq.getAndIncrement(), System.currentTimeMillis(), message);
        notification.setUserData("user");
        this.sendNotification(notification);
    }

}
