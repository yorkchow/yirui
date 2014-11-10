package com.yirui.admin.sys.auth.model;

import com.google.common.collect.Sets;
import com.yirui.common.model.BaseModel;
import com.yirui.common.model.support.annotation.TableName;

import java.util.Set;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:05
 */
@TableName(value = "sys_auth")
public class Auth extends BaseModel<Integer> {

    /**
     * 组织机构
     * @Column(name = "organization_id")
     */
    private Integer organizationId = 0;

    /**
     * 工作职务
     * @Column(name = "job_id")
     */
    private Integer jobId = 0;

    /**
     * 用户
     * @Column(name = "user_id")
     */
    private Integer userId = 0;

    /**
     * 组
     * @Column(name = "group_id")
     */
    private Integer groupId = 0;

    //@Column(name = "role_ids")
    private Set<Integer> roleIds;

    private AuthType type;


    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<Integer> getRoleIds() {
        if (roleIds == null) {
            roleIds = Sets.newHashSet();
        }
        return roleIds;
    }

    public void setRoleIds(Set<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public void addRoleId(Integer roleId) {
        getRoleIds().add(roleId);
    }


    public void addRoleIds(Set<Integer> roleIds) {
        getRoleIds().addAll(roleIds);
    }

    public AuthType getType() {
        return type;
    }

    public void setType(AuthType type) {
        this.type = type;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
