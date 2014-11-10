package com.yirui.admin.sys.permission.dao;

import com.yirui.admin.sys.permission.model.Permission;
import org.springframework.stereotype.Service;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/11
 * Time: 2:39
 */
@Service
public interface PermissionDao {

    public Permission findOne(Integer id);
}
