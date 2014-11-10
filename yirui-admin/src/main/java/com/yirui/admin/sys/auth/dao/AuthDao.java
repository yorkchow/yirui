package com.yirui.admin.sys.auth.dao;

import com.yirui.admin.sys.auth.model.Auth;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:10
 */
@Service
public interface AuthDao {

    public int save(Auth auth);

    public Auth findByUserId(Integer userId);

    public Auth findByGroupId(Integer groupId);

    public Auth findByOrganizationIdAndJobId(Integer organizationId, Integer jobId);

    public List<Integer> findRoleIds(Integer userId, List<Integer> groupIds, List<Integer> organizationIds,
                              List<Integer> jobIds, List<Integer[]> organizationJobIds);
}
