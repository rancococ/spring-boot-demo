package com.catvgd.springbootdemo.common.logger.log4j2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;

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

    @Override
    public List<String> listLoggerInfo(String loggerNamePrefix) {
        List<String> loggerList = new ArrayList<String>();
        LoggerContext loggerContext = LoggerContext.getContext(false);
        Collection<Logger> currentLoggers = loggerContext.getLoggers();
        for (Logger logger : currentLoggers) {
            if (StringUtil.startsWithIgnoreCase(logger.getName(), loggerNamePrefix)) {
                loggerList.add("[" + logger.getName() + "] | [" + logger.getLevel() + "]");
            }
        }
        return loggerList;
    }

    @Override
    public String changeLoggerLevel(String loggerName, String loggerLevel) {
        LoggerContext loggerContext = LoggerContext.getContext(false);
        Logger logger = loggerContext.getLogger(loggerName);
        String result = "";
        if (logger != null) {
            logger.setLevel(Level.toLevel(loggerLevel));
            result = "logger [" + logger.getName() + "] | [" + logger.getLevel() + "]";
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
