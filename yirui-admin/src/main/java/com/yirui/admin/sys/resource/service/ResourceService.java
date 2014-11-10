package com.yirui.admin.sys.resource.service;

import com.yirui.admin.sys.auth.service.UserAuthService;
import com.yirui.admin.sys.resource.model.Resource;
import com.yirui.admin.sys.resource.model.tmp.Menu;
import com.yirui.admin.sys.user.model.User;
import com.yirui.common.plugin.service.BaseTreeableService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/9
 * Time: 0:48
 */
@Service
public interface ResourceService {

    /**
     * 得到真实的资源标识  即 父亲:儿子
     * @param resource
     * @return
     */
    public String findActualResourceIdentity(Resource resource);

    public Resource findOne(Integer id);

}
