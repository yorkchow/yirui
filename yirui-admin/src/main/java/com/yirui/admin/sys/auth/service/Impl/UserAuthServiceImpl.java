package com.yirui.admin.sys.auth.service.Impl;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.yirui.admin.sys.auth.service.AuthService;
import com.yirui.admin.sys.auth.service.UserAuthService;
import com.yirui.admin.sys.group.service.GroupService;
import com.yirui.admin.sys.organization.service.JobService;
import com.yirui.admin.sys.organization.service.OrganizationService;
import com.yirui.admin.sys.permission.model.Permission;
import com.yirui.admin.sys.permission.model.Role;
import com.yirui.admin.sys.permission.model.RoleResourcePermission;
import com.yirui.admin.sys.permission.service.PermissionService;
import com.yirui.admin.sys.permission.service.RoleService;
import com.yirui.admin.sys.resource.model.Resource;
import com.yirui.admin.sys.resource.service.ResourceService;
import com.yirui.admin.sys.user.model.User;
import com.yirui.admin.sys.user.model.UserOrganizationJob;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 分组、组织机构、用户、新增、修改、删除时evict缓存
 * <p/>
 * 获取用户授权的角色及组装好的权限
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 10:35
 */
@Service("userAuthService")
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private JobService jobService;

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PermissionService permissionService;


    public List<Role> findRoles(User user) {

        if (user == null) {
            return Lists.newArrayList();
        }

        Integer userId = user.getId();

        List<Integer[]> organizationJobIds = Lists.newArrayList();
        List<Integer> organizationIds = Lists.newArrayList();
        List<Integer> jobIds = Lists.newArrayList();

        for (UserOrganizationJob o : user.getOrganizationJobs()) {
            Integer organizationId = o.getOrganizationId();
            Integer jobId = o.getJobId();

            if (organizationId != null && jobId != null && organizationId != 0L && jobId != 0L) {
                organizationJobIds.add(new Integer[]{organizationId, jobId});
            }
            organizationIds.add(organizationId);
            jobIds.add(jobId);
        }

        //TODO 目前默认子会继承父 后续实现添加flag控制是否继承

        //找组织机构祖先
        organizationIds.addAll(organizationService.findAncestorIds(organizationIds));
        //找工作职务的祖先
        jobIds.addAll(jobService.findAncestorIds(jobIds));

        //过滤组织机构 仅获取目前可用的组织机构数据
        organizationService.filterForCanShow(organizationIds, organizationJobIds);
        jobService.filterForCanShow(jobIds, organizationJobIds);

        //过滤工作职务 仅获取目前可用的工作职务数据

        //默认分组 + 根据用户编号 和 组织编号 找 分组
        List<Integer> groupIds = groupService.findShowGroupIds(userId, organizationIds);

        //获取权限
        //1.1、获取用户角色
        //1.2、获取组织机构角色
        //1.3、获取工作职务角色
        //1.4、获取组织机构和工作职务组合的角色
        //1.5、获取组角色
        List<Integer> roleIds = authService.findRoleIds(userId, groupIds, organizationIds, jobIds, organizationJobIds);

        List<Role> roles = roleService.findShowRoles(roleIds);

        return roles;

    }

    public List<String> findStringRoles(User user) {
        List<Role> roles = ((UserAuthServiceImpl) AopContext.currentProxy()).findRoles(user);
        return Lists.newArrayList(Collections2.transform(roles, new Function<Role, String>() {
            @Override
            public String apply(Role input) {
                return input.getRole();
            }
        }));
    }

    /**
     * 根据角色获取 权限字符串 如sys:admin
     *
     * @param user
     * @return
     */
    public List<String> findStringPermissions(User user) {
        List<String> permissions = Lists.newArrayList();

        List<Role> roles = ((UserAuthServiceImpl) AopContext.currentProxy()).findRoles(user);
        for (Role role : roles) {
            for (RoleResourcePermission rrp : role.getResourcePermissions()) {
                Resource resource = resourceService.findOne(rrp.getResourceId());

                String actualResourceIdentity = resourceService.findActualResourceIdentity(resource);

                //不可用 即没查到 或者标识字符串不存在
                if (resource == null || StringUtils.isEmpty(actualResourceIdentity) || Boolean.FALSE.equals(resource.getShow())) {
                    continue;
                }

                for (Integer permissionId : rrp.getPermissionIds()) {
                    Permission permission = permissionService.findOne(permissionId);

                    //不可用
                    if (permission == null || Boolean.FALSE.equals(permission.getShow())) {
                        continue;
                    }
                    permissions.add(actualResourceIdentity + ":" + permission.getPermission());

                }
            }

        }

        return permissions;
    }
}
