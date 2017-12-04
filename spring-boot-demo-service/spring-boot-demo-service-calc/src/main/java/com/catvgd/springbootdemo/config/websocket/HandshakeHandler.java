package com.catvgd.springbootdemo.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

public class HandshakeHandler extends DefaultHandshakeHandler {
    
    private static Logger logger = LoggerFactory.getLogger(HandshakeHandler.class);
    
}
