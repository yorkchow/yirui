package com.yirui.common.service;

import com.yirui.common.dao.BaseDao;
import com.yirui.common.model.AbstractModel;
import com.yirui.common.model.support.annotation.TableName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>抽象service层基类 提供一些简便方法
 * <p/>
 * <p>泛型 ： M 表示实体类型；ID表示主键类型
 * <p/>
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 10:43
 */
public abstract class BaseService<M extends AbstractModel, ID extends Serializable> {

    protected BaseDao<M, ID> baseDao;

    /*@Autowired
    public void setBaseDao(BaseDao<M, ID> baseDao) {
        this.baseDao = baseDao;
    }*/
    /**
     * 保存单个实体
     *
     * @param m 实体
     * @return 返回保存的实体
     */
    public M save(M m) {
        return baseDao.save(m);
    }

    /**
     * 更新单个实体
     *
     * @param m 实体
     * @return 返回更新的实体
     */
    public M update(M m) {
        return baseDao.update(m);
    }

    /**
     * 删除实体
     *
     * @param m 实体
     */
    public void delete(M m) {
        baseDao.delete(m);
    }

    /**
     * 根据主键删除相应实体
     *
     * @param id 主键
     */
    public void delete(ID id) {
        baseDao.delete(id);
    }

    /**
     * 批量删除相应实体
     * @param id
     */
    public void delete(ID[] ids) {
        for (ID id : ids) {
            baseDao.delete(id);
        }
    }

    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    public M findOne(ID id) {
        return baseDao.findOne(id);
    }

    /**
     * 实体是否存在
     *
     * @param id 主键
     * @return 存在 返回true，否则false
     */
    public boolean exists(ID id) {
        return baseDao.exists(id);
    }

    /**
     * 统计实体总数
     *
     * @return 实体总数
     */
    public long count() {
        return baseDao.count();
    }

    /**
     * 查询所有实体
     *
     * @return
     */
    public List<M> findAll() {
        return baseDao.findAll();
    }

    /**
     * 按照顺序查询所有实体
     *
     * @param sort
     * @return
     */
    public List<M> findAllAndSort(Map<String, Object> sort) {
        return baseDao.findAllAndSort(sort);
    }

    /**
     * 按照搜索条件查询所有实体
     * @param search
     * @return
     */
    public List<M> findAllBySearch(Map<String, Object> search) {
        return baseDao.findAllBySearch(search);
    }

    /**
     * 获取实体类的表名
     *
     * @return
     */
    public String getTableName(Class<?> entityClass) {
        TableName tableNameAnnotation = AnnotationUtils.findAnnotation(entityClass, TableName.class);
        return tableNameAnnotation != null ? tableNameAnnotation.value() : null;
    }
}
