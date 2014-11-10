package com.yirui.admin.sys.user.service;

import com.yirui.admin.sys.user.model.User;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 10:42
 */
public interface UserService {

    public User findOne(Integer id);

    public User findByUsername(String username);

    public User findByEmail(String email);

    public User findByMobile(String mobile);

    public User login(String username, String password);

}
