package com.yirui.common.model;

import java.io.Serializable;

/**
 * <p> 抽象实体基类，提供统一的ID，和相关的基本功能方法
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/7
 * Time: 21:01
 */
public abstract class BaseModel<ID extends Serializable> extends AbstractModel<ID> {

    private ID id;

    @Override
    public ID getId() {
        return null;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }
}
