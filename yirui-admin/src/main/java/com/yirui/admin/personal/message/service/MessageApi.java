package com.yirui.admin.personal.message.service;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/12
 * Time: 22:09
 */
public interface MessageApi {

    public static final String REPLY_PREFIX = "回复：";
    public static final String FOWRARD_PREFIX = "转发：";
    public static final String FOWRARD_TEMPLATE = "<br/><br/>-----------转发消息------------<br/>发件人:%s<br/>收件人：%s<br/>标题：%s<br/><br/>%s";


}
