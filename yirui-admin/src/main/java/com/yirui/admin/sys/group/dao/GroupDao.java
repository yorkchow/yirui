package com.yirui.admin.sys.group.dao;

import com.yirui.admin.sys.group.model.Group;
import com.yirui.common.dao.BaseDao;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 17:05
 */
public interface GroupDao extends BaseDao<Group, Integer> {

    List<Group> findAll();

    List<Integer> findDefaultGroupIds();
}
