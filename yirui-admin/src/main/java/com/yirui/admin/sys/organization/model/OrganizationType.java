package com.yirui.admin.sys.organization.model;

/**
 * 组织机构类型
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 22:53
 */
public enum OrganizationType {

    bloc("集团"), branch_office("分公司"), department("部门"), group("部门小组");

    private final String info;

    private OrganizationType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
