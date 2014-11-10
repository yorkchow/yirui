package com.yirui.admin.sys.organization.dao;

import com.yirui.admin.sys.organization.model.Organization;
import org.springframework.stereotype.Service;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/11
 * Time: 2:12
 */
@Service
public interface OrganizationDao {

    public Organization findOne(Integer id);
}
