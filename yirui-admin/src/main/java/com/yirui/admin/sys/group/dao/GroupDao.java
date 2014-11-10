package com.yirui.admin.sys.group.dao;

import com.yirui.admin.sys.group.model.Group;
import com.yirui.common.dao.BaseDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 17:05
 */
@Service
public interface GroupDao {

    public Group findOne(Integer id);

    public List<Group> findAll();

    public List<Integer> findDefaultGroupIds();
}
