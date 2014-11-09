package com.yirui.admin.sys.permission.model;

import com.yirui.common.model.BaseModel;
import com.yirui.common.model.support.annotation.TableName;

/**
 * 权限表
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/9
 * Time: 0:40
 */
//@Table(name = "sys_permission")
@TableName(value = "sys_permission")
public class Permission extends BaseModel<Integer> {

    /**
     * 前端显示名称
     */
    private String name;
    /**
     * 系统中验证时使用的权限标识
     */
    private String permission;

    /**
     * 详细描述
     */
    private String description;

    /**
     * 是否显示 也表示是否可用 为了统一 都使用这个
     * @Column(name = "is_show")
     */
    private Boolean show = Boolean.FALSE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
