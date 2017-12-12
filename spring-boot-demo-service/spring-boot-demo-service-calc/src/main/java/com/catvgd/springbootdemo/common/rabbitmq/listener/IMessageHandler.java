package com.catvgd.springbootdemo.common.rabbitmq.listener;

public interface IMessageHandler {

    void handler(String message);

}
