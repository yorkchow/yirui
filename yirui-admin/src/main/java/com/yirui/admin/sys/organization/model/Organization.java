package com.yirui.admin.sys.organization.model;

import com.yirui.common.model.BaseTreeModel;
import com.yirui.common.model.support.annotation.TableName;

/**
 * 组织机构树
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 22:52
 */
@TableName(value = "sys_organization")
public class Organization extends BaseTreeModel<Integer> {

    /**
     * 组织机构类型 默认 部门
     */
    private OrganizationType type = OrganizationType.department;

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }
}
