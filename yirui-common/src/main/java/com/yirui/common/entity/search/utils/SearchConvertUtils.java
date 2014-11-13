package com.yirui.common.entity.search.utils;

import com.yirui.common.entity.search.SearchOperator;
import org.apache.commons.lang3.StringUtils;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/14
 * Time: 1:03
 */
public final class SearchConvertUtils {

    public static String getConvertValue(String search, String condition) {
        if (!search.contains(",")) {
            return search;
        }
        String searchValue = "";
        String[] searchParams = search.split(",");
        for (String searchParam : searchParams) {
            String[] param = searchParam.split("=");
            String[] keys = param[0].split("_");
            String key = keys[0];
            String op = keys[1];
            String value = StringUtils.stripToEmpty(param[1]);
            String symbol = SearchOperator.valueOf(op).getSymbol();
            searchValue += key + symbol + value + condition;
        }
        searchValue = searchValue.substring(0, searchValue.length() - condition.length());
        return searchValue;
    }
}
