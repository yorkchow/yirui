package com.yirui.admin.sys.organization.service.impl;

import com.google.common.collect.Lists;
import com.yirui.admin.sys.organization.dao.OrganizationDao;
import com.yirui.admin.sys.organization.model.Organization;
import com.yirui.admin.sys.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 22:51
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;
    /**
     * 过滤仅获取可显示的数据
     *
     * @param organizationIds
     * @param organizationJobIds
     */
    public void filterForCanShow(List<Integer> organizationIds, List<Integer[]> organizationJobIds) {

        Iterator<Integer> iter1 = organizationIds.iterator();

        while (iter1.hasNext()) {
            Integer id = iter1.next();
            Organization o = organizationDao.findOne(id);
            if (o == null || Boolean.FALSE.equals(o.getShow())) {
                iter1.remove();
            }
        }

        Iterator<Integer[]> iter2 = organizationJobIds.iterator();

        while (iter2.hasNext()) {
            Integer id = iter2.next()[0];
            Organization o = organizationDao.findOne(id);
            if (o == null || Boolean.FALSE.equals(o.getShow())) {
                iter2.remove();
            }
        }

    }

    @Override
    public List<Integer> findAncestorIds(List<Integer> currentIds) {
        List<Integer> parents = Lists.newArrayList();
        for (Integer currentId : currentIds) {
            parents.addAll(findAncestorIds(currentId));
        }
        return parents;
    }

    public List<Integer> findAncestorIds(Integer currentId) {
        List<Integer> ids = Lists.newArrayList();
        Organization m = organizationDao.findOne(currentId);
        if (m == null) {
            return ids;
        }
        for (String idStr : StringUtils.tokenizeToStringArray(m.getParentIds(), "/")) {
            if (!StringUtils.isEmpty(idStr)) {
                ids.add(Integer.valueOf(idStr));
            }
        }
        return ids;
    }
}
