package com.yirui.admin.sys.user.dao;

import com.yirui.admin.sys.user.model.UserStatusHistory;
import org.springframework.stereotype.Service;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/11
 * Time: 1:45
 */
@Service
public interface UserStatusHistoryDao {

    public int save(UserStatusHistory userStatusHistory);
}
