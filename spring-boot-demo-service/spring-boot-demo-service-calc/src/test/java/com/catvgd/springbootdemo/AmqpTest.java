package com.catvgd.springbootdemo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AmqpTest {

    private Logger logger = LoggerFactory.getLogger(AmqpTest.class);

    @Autowired
    private org.springframework.amqp.rabbit.core.RabbitTemplate rabbitTemplate;

    private String queue_key = "key.message";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void send() throws Exception {
        try {
            logger.info("-----------------------------------------");
            rabbitTemplate.convertAndSend(queue_key, "NIHAO..............");
            logger.info("-----------------------------------------");
        } catch (Exception e) {
            // LOGGER.error(e);
        }
        Thread.sleep(100000);
    }

}
