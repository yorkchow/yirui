package com.yirui.admin.sys.permission.dao;

import com.yirui.admin.sys.permission.model.Role;
import com.yirui.admin.sys.permission.model.RoleResourcePermission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:22
 */
@Service
public interface RoleDao {

    public List<Role> findAll();

    //@Select("from RoleResourcePermission where role=?1 and resourceId=?2")
    public RoleResourcePermission findRoleResourcePermission(Role role, Integer resourceId);

    public void update(Role role);
}
