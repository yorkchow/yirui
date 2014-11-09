package com.yirui.admin.sys.resource.model;

import com.yirui.common.model.BaseTreeModel;
import com.yirui.common.model.support.annotation.TableName;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/9
 * Time: 0:42
 */
@TableName(value = "sys_resource")
public class Resource extends BaseTreeModel<Integer> {

    /**
     * 资源标识符 用于权限匹配的 如sys:resource
     */
    private String identity;

    /**
     * 点击后前往的地址
     * 菜单才有
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTreetableIds() {
        String selfId = makeSelfAsNewParentIds().replace("/", "-");
        return selfId.substring(0, selfId.length() - 1);
    }

    public String getTreetableParentIds() {
        String parentIds = getParentIds().replace("/", "-");
        return parentIds.substring(0, parentIds.length() - 1);
    }

}
