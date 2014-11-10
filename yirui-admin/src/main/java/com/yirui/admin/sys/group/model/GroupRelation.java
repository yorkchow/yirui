package com.yirui.admin.sys.group.model;

import com.yirui.common.model.BaseModel;
import com.yirui.common.model.support.annotation.TableName;

/**
 * 分组与 用户/组织机构关系表
 * <p/>
 * 将用户/组织机构放一张表目的是提高查询性能
 * <p/>
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:46
 */
@TableName(value = "sys_group_relation")
public class GroupRelation extends BaseModel<Integer> {

    //@Column(name = "group_id")
    private Integer groupId;

    //@Column(name = "organization_id")
    private Integer organizationId;

    /**
     * 关联的单个用户
     */
    //@Column(name = "user_id")
    private Integer userId;

    /**
     * 关联的 区间user id 作为单个关联的一种优化
     * 和user二者选一
     * [startUserId, endUserId]闭区间
     */
    //@Column(name = "start_user_id")
    private Integer startUserId;
    //@Column(name = "end_user_id")
    private Integer endUserId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(Integer startUserId) {
        this.startUserId = startUserId;
    }

    public Integer getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(Integer endUserId) {
        this.endUserId = endUserId;
    }
}
