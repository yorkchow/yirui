package com.yirui.admin.sys.user.service;

import com.yirui.admin.sys.user.model.User;
import com.yirui.admin.sys.user.model.UserStatus;
import com.yirui.admin.sys.user.model.UserStatusHistory;
import com.yirui.common.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 14:38
 */
@Service
public class UserStatusHistoryService extends BaseService<UserStatusHistory, Integer> {

    public void log(User opUser, User user, UserStatus newStatus, String reason) {
        UserStatusHistory history = new UserStatusHistory();
        history.setUser(user);
        history.setOpUser(opUser);
        history.setOpDate(new Date());
        history.setStatus(newStatus);
        history.setReason(reason);
        save(history);
    }

    public UserStatusHistory findLastHistory(final User user) {
        // TODO
        return null;
    }

    public String getLastReason(User user) {
        UserStatusHistory history = findLastHistory(user);
        if (history == null) {
            return "";
        }
        return history.getReason();
    }
}
