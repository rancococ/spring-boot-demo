package com.catvgd.springbootdemo.biz.calc.mapper;

import com.catvgd.springbootdemo.biz.calc.model.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface UserMapper extends Mapper<User>, MySqlMapper<User> {

    List<User> getUserList();

    User getUserById(@Param("id") long id);

}
