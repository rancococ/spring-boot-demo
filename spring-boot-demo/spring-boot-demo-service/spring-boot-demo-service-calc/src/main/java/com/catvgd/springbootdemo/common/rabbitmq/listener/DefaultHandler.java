package com.catvgd.springbootdemo.common.rabbitmq.listener;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 99999)
public class DefaultHandler implements IHandler {

    public void handler() {
        //
    }

}
