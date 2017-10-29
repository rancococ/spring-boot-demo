package com.catvgd.springbootdemo.biz.calc.controller;

import com.catvgd.springbootdemo.biz.calc.model.User;
import com.catvgd.springbootdemo.biz.calc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@Api(value = "用户信息1", tags = "用户信息")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getUserList() {
        logger.info("id(zh):" + messageSource.getMessage("user.id", null, Locale.CHINA));
        logger.info("name(en):" + messageSource.getMessage("user.name", null, Locale.US));
        logger.info("id(zh):" + messageSource.getMessage("system.user.id", null, Locale.CHINA));
        logger.info("name(en):" + messageSource.getMessage("system.user.name", null, Locale.US));
        List<User> userList = userService.getUserList();
        logger.info("====user list size====" + userList.size());
        return userList;
    }

}
