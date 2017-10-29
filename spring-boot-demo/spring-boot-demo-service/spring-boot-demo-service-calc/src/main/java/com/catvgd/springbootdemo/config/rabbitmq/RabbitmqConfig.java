package com.catvgd.springbootdemo.config.rabbitmq;

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

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean(name = "test1")
    public Queue rabbitQueue() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("x-max-priority", 10);
        return new Queue("test1", true, false, false, props);
    }

    @Bean(name = "test2")
    public Queue rabbitQueue2() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("x-max-priority", 10);
        return new Queue("test2", true, false, false, props);
    }

    @Bean(name = "topic-exchange")
    public TopicExchange exchange() {
        return new TopicExchange("exchange", true, false);
    }

    @Bean
    public Binding bindingExchangeMessage(@Qualifier(value = "test1") Queue queue, @Qualifier(value = "topic-exchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.message");
    }

    @Bean
    public Binding bindingExchangeMessage2(@Qualifier(value = "test2") Queue queue, @Qualifier(value = "topic-exchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.message2");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        template.setExchange("topic-exchange");
        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

}
