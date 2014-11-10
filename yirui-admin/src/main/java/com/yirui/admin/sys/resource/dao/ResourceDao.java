package com.yirui.admin.sys.resource.dao;

import com.yirui.admin.sys.resource.model.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/11
 * Time: 1:54
 */
@Service
public interface ResourceDao {

    public Resource findOne(Integer id);

    public List<Resource> findAll();
}
