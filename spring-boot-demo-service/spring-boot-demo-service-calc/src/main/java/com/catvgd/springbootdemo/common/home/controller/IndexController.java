package com.catvgd.springbootdemo.common.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/index")
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/plus", method = RequestMethod.GET)
    public int plus(int a, int b) {
        logger.debug("{} + {} = {}", a, b, (a + b));
        logger.info("{} + {} = {}", a, b, (a + b));
        logger.error("{} + {} = {}", a, b, (a + b));
        return a + b;
    }

}
