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
public class MessageListenerContainer {

    private final Logger logger = LoggerFactory.getLogger(MessageListenerContainer.class);

    @Autowired
    private List<IMessageHandler> handlerList;

    @RabbitListener(queues = { "test1" }, containerFactory = "rabbitListenerContainerFactory")
    @RabbitHandler
    public void handler(Message message) {
        if (message.getMessageProperties() != null) {
            // logger.info(message.getMessageProperties().toString());
            logger.info("Priority : " + message.getMessageProperties().getPriority());
        }
        String jsonMessage = new String(message.getBody());
        // logger.info(jsonMessage);
        if (handlerList != null && !handlerList.isEmpty()) {
            for (IMessageHandler handler : handlerList) {
                handler.handler(jsonMessage);
            }
        }
    }

    @RabbitListener(queues = { "test2" }, containerFactory = "rabbitListenerContainerFactory")
    @RabbitHandler
    public void handler2(Message message) {
        if (message.getMessageProperties() != null) {
            // logger.info(message.getMessageProperties().toString());
            logger.info("Priority : " + message.getMessageProperties().getPriority());
        }
        String jsonMessage = new String(message.getBody());
        // logger.info(jsonMessage);
        if (handlerList != null && !handlerList.isEmpty()) {
            for (IMessageHandler handler : handlerList) {
                handler.handler(jsonMessage);
            }
        }
    }
}
