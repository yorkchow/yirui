package com.yirui.admin.sys.user.dao;

import com.yirui.admin.sys.user.model.User;
import com.yirui.common.dao.BaseDao;
import org.springframework.stereotype.Service;


/**
 * UserDao equals mybatis' model mapper
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 11:21
 */
@Service
public interface UserDao {

    public User findByUsername(String username);

    public User findByEmail(String email);

    public User findByMobile(String mobile);

    public User findOne(Integer id);

}
