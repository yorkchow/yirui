package com.yirui.admin.sys.group.service.impl;

import com.google.common.collect.Lists;
import com.yirui.admin.sys.group.dao.GroupRelationDao;
import com.yirui.admin.sys.group.model.GroupRelation;
import com.yirui.admin.sys.group.service.GroupRelationService;
import com.yirui.common.service.BaseService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:46
 */
@Service("groupRelationService")
public class GroupRelationServiceImpl implements GroupRelationService {

    @Autowired
    private GroupRelationDao groupRelationDao;

    public void appendRelation(Integer groupId, Integer[] organizationIds) {
        if (ArrayUtils.isEmpty(organizationIds)) {
            return;
        }
        for (Integer organizationId : organizationIds) {
            if (organizationId == null) {
                continue;
            }
            GroupRelation r = groupRelationDao.findByGroupIdAndOrganizationId(groupId, organizationId);
            if (r == null) {
                r = new GroupRelation();
                r.setGroupId(groupId);
                r.setOrganizationId(organizationId);
                groupRelationDao.save(r);
            }
        }
    }

    public void appendRelation(Integer groupId, Integer[] userIds, Integer[] startUserIds, Integer[] endUserIds) {
        if (ArrayUtils.isEmpty(userIds) && ArrayUtils.isEmpty(startUserIds)) {
            return;
        }
        if (!ArrayUtils.isEmpty(userIds)) {
            for (Integer userId : userIds) {
                if (userId == null) {
                    continue;
                }
                GroupRelation r = groupRelationDao.findByGroupIdAndUserId(groupId, userId);
                if (r == null) {
                    r = new GroupRelation();
                    r.setGroupId(groupId);
                    r.setUserId(userId);
                    groupRelationDao.save(r);
                }
            }
        }

        if (!ArrayUtils.isEmpty(startUserIds)) {
            for (int i = 0, l = startUserIds.length; i < l; i++) {
                Integer startUserId = startUserIds[i];
                Integer endUserId = endUserIds[i];
                //范围查 如果在指定范围内 就没必要再新增一个 如当前是[10,20] 如果数据库有[9,21]
                GroupRelation r = groupRelationDao.findByGroupIdAndStartUserIdLessThanEqualAndEndUserIdGreaterThanEqual(groupId, startUserId, endUserId);

                if (r == null) {
                    //删除范围内的
                    groupRelationDao.deleteInRange(startUserId, endUserId);
                    r = new GroupRelation();
                    r.setGroupId(groupId);
                    r.setStartUserId(startUserId);
                    r.setEndUserId(endUserId);
                    groupRelationDao.save(r);
                }

            }
        }
    }

    public List<Integer> findGroupIds(Integer userId, List<Integer> organizationIds) {
        if (organizationIds.isEmpty()) {
            return Lists.newArrayList(groupRelationDao.findGroupIds(userId));
        }

        return Lists.newArrayList(groupRelationDao.findGroupIds(userId, organizationIds));
    }
}
