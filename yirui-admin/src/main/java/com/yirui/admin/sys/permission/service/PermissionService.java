package com.yirui.admin.sys.permission.service;


import com.yirui.admin.sys.permission.model.Permission;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:27
 */
public interface PermissionService {

    public Permission findOne(Integer id);
}
