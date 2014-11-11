package com.yirui.admin.sys.user.service.impl;

import com.yirui.admin.sys.user.dao.UserDao;
import com.yirui.admin.sys.user.exception.UserBlockedException;
import com.yirui.admin.sys.user.exception.UserNotExistsException;
import com.yirui.admin.sys.user.exception.UserPasswordNotMatchException;
import com.yirui.admin.sys.user.model.User;
import com.yirui.admin.sys.user.model.UserStatus;
import com.yirui.admin.sys.user.service.PasswordService;
import com.yirui.admin.sys.user.service.UserService;
import com.yirui.admin.sys.user.service.UserStatusHistoryService;
import com.yirui.admin.sys.user.utils.UserLogUtils;
import com.yirui.common.service.BaseService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 10:42
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserStatusHistoryService userStatusHistoryService;

    @Autowired
    private PasswordService passwordService;

    @Override
    public User findOne(Integer id) {
        return userDao.findOne(id);
    }

    public User findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        return userDao.findByEmail(email);
    }

    public User findByMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return null;
        }
        return userDao.findByMobile(mobile);
    }

    public User login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "username is empty");
            throw new UserNotExistsException();
        }
        //密码如果不在指定范围内 肯定错误
        if (password.length() < User.PASSWORD_MIN_LENGTH || password.length() > User.PASSWORD_MAX_LENGTH) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "password length error! password is between {} and {}",
                    User.PASSWORD_MIN_LENGTH, User.PASSWORD_MAX_LENGTH);

            throw new UserPasswordNotMatchException();
        }

        User user = null;

        //此处需要走代理对象，目的是能走缓存切面
        UserService proxyUserService = (UserService) AopContext.currentProxy();
        if (maybeUsername(username)) {
            user = proxyUserService.findByUsername(username);
        }

        if (user == null && maybeEmail(username)) {
            user = proxyUserService.findByEmail(username);
        }

        if (user == null && maybeMobile(username)) {
            user = proxyUserService.findByMobile(username);
        }

        if (user == null) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "user is not exists!");

            throw new UserNotExistsException();
        }

        passwordService.validate(user, password);

        if (user.getStatus() == UserStatus.blocked) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "user is blocked!");
            throw new UserBlockedException(userStatusHistoryService.getLastReason(user));
        }

        UserLogUtils.log(username, "loginSuccess", "");
        return user;
    }

    private boolean maybeUsername(String username) {
        if (!username.matches(User.USERNAME_PATTERN)) {
            return false;
        }
        //如果用户名不在指定范围内也是错误的
        if (username.length() < User.USERNAME_MIN_LENGTH || username.length() > User.USERNAME_MAX_LENGTH) {
            return false;
        }

        return true;
    }

    private boolean maybeEmail(String username) {
        if (!username.matches(User.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }

    private boolean maybeMobile(String username) {
        if (!username.matches(User.MOBILE_PATTERN)) {
            return false;
        }
        return true;
    }
}
