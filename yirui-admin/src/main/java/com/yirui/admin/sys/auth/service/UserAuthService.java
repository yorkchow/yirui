package com.yirui.admin.sys.auth.service;

import com.yirui.admin.sys.permission.model.Role;
import com.yirui.admin.sys.user.model.User;

import java.util.List;

/**
 * 分组、组织机构、用户、新增、修改、删除时evict缓存
 * <p/>
 * 获取用户授权的角色及组装好的权限
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 10:35
 */
public interface UserAuthService {

    public List<Role> findRoles(User user);

    public List<String> findStringRoles(User user);

    /**
     * 根据角色获取 权限字符串 如sys:admin
     *
     * @param user
     * @return
     */
    public List<String> findStringPermissions(User user);
}
