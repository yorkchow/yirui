package com.yirui.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>抽象树形DAO层基类 提供树形结构的一些简便方法
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/9
 * Time: 13:51
 */
public interface BaseTreeableDao<M, ID extends Serializable> extends BaseDao<M, ID> {

    public void deleteSelfAndChild(Map params);

    public void updateSelftAndChild(Map params);

    public List<M> findSelfAndNextSiblings(Map params);

    public int findNextWeight(Map params);
}
