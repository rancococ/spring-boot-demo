package com.catvgd.springbootdemo.biz.calc.controller;

import com.catvgd.springbootdemo.biz.calc.model.User;
import com.catvgd.springbootdemo.biz.calc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Api(value = "用户信息1", tags = "用户信息")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserService userService;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
    @Resource(name = "objectRedisTemplate")
    private RedisTemplate<Object, Object> objectRedisTemplate;

    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getUserList(HttpSession session) {
        logger.info("id(zh):" + messageSource.getMessage("user.id", null, Locale.CHINA));
        logger.info("name(en):" + messageSource.getMessage("user.name", null, Locale.US));
        logger.info("id(zh):" + messageSource.getMessage("system.user.id", null, Locale.CHINA));
        logger.info("name(en):" + messageSource.getMessage("system.user.name", null, Locale.US));
        List<User> userList = userService.getUserList();
        logger.info("====user list size====" + userList.size());

        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        logger.info("====session====");
        logger.info("sid:" + session.getId());
        logger.info("uid:" + uid);

        Object obj = objectRedisTemplate.opsForValue().get("abc");
        if (obj == null) {
            objectRedisTemplate.opsForValue().set("abc", "123");
        }
        logger.info(objectRedisTemplate.opsForValue().get("abc").toString());
        logger.info(stringRedisTemplate.keys("*").toString());

        return userList;
    }

}
