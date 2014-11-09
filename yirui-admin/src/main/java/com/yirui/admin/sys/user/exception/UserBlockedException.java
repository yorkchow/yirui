package com.yirui.admin.sys.user.exception;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 10:40
 */
public class UserBlockedException extends UserException {

    public UserBlockedException(String reason) {
        super("user.blocked", new Object[]{reason});
    }
}
