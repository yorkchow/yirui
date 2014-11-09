package com.yirui.admin.sys.group.service;

import com.yirui.admin.sys.group.dao.GroupDao;
import com.yirui.admin.sys.group.model.Group;
import com.yirui.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 16:59
 */
@Service
public class GroupService extends BaseService<Group, Integer> {

    @Autowired
    private GroupDao groupDao;


}
