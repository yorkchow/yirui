package com.yirui.paginator.sql;

import com.yirui.paginator.SqlUtil;
import net.sf.jsqlparser.JSQLParserException;
import org.junit.Test;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 22:55
 */
public class SqlTest {

    @Test
    public void testSqlTest() throws JSQLParserException {
        String originalSql = "Select * from sys_user o where abc = ? order by id desc , name asc";
        //SqlUtil.testSql("mysql", originalSql);
        SqlUtil.testSql("hsqldb", originalSql);
        //SqlUtil.testSql("oracle", originalSql);
        //SqlUtil.testSql("postgresql", originalSql);
    }
}
