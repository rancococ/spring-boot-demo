package com.catvgd.springbootdemo.common.logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoggerManager implements InitializingBean {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        LoggerMessage loggerMessage = LoggerQueue.getInstance().poll();
                        if (loggerMessage != null) {
                            if (messagingTemplate != null)
                                messagingTemplate.convertAndSend("/topic/pullLogger", loggerMessage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }
        }, "pushLogger");
        thread.setDaemon(true);
        thread.start();
    }

}
