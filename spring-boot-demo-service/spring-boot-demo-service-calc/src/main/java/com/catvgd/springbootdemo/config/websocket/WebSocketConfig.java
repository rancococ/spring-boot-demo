package com.catvgd.springbootdemo.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.OriginHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // endPoint 注册协议节点,并映射指定的URl
        // 注册一个名字为"endpointChat" 的endpoint,并指定 SockJS协议。 点对点-用
        registry.addEndpoint("/websocket") // 指定地址
                .addInterceptors(new OriginHandshakeInterceptor())//
                .setAllowedOrigins("http://localhost")//
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { // 配置消息代理(message broker)
        // 点对点式增加一个/queue 消息代理
        registry.enableSimpleBroker("/queue", "/topic");
    }

}
