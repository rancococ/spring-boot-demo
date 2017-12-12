package com.catvgd.springbootdemo.config.rabbitmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRabbit
public class RabbitmqConfig {

    @Bean(name = "rabbitAdmin")
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean(name = "test1")
    public Queue rabbitQueue(@Qualifier(value = "rabbitAdmin") RabbitAdmin rabbitAdmin) {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("x-max-priority", 10);
        Queue queue = new Queue("test1", true, false, false, props);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean(name = "test2")
    public Queue rabbitQueue2(@Qualifier(value = "rabbitAdmin") RabbitAdmin rabbitAdmin) {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("x-max-priority", 10);
        Queue queue = new Queue("test2", true, false, false, props);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean(name = "topic-exchange")
    public TopicExchange exchange(@Qualifier(value = "rabbitAdmin") RabbitAdmin rabbitAdmin) {
        TopicExchange exchange = new TopicExchange("topic-exchange", true, false);
        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Binding bindingExchangeMessage(@Qualifier(value = "rabbitAdmin") RabbitAdmin rabbitAdmin, @Qualifier(value = "test1") Queue queue,
            @Qualifier(value = "topic-exchange") TopicExchange exchange) {
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("key.message");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public Binding bindingExchangeMessage2(@Qualifier(value = "rabbitAdmin") RabbitAdmin rabbitAdmin, @Qualifier(value = "test2") Queue queue,
            @Qualifier(value = "topic-exchange") TopicExchange exchange) {
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("key.message2");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name = "rabbitTemplate")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        template.setEncoding("UTF-8");
        template.setExchange("topic-exchange");
        return template;
    }

    @Bean(name = "rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

}
