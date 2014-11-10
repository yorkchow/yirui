package com.yirui.admin.sys.user.service;

import com.yirui.admin.sys.user.model.User;
import com.yirui.admin.sys.user.model.UserStatus;
import com.yirui.admin.sys.user.model.UserStatusHistory;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 14:38
 */
public interface UserStatusHistoryService {

    public void log(User opUser, User user, UserStatus newStatus, String reason);

    public UserStatusHistory findLastHistory(final User user);

    public String getLastReason(User user);
}
