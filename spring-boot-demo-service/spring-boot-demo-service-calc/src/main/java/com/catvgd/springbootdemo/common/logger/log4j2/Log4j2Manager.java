package com.catvgd.springbootdemo.common.logger.log4j2;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.catvgd.springbootdemo.common.util.StringUtil;

public class Log4j2Manager extends NotificationBroadcasterSupport implements Log4j2ManagerMBean {

    private volatile static Log4j2Manager instance;
    private AtomicLong seq = new AtomicLong(1);

    private Log4j2Manager() {
    }

    public static Log4j2Manager getInstance() {
        if (instance == null) {
            synchronized (Log4j2Manager.class) {
                if (instance == null) {
                    instance = new Log4j2Manager();
                }
            }
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> listLoggerInfo(String loggerNamePrefix) {
        Enumeration<Logger> currentLoggers = LogManager.getCurrentLoggers();
        List<String> loggerList = new ArrayList<String>();
        while (currentLoggers.hasMoreElements()) {
            Logger logger = currentLoggers.nextElement();
            if (StringUtil.startsWithIgnoreCase(logger.getName(), loggerNamePrefix)) {
                loggerList.add(logger.getName() + " : " + logger.getLevel());
            }
        }
        return loggerList;
    }

    @Override
    public String changeLoggerLevel(String loggerName, String loggerLevel) {
        Logger logger = LogManager.exists(loggerName);
        String result = "";
        if (logger != null) {
            logger.setLevel(Level.toLevel(loggerLevel));
            result = logger.getName() + " | " + logger.getLevel();
        } else {
            result = "logger [" + loggerName + "] not exist.";
        }
        return result;

    }

    public void pushLogger(String message) {
        Notification notification = new Notification("LoggerMonitor", this, seq.getAndIncrement(), System.currentTimeMillis(), message);
        notification.setUserData("user");
        this.sendNotification(notification);
    }

}
