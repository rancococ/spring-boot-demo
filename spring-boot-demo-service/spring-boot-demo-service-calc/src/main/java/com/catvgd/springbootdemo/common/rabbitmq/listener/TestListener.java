package com.catvgd.springbootdemo.common.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestListener {

    private final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Autowired
    private List<IHandler> handlerList;

    @RabbitListener(queues = {"test1"}, containerFactory = "rabbitListenerContainerFactory")
    @RabbitHandler
    public void handler(Message message) {
        if (message.getMessageProperties() != null) {
            logger.info(message.getMessageProperties().toString());
        }
        String jsonMessage = new String(message.getBody());
        logger.info(jsonMessage);
        if (handlerList != null && !handlerList.isEmpty()) {
            for (IHandler handler : handlerList) {
                handler.handler();
            }
        }
    }

}
