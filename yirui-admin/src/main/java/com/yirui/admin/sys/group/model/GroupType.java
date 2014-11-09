package com.yirui.admin.sys.group.model;

/**
 * 用户组分类
 *
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 17:02
 */
public enum GroupType {

    user("用户组"), organization("组织机构组");

    private final String info;

    private GroupType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
