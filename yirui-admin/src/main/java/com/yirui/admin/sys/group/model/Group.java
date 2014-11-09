package com.yirui.admin.sys.group.model;

import com.yirui.common.model.BaseModel;
import com.yirui.common.model.support.annotation.TableName;

/**
 * 分组超类
 *
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 17:01
 */
//@Table(name = "sys_group")
@TableName(value = "sys_group")
public class Group extends BaseModel<Integer> {

    /**
     * 分组名称
     */
    private String name;

    /**
     * 分组类型 如 用户分组/组织机构分组
     */
    private GroupType type;

    /**
     * 是否是默认分组
     */
    private Boolean defaultGroup = Boolean.FALSE;

    /**
     * 是否显示/可用
     */
    private Boolean show = Boolean.FALSE;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupType getType() {
        return type;
    }

    public void setType(GroupType type) {
        this.type = type;
    }

    public Boolean getDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(Boolean defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
