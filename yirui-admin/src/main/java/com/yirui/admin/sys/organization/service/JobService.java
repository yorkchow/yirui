package com.yirui.admin.sys.organization.service;

import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/10
 * Time: 23:02
 */
public interface JobService {

    /**
     * 过滤仅获取可显示的数据
     *
     * @param jobIds
     * @param organizationJobIds
     */
    public void filterForCanShow(List<Integer> jobIds, List<Integer[]> organizationJobIds);

    public List<Integer> findAncestorIds(List<Integer> currentIds);
}
