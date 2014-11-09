package com.yirui.admin.sys.user.model;

import com.yirui.common.model.BaseModel;
import com.yirui.common.model.support.annotation.TableName;

/**
 * 为了提高连表性能 使用数据冗余 而不是 组织机构(1)-----(*)职务的中间表
 * 即在该表中 用户--组织机构--职务 是唯一的  但 用户-组织机构可能重复
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/7
 * Time: 22:45
 */
//@Table(name = "sys_user_organization_job")
@TableName(value = "sys_user_organization_job")
public class UserOrganizationJob extends BaseModel<Integer> {

    private User user;

    private Integer organizationId;

    private Integer jobId;


    public UserOrganizationJob() {
    }

    public UserOrganizationJob(Integer id) {
        setId(id);
    }

    public UserOrganizationJob(Integer organizationId, Integer jobId) {
        this.organizationId = organizationId;
        this.jobId = jobId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    @Override
    public String toString() {
        return "UserOrganizationJob{id = " + this.getId() +
                ",organizationId=" + organizationId +
                ", jobId=" + jobId +
                ", userId=" + (user != null ? user.getId() : "null") +
                '}';
    }
}
