package com.yirui.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>抽象DAO层基类 提供一些简便方法
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 10:48
 */
public interface BaseDao<M, ID extends Serializable> {

    /**
     * 保存单个实体
     * @param m
     * @return 返回保存的实体
     */
    public M save(M m);

    /**
     * 更新单个实体
     *
     * @param m 实体
     * @return 返回更新的实体
     */
    public M update(M m);

    /**
     * 删除实体
     *
     * @param m 实体
     */
    public void delete(M m);
    /**
     * 根据主键删除
     *
     * @param id
     */
    public void delete(ID id);

    /**
     * 查询所有实体
     *
     * @return
     */
    public List<M> findAll();

    /**
     * 查询所有排序后的实体
     * @param sort
     * @return
     */
    public List<M> findAllAndSort(Map<String, Object> sort);

    /**
     * 根据条件查询所有
     * 条件 + 排序
     *
     * @param search
     * @return
     */
    public List<M> findAllBySearch(Map<String, Object> search);

    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    public M findOne(ID id);

    /**
     * 实体是否存在
     *
     * @param id 主键
     * @return 存在 返回true，否则false
     */
    public boolean exists(ID id);

    /**
     * 统计实体总数
     *
     * @return 实体总数
     */
    public long count();


}
