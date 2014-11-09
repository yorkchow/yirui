package com.yirui.admin.sys.user.dao;

import com.yirui.admin.sys.user.model.User;
import com.yirui.common.dao.BaseDao;


/**
 * UserDao equals mybatis' model mapper
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 11:21
 */
public interface UserDao extends BaseDao<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByMobile(String mobile);

}
