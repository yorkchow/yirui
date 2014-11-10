package com.yirui.admin.sys.permission.service.impl;

import com.yirui.admin.sys.permission.dao.PermissionDao;
import com.yirui.admin.sys.permission.model.Permission;
import com.yirui.admin.sys.permission.service.PermissionService;
import com.yirui.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:27
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission findOne(Integer id) {
        return permissionDao.findOne(id);
    }
}
