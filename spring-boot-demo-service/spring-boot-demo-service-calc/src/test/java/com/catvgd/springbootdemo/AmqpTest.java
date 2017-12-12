package com.catvgd.springbootdemo;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AmqpTest {

    private Logger logger = LoggerFactory.getLogger(AmqpTest.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    private String queue_key = "key.message";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void send() throws Exception {
        logger.info("-----------------start------------------------");
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            final int priority = random.nextInt(10 - 1 + 1) + 1;// 随机的优先级
            amqpTemplate.convertAndSend(queue_key, (Object) ("---No " + i + "---this is test message---"), new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setPriority(priority);
                    return message;
                }
            });
        }
        logger.info("-----------------end--------------------------");
        Thread.sleep(10000);
    }

}
