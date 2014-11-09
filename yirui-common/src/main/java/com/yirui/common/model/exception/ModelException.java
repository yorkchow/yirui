package com.yirui.common.model.exception;

import com.yirui.common.exception.BaseException;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/9
 * Time: 17:23
 */
public class ModelException extends BaseException {

    public ModelException(String code, Object[] args) {
        super("model", code, args, null);
    }
}
