package com.yirui.paginator.test.basic.dynamic;

import com.yirui.paginator.Page;
import com.yirui.paginator.PageHelper;
import com.yirui.paginator.mapper.CountryMapper;
import com.yirui.paginator.model.Country;
import com.yirui.paginator.util.MybatisHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 23:54
 */
public class TestDynamicIf {

    /**
     * 使用Mapper接口调用时，使用PageHelper.startPage效果更好，不需要添加Mapper接口参数
     */
    @Test
    public void testMapperWithStartPage() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        try {
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(1, 10);
            List<Country> list = countryMapper.selectIf(1);
            assertEquals(2, list.get(0).getId());
            assertEquals(10, list.size());
            assertEquals(182, ((Page) list).getTotal());

            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(1, 10);
            list = countryMapper.selectIf(null);
            assertEquals(1, list.get(0).getId());
            assertEquals(10, list.size());
            assertEquals(183, ((Page) list).getTotal());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 单个POJO参数情况特殊
     */
    @Test
    public void testMapper() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        try {
            Map map = new HashMap();

            Country country = new Country();
            country.setId(1);
            map.put("country", country);
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(1, 10);
            List<Country> list = countryMapper.selectIf3(country);
            assertEquals(2, list.get(0).getId());
            assertEquals(10, list.size());
            assertEquals(182, ((Page) list).getTotal());
        } finally {
            sqlSession.close();
        }
    }
}
