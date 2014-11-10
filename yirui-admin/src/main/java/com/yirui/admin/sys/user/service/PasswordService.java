package com.yirui.admin.sys.user.service;

import com.yirui.admin.sys.user.model.User;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 14:27
 */
public interface PasswordService {

    public void init();

    public void validate(User user, String password);

}
