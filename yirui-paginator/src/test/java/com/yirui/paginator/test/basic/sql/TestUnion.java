package com.yirui.paginator.test.basic.sql;

import com.yirui.paginator.Page;
import com.yirui.paginator.PageHelper;
import com.yirui.paginator.mapper.CountryMapper;
import com.yirui.paginator.model.Country;
import com.yirui.paginator.util.MybatisHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestUnion {

    /**
     * union的count查询sql特殊
     */
    @Test
    public void testUnion() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        try {
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(1, 10);
            List<Country> list = countryMapper.selectUnion();
            assertEquals(1, list.get(0).getId());
            assertEquals(10, list.size());
            assertEquals(13, ((Page) list).getTotal());

            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(2, 10);
            list = countryMapper.selectUnion();
            assertEquals(181, list.get(0).getId());
            assertEquals(3, list.size());
            assertEquals(13, ((Page) list).getTotal());
        } finally {
            sqlSession.close();
        }
    }
}
