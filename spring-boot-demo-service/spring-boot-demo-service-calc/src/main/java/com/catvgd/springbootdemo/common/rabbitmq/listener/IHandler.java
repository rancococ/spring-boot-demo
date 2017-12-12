package com.catvgd.springbootdemo.common.rabbitmq.listener;

public interface IHandler {

    void handler(String message);

}
