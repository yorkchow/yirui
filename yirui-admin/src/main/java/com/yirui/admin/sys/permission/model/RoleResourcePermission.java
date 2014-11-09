package com.yirui.admin.sys.permission.model;

import com.google.common.collect.Sets;
import com.yirui.common.model.BaseModel;
import com.yirui.common.model.support.annotation.TableName;

import java.util.Set;

/**
 * 此处没有使用关联 是为了提高性能（后续会挨着查询资源和权限列表，因为有缓存，数据量也不是很大 所以性能不会差）
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/9
 * Time: 0:36
 */
//@Table(name = "sys_role_resource_permission")
@TableName(value = "sys_role_resource_permission")
public class RoleResourcePermission extends BaseModel<Integer> {

    /**
     * 角色id
     */
    private Role role;

    /**
     * 资源id
     * @Column(name = "resource_id")
     */
    private Integer resourceId;

    /**
     * 权限id列表
     * 数据库通过字符串存储 逗号分隔
     * @Column(name = "permission_ids")
     */
    private Set<Integer> permissionIds;

    public RoleResourcePermission() {
    }

    public RoleResourcePermission(Integer id) {
        setId(id);
    }

    public RoleResourcePermission(Integer resourceId, Set<Integer> permissionIds) {
        this.resourceId = resourceId;
        this.permissionIds = permissionIds;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Set<Integer> getPermissionIds() {
        if (permissionIds == null) {
            permissionIds = Sets.newHashSet();
        }
        return permissionIds;
    }

    public void setPermissionIds(Set<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Override
    public String toString() {
        return "RoleResourcePermission{id=" + this.getId() +
                ",roleId=" + (role != null ? role.getId() : "null") +
                ", resourceId=" + resourceId +
                ", permissionIds=" + permissionIds +
                '}';
    }
}
