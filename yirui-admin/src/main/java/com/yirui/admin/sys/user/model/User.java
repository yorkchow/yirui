package com.yirui.admin.sys.user.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yirui.common.model.BaseModel;
import com.yirui.common.model.support.annotation.TableName;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.beans.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Model User class
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/7
 * Time: 20:52
 */
// @Table(name = "sys_user", catalog = "yirui_db")
@TableName(value = "sys_user")
public class User extends BaseModel<Integer> {

    private static final long serialVersionUID = -8586176696947785315L;
    public static final String USERNAME_PATTERN = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{1,19}$";
    public static final String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
    public static final String MOBILE_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";
    public static final int USERNAME_MIN_LENGTH = 4;
    public static final int USERNAME_MAX_LENGTH = 20;
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 50;

    @NotNull(message = "{not.null}")
    @Pattern(regexp = USERNAME_PATTERN, message = "{user.username.not.valid}")
    @Length(min = USERNAME_MIN_LENGTH, max = USERNAME_MAX_LENGTH, message = "{user.username.not.valid}")
    private String username;

    @NotEmpty(message = "{not.null}")
    @Pattern(regexp = EMAIL_PATTERN, message = "{user.email.not.valid}")
    private String email;

    @NotEmpty(message = "{not.null}")
    @Pattern(regexp = MOBILE_PATTERN, message = "{user.mobile.phone.number.not.valid}")
    private String mobile;

    @Length(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = "{user.password.not.valid}")
    private String password;

    /**
     * 加密密码时使用的种子
     */
    private String salt;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 系统用户的状态
     */
    private UserStatus status = UserStatus.normal;

    /**
     * 是否是管理员
     */
    private Boolean admin = Boolean.FALSE;

    /**
     * 用户 组织机构 工作职务关联表
     */
    private List<UserOrganizationJob> organizationJobs;


    public User() {
    }

    public User(Integer id) {
        setId(id);
    }

    public List<UserOrganizationJob> getOrganizationJobs() {
        if (organizationJobs == null) {
            organizationJobs = Lists.newArrayList();
        }
        return organizationJobs;
    }

    public void addOrganizationJob(UserOrganizationJob userOrganizationJob) {
        userOrganizationJob.setUser(this);
        getOrganizationJobs().add(userOrganizationJob);
    }

    public void setOrganizationJobs(List<UserOrganizationJob> organizationJobs) {
        this.organizationJobs = organizationJobs;
    }

    private transient Map<Integer, List<UserOrganizationJob>> organizationJobsMap;

    @Transient
    public Map<Integer, List<UserOrganizationJob>> getDisplayOrganizationJobs() {
        if (organizationJobsMap != null) {
            return organizationJobsMap;
        }

        organizationJobsMap = Maps.newHashMap();

        for (UserOrganizationJob userOrganizationJob : getOrganizationJobs()) {
            Integer organizationId = userOrganizationJob.getOrganizationId();
            List<UserOrganizationJob> userOrganizationJobList = organizationJobsMap.get(organizationId);
            if (userOrganizationJobList == null) {
                userOrganizationJobList = Lists.newArrayList();
                organizationJobsMap.put(organizationId, userOrganizationJobList);
            }
            userOrganizationJobList.add(userOrganizationJob);
        }
        return organizationJobsMap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 生成新的种子
     */
    public void randomSalt() {
        setSalt(RandomStringUtils.randomAlphanumeric(10));
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobilePhoneNumber(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
