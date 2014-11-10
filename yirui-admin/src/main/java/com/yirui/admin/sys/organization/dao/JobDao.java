package com.yirui.admin.sys.organization.dao;

import com.yirui.admin.sys.organization.model.Job;
import org.springframework.stereotype.Service;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/11
 * Time: 2:09
 */
@Service
public interface JobDao {

    public Job findOne(Integer id);
}
