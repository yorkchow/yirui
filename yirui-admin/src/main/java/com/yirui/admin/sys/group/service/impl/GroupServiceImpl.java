package com.yirui.admin.sys.group.service.impl;

import com.google.common.collect.Lists;
import com.yirui.admin.sys.group.dao.GroupDao;
import com.yirui.admin.sys.group.model.Group;
import com.yirui.admin.sys.group.service.GroupRelationService;
import com.yirui.admin.sys.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 16:59
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GroupRelationService groupRelationService;

    /*public Set<Map<String, Object>> findIdAndNames(Searchable searchable, String groupName) {

        searchable.addSearchFilter("name", SearchOperator.like, groupName);

        return Sets.newHashSet(
                Lists.transform(
                        findAll(searchable).getContent(),
                        new Function<Group, Map<String, Object>>() {
                            @Override
                            public Map<String, Object> apply(Group input) {
                                Map<String, Object> data = Maps.newHashMap();
                                data.put("label", input.getName());
                                data.put("value", input.getId());
                                return data;
                            }
                        }
                )
        );
    }*/

    /**
     * 获取可用的的分组编号列表
     *
     * @param userId
     * @param organizationIds
     * @return
     */
    public List<Integer> findShowGroupIds(Integer userId, List<Integer> organizationIds) {
        List<Integer> groupIds = Lists.newArrayList();
        groupIds.addAll(groupDao.findDefaultGroupIds());
        groupIds.addAll(groupRelationService.findGroupIds(userId, organizationIds));


        //TODO 如果分组数量很多 建议此处查询时直接带着是否可用的标识去查
        for (Group group : groupDao.findAll()) {
            if (Boolean.FALSE.equals(group.getShow())) {
                groupIds.remove(group.getId());
            }
        }

        return groupIds;
    }

    @Override
    public Group findOne(Integer id) {
        return groupDao.findOne(id);
    }
}
