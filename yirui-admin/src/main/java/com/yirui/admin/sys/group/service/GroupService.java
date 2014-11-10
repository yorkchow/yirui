package com.yirui.admin.sys.group.service;

import com.yirui.admin.sys.group.model.Group;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 16:59
 */
public interface GroupService {

    /**
     * 获取可用的的分组编号列表
     *
     * @param userId
     * @param organizationIds
     * @return
     */
    public List<Integer> findShowGroupIds(Integer userId, List<Integer> organizationIds);

    public Group findOne(Integer id);
}
