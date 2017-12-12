package com.catvgd.springbootdemo.common.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 99999)
public class DefaultHandler implements IHandler {

    private Logger logger = LoggerFactory.getLogger(DefaultHandler.class);

    public void handler(String message) {
        logger.info(message);
    }

}
