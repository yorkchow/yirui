package com.yirui.common.model.exception;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/9
 * Time: 17:26
 */
public class ModelTableNameNotNullException extends ModelException {

    public ModelTableNameNotNullException(Object[] tableName) {
        super("model.table.name,not.null", tableName);
    }
}
