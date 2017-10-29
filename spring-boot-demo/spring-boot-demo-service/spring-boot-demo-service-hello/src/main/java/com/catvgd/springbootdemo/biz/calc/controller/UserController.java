package com.catvgd.springbootdemo.biz.calc.controller;

import com.catvgd.springbootdemo.biz.calc.model.User;
import com.catvgd.springbootdemo.biz.calc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> userList = userService.getUserList();
        logger.info("====user list size====" + userList.size());
        return userList;
    }

}
