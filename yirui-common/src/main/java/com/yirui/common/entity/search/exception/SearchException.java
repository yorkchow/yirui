package com.yirui.common.entity.search.exception;

import org.springframework.core.NestedRuntimeException;
/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/14
 * Time: 0:07
 */
public class SearchException extends NestedRuntimeException {

    public SearchException(String msg) {
        super(msg);
    }

    public SearchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
