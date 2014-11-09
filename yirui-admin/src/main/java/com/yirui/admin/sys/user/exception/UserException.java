package com.yirui.admin.sys.user.exception;

import com.yirui.common.exception.BaseException;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 10:37
 */
public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }

}
