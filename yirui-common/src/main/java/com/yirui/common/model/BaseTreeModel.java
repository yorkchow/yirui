package com.yirui.common.model;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * <p> 抽象树形基类，提供统一的属性
 *
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/9
 * Time: 16:09
 */
public class BaseTreeModel<ID extends Serializable> extends BaseModel<ID> {

    /**
     * 标题
     */
    private String name;

    /**
     * 父ID
     * @Column(name = "parent_id")
     */
    private ID parentId;

    /**
     * 父路径
     * @Column(name = "parent_ids")
     */
    private String parentIds;

    private Integer weight;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否有叶子节点
     * @Formula(value = "(select count(*) from {treealbe_talbe} f_t where f_t.parent_id = id)")
     */
    private boolean hasChildren;

    /**
     * 是否显示
     * @Column(name = "is_show")
     */
    private Boolean show = Boolean.FALSE;

    public String getSeparator() {
        return "/";
    }

    public String makeSelfAsNewParentIds() {
        return getParentIds() + getId() + getSeparator();
    }

    public String getName() {
        return name;
    }

    public boolean isRoot() {
        if (getParentId() != null && ((Integer) getParentId()) == 0) {
            return true;
        }
        return false;
    }

    public boolean isLeaf() {
        if (isRoot()) {
            return false;
        }
        if (isHasChildren()) {
            return false;
        }

        return true;
    }

    /**
     * 根节点默认图标 如果没有默认 空即可
     *
     * @return
     */
    public String getRootDefaultIcon() {
        return "ztree_root_open";
    }

    /**
     * 树枝节点默认图标 如果没有默认 空即可
     *
     * @return
     */
    public String getBranchDefaultIcon() {
        return "ztree_branch";
    }

    /**
     * 树叶节点默认图标 如果没有默认 空即可
     *
     * @return
     */
    public String getLeafDefaultIcon() {
        return "ztree_leaf";
    }

    public void setName(String name) {
        this.name = name;
    }

    public ID getParentId() {
        return parentId;
    }

    public void setParentId(ID parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getIcon() {
        if (!StringUtils.isEmpty(icon)) {
            return icon;
        }
        if (isRoot()) {
            return getRootDefaultIcon();
        }
        if (isLeaf()) {
            return getLeafDefaultIcon();
        }
        return getBranchDefaultIcon();
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
