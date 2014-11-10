package com.yirui.admin.sys.group.dao;

import com.yirui.admin.sys.group.model.GroupRelation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:48
 */
@Service
public interface GroupRelationDao {

    public int save(GroupRelation groupRelation);

    public GroupRelation findByGroupIdAndUserId(Integer groupId, Integer userId);

    /**
     * 范围查 如果在指定范围内 就没必要再新增一个 如当前是[10,20] 如果数据库有[9,21] 10<=9 and 21>=20
     *
     * @param groupId
     * @param startUserId
     * @param endUserId
     * @return
     */
    public GroupRelation findByGroupIdAndStartUserIdLessThanEqualAndEndUserIdGreaterThanEqual(Integer groupId, Integer startUserId, Integer endUserId);

    /**
     * 删除区间内的数据 因为之前已经有一个区间包含它们了
     *
     * @param startUserId
     * @param endUserId
     */
    //@Delete("delete from GroupRelation where (startUserId>=?1 and endUserId<=?2) or (userId>=?1 and userId<=?2)")
    public void deleteInRange(Integer startUserId, Integer endUserId);

    public GroupRelation findByGroupIdAndOrganizationId(Integer groupId, Integer organizationId);

    //@Select("select groupId from GroupRelation where userId=?1 or (startUserId<=?1 and endUserId>=?1))")
    public List<Integer> findGroupIds(Integer userId);

    //@Select("select groupId from GroupRelation where userId=?1 or (startUserId<=?1 and endUserId>=?1) or (organizationId in (?2))")
    public List<Integer> findGroupIds(Integer userId, List<Integer> organizationIds);

    /*无需删除用户 因为用户并不逻辑删除
    @Delete("delete from GroupRelation r where " +
            "not exists (select 1 from Group g where r.groupId = g.id) or " +
            "not exists(select 1 from Organization o where r.organizationId = o.id)")
    */
    public void clearDeletedGroupRelation();
}
