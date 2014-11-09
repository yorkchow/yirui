package com.yirui.common.model.support.annotation;

import java.lang.annotation.*;

/**
 * 自定义表名注解，为了动态生成sql
 *
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/9
 * Time: 15:48
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {

    String value();
}
