package com.yirui.admin.sys.user.model;

import com.yirui.common.model.BaseModel;
import com.yirui.common.model.support.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 14:39
 */
//@Table(name = "sys_user_status_history")
@TableName(value = "sys_user_status_history")
public class UserStatusHistory extends BaseModel<Integer> {

    /**
     * 锁定的用户
     */
    private User user;

    /**
     * 备注信息
     */
    private String reason;

    /**
     * 操作的状态
     */
    private UserStatus status;

    /**
     * 操作的管理员
     */
    private User opUser;

    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date opDate;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getOpUser() {
        return opUser;
    }

    public void setOpUser(User opUser) {
        this.opUser = opUser;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }
}
