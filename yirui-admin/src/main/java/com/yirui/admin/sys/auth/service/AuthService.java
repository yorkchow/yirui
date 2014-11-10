package com.yirui.admin.sys.auth.service;

import com.yirui.admin.sys.auth.model.Auth;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:04
 */
public interface AuthService {

    public void addUserAuth(Integer[] userIds, Auth m);

    public void addGroupAuth(Integer[] groupIds, Auth m);

    public void addOrganizationJobAuth(Integer[] organizationIds, Integer[][] jobIds, Auth m);


    /**
     * 根据用户信息获取 角色
     * 1.1、用户  根据用户绝对匹配
     * 1.2、组织机构 根据组织机构绝对匹配 此处需要注意 祖先需要自己获取
     * 1.3、工作职务 根据工作职务绝对匹配 此处需要注意 祖先需要自己获取
     * 1.4、组织机构和工作职务  根据组织机构和工作职务绝对匹配 此处不匹配祖先
     * 1.5、组  根据组绝对匹配
     *
     * @param userId             必须有
     * @param groupIds           可选
     * @param organizationIds    可选
     * @param jobIds             可选
     * @param organizationJobIds 可选
     * @return
     */
    public List<Integer> findRoleIds(Integer userId, List<Integer> groupIds, List<Integer> organizationIds,
                                     List<Integer> jobIds, List<Integer[]> organizationJobIds);
}
