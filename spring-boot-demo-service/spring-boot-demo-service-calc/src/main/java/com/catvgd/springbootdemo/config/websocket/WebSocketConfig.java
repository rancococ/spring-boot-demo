package com.catvgd.springbootdemo.config.websocket;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 服务器要监听的端口，message会从这里进来，要对这里加一个Handler
     * 这样在网页中就可以通过websocket连接上服务了
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // endPoint 注册协议节点,并映射指定的URl
        // 注册一个名字为"endpointChat" 的endpoint,并指定 SockJS协议。 点对点-用
        registry.addEndpoint("/websocket") // 指定地址
                .setAllowedOrigins("*")//
                .setHandshakeHandler(new HandshakeHandler())//
                .addInterceptors(new HandshakeInterceptor())//
                .withSockJS();
    }

    /**
     * 消息传输参数配置
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.setMessageSizeLimit(8192) // 设置消息字节数大小
                .setSendBufferSizeLimit(8192)// 设置消息缓存大小
                .setSendTimeLimit(10000); // 设置消息发送时间限制毫秒
    }

    /**
     * 输入通道参数设置
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.taskExecutor().corePoolSize(4) // 设置消息输入通道的线程池线程数
                .maxPoolSize(8)// 最大线程数
                .keepAliveSeconds(60);// 线程活动时间
    }

    /**
     * 输出通道参数设置
     */
    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.taskExecutor().corePoolSize(4).maxPoolSize(8);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        return true;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { // 配置消息代理(message broker)
        // 点对点式增加一个/queue 消息代理
        // registry.enableSimpleBroker("/queue", "/topic");
        registry.enableStompBrokerRelay("/topic") // 设置可以订阅的地址，也就是服务器可以发送的地址
                // .setRelayHost(ConfigureUtil.getProperty("BrokerUrl")).setRelayPort(Integer.valueOf(ConfigureUtil.getProperty("BrokerPort"))) //
                // 设置broker的地址及端口号
                .setSystemHeartbeatReceiveInterval(2000) // 设置心跳信息接收时间间隔
                .setSystemHeartbeatSendInterval(2000); // 设置心跳信息发送时间间隔
        registry.setApplicationDestinationPrefixes("/ws");
    }

}
