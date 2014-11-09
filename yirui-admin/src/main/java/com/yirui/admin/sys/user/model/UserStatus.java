package com.yirui.admin.sys.user.model;

/**
 * User status
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/7
 * Time: 22:41
 */
public enum UserStatus {

    normal("正常状态"), blocked("封禁状态");

    private final String info;

    private UserStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
