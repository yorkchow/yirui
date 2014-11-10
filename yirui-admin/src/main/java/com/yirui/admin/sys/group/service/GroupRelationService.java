package com.yirui.admin.sys.group.service;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:46
 */
public interface GroupRelationService {

    public void appendRelation(Integer groupId, Integer[] organizationIds);

    public void appendRelation(Integer groupId, Integer[] userIds, Integer[] startUserIds, Integer[] endUserIds);

    public List<Integer> findGroupIds(Integer userId, List<Integer> organizationIds);
}
