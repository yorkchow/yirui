package com.yirui.common;

import com.yirui.common.entity.search.SearchOperator;
import org.apache.commons.lang3.StringUtils;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/14
 * Time: 0:09
 */
public class Main {

    public static void main(String[] args) {
        String searchValue = "";
        String condition = " or ";
        String[] searchParams = new String[]{"id_gte=0", "admin_eq=0"};
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
        System.out.println(searchValue);
    }
}
