package com.catvgd.springbootdemo.common.shiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catvgd.springbootdemo.common.tkmapper.mapper.SysUserMapper;
import com.catvgd.springbootdemo.common.tkmapper.model.SysUser;

import tk.mybatis.mapper.entity.Example;

@Service
public class ShiroUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public List<SysUser> selectUsersByUsernameAndPassward(String username, String password) {
        List<SysUser> users = null;
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("username", username).andEqualTo("password", password);
        users = sysUserMapper.selectByExample(example);
        return users;
    }

    public void updateUsersByUid(SysUser user) {
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

}
