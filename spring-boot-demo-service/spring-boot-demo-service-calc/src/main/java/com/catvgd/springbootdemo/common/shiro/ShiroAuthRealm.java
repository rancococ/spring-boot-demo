package com.catvgd.springbootdemo.common.shiro;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.catvgd.springbootdemo.common.tkmapper.model.SysUser;
import com.catvgd.springbootdemo.common.util.Md5Util;

@Component
public class ShiroAuthRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(ShiroAuthRealm.class);

    @Autowired
    private ShiroUserService userService;
    // @Autowired
    // private ShiroPermissionService permissionService;
    // @Autowired
    // private ShiroRoleService roleService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 用户登录次数计数 redisKey 前缀
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";
    // 用户登录是否被锁定 一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * 
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        // 访问一次，计数一次
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.increment(SHIRO_LOGIN_COUNT + username, 1);
        // 计数大于5时，设置用户被锁定一小时
        if (Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + username)) >= 5) {
            opsForValue.set(SHIRO_IS_LOCK + username, "LOCK");
            stringRedisTemplate.expire(SHIRO_IS_LOCK + username, 1, TimeUnit.HOURS);
        }
        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK + username))) {
            throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
        }
        // 密码进行加密处理 明文为 password+name
        String paw = password + username;
        String pawmd5 = Md5Util.getMD5Str(paw);
        SysUser user = null;
        // 从数据库获取对应用户名密码的用户
        List<SysUser> userList = userService.selectUsersByUsernameAndPassward(username, pawmd5);
        if (userList.size() != 0) {
            user = userList.get(0);
        }
        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        } else if ("0".equals(user.getStatus())) {
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("此帐号已经设置为禁止登录！");
        } else {
            // 登录成功
            // 更新登录时间 last login time
            Date now = new Date();
            user.setLastLoginTime(now);
            user.setUpdateTime(now);
            userService.updateUsersByUid(user);
            // 清空登录计数
            opsForValue.set(SHIRO_LOGIN_COUNT + username, "0");
        }
        // Logger.getLogger(getClass()).info("身份认证成功，登录用户：" + name);
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("ShiroAuthRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        // String userId = String.valueOf(user.getUid());
        // 根据用户ID查询角色（role），放入到Authorization里。

        /*
         * Map<String, Object> map = new HashMap<String, Object>();
         * map.put("user_id", userId);
         * List<SysRole> roleList = sysRoleService.selectByMap(map);
         * Set<String> roleSet = new HashSet<String>();
         * for(SysRole role : roleList){
         * roleSet.add(role.getType());
         * }
         */
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("100002");
        info.setRoles(roleSet);
        // 根据用户ID查询权限（permission），放入到Authorization里。

        /*
         * List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
         * Set<String> permissionSet = new HashSet<String>();
         * for(SysPermission Permission : permissionList){
         * permissionSet.add(Permission.getName());
         * }
         */
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.setStringPermissions(permissionSet);
        return info;
    }

}
