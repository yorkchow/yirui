package com.yirui.admin.sys.permission.service;

import com.yirui.admin.sys.permission.model.Role;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:21
 */
public interface RoleService {


    public void update(Role role);

    /**
     * 获取可用的角色列表
     *
     * @param roleIds
     * @return
     */
    public List<Role> findShowRoles(List<Integer> roleIds);
}
