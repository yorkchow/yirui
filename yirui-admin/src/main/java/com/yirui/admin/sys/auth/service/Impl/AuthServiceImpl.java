package com.yirui.admin.sys.auth.service.Impl;

import com.yirui.admin.sys.auth.dao.AuthDao;
import com.yirui.admin.sys.auth.model.Auth;
import com.yirui.admin.sys.auth.service.AuthService;
import com.yirui.admin.sys.group.model.Group;
import com.yirui.admin.sys.group.service.GroupService;
import com.yirui.admin.sys.user.model.User;
import com.yirui.admin.sys.user.service.UserService;
import com.yirui.common.service.BaseService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:04
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private AuthDao authDao;

    public void addUserAuth(Integer[] userIds, Auth m) {

        if (ArrayUtils.isEmpty(userIds)) {
            return;
        }

        for (Integer userId : userIds) {

            User user = userService.findOne(userId);
            if (user == null) {
                continue;
            }

            Auth auth = authDao.findByUserId(userId);
            if (auth != null) {
                auth.addRoleIds(m.getRoleIds());
                continue;
            }
            auth = new Auth();
            auth.setUserId(userId);
            auth.setType(m.getType());
            auth.setRoleIds(m.getRoleIds());
            authDao.save(auth);
        }
    }

    public void addGroupAuth(Integer[] groupIds, Auth m) {
        if (ArrayUtils.isEmpty(groupIds)) {
            return;
        }

        for (Integer groupId : groupIds) {
            Group group = groupService.findOne(groupId);
            if (group == null) {
                continue;
            }

            Auth auth = authDao.findByGroupId(groupId);
            if (auth != null) {
                auth.addRoleIds(m.getRoleIds());
                continue;
            }
            auth = new Auth();
            auth.setGroupId(groupId);
            auth.setType(m.getType());
            auth.setRoleIds(m.getRoleIds());
            authDao.save(auth);
        }
    }

    public void addOrganizationJobAuth(Integer[] organizationIds, Integer[][] jobIds, Auth m) {

        if (ArrayUtils.isEmpty(organizationIds)) {
            return;
        }
        for (int i = 0, l = organizationIds.length; i < l; i++) {
            Integer organizationId = organizationIds[i];
            if (jobIds[i].length == 0) {
                addOrganizationJobAuth(organizationId, null, m);
                continue;
            }

            //仅新增/修改一个 spring会自动split（“，”）--->给数组
            if (l == 1) {
                for (int j = 0, l2 = jobIds.length; j < l2; j++) {
                    Integer jobId = jobIds[i][0];
                    addOrganizationJobAuth(organizationId, jobId, m);
                }
            } else {
                for (int j = 0, l2 = jobIds[i].length; j < l2; j++) {
                    Integer jobId = jobIds[i][0];
                    addOrganizationJobAuth(organizationId, jobId, m);
                }
            }

        }
    }

    private void addOrganizationJobAuth(Integer organizationId, Integer jobId, Auth m) {
        if (organizationId == null) {
            organizationId = 0;
        }
        if (jobId == null) {
            jobId = 0;
        }


        Auth auth = authDao.findByOrganizationIdAndJobId(organizationId, jobId);
        if (auth != null) {
            auth.addRoleIds(m.getRoleIds());
            return;
        }

        auth = new Auth();
        auth.setOrganizationId(organizationId);
        auth.setJobId(jobId);
        auth.setType(m.getType());
        auth.setRoleIds(m.getRoleIds());
        authDao.save(auth);

    }

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
                                     List<Integer> jobIds, List<Integer[]> organizationJobIds) {
        return authDao.findRoleIds(userId, groupIds, organizationIds, jobIds, organizationJobIds);
    }
}
