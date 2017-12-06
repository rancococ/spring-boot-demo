package com.catvgd.springbootdemo.common.logger;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoggerNotificationListener implements NotificationListener, NotificationFilter, InitializingBean {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public boolean isNotificationEnabled(Notification notification) {
        // return AttributeChangeNotification.class.isAssignableFrom(notification.getClass());
        return true;
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        // System.out.println(notification);
        if (messagingTemplate != null) {
            messagingTemplate.convertAndSend("/topic/pullLogger", notification.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName mbean = new ObjectName("org.apache.logging.log4j2:name=Log4j2ManagerMBean");
        mbeanServer.addNotificationListener(mbean, this, this, null);
    }

}
