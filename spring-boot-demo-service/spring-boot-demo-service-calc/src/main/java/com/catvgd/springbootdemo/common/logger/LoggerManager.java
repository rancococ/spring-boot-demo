package com.catvgd.springbootdemo.common.logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoggerManager implements InitializingBean {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private final LoggerQueue loggerQueue = LoggerQueue.getInstance();

    @Override
    public void afterPropertiesSet() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        LoggerMessage loggerMessage = loggerQueue.poll();
                        if (loggerMessage != null) {
                            if (messagingTemplate != null)
                                messagingTemplate.convertAndSend("/topic/pullLogger", loggerMessage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "pushLogger");
        thread.setDaemon(true);
        thread.start();
    }

}
