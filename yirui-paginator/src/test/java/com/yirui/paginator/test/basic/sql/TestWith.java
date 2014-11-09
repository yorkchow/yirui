package com.yirui.paginator.test.basic.sql;

import com.yirui.paginator.Page;
import com.yirui.paginator.PageHelper;
import com.yirui.paginator.mapper.CountryMapper;
import com.yirui.paginator.model.Country;
import com.yirui.paginator.util.MybatisHelper;
import com.yirui.paginator.util.TestUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestWith {

    /**
     * with的count查询sql特殊 - 只测试oracle,别的可能不支持
     */
    @Test
    public void testUnion() throws Exception {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        //只测试oracle
        if (!TestUtil.getXmlPath().equalsIgnoreCase("oracle")) {
            return;
        }
        try {
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(1, 10);
            List<Country> list = countryMapper.selectWith();
            assertEquals(151, list.get(0).getId());
            assertEquals(10, list.size());
            assertEquals(33, ((Page) list).getTotal());

            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(2, 10);
            list = countryMapper.selectWith();
            assertEquals(161, list.get(0).getId());
            assertEquals(10, list.size());
            assertEquals(33, ((Page) list).getTotal());
        } finally {
            sqlSession.close();
        }
    }
}
