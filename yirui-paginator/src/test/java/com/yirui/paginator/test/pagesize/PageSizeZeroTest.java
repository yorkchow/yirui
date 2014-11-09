package com.yirui.paginator.test.pagesize;

import com.yirui.paginator.PageHelper;
import com.yirui.paginator.PageInfo;
import com.yirui.paginator.mapper.CountryMapper;
import com.yirui.paginator.model.Country;
import com.yirui.paginator.util.MybatisPageSizeZeroHelper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PageSizeZeroTest {

    /**
     * 使用Mapper接口调用时，使用PageHelper.startPage效果更好，不需要添加Mapper接口参数
     */
    @Test
    public void testWithStartPage() {
        SqlSession sqlSession = MybatisPageSizeZeroHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        try {
            //pageSize=0的时候查询全部结果
            PageHelper.startPage(1, 0);
            List<Country> list = countryMapper.selectAll();
            PageInfo page = new PageInfo(list);
            assertEquals(183, list.size());
            assertEquals(183, page.getTotal());

            //pageSize=0的时候查询全部结果
            PageHelper.startPage(10, 0);
            list = countryMapper.selectAll();
            page = new PageInfo(list);
            assertEquals(183, list.size());
            assertEquals(183, page.getTotal());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 使用Mapper接口调用时，使用PageHelper.startPage效果更好，不需要添加Mapper接口参数
     */
    @Test
    public void testWithRowbounds() {
        SqlSession sqlSession = MybatisPageSizeZeroHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        try {
            //pageSize=0的时候查询全部结果
            List<Country> list = countryMapper.selectAll(new RowBounds(1, 0));
            PageInfo page = new PageInfo(list);
            assertEquals(183, list.size());
            assertEquals(183, page.getTotal());

            //pageSize=0的时候查询全部结果
            PageHelper.startPage(10, 0);
            list = countryMapper.selectAll(new RowBounds(1000, 0));
            page = new PageInfo(list);
            assertEquals(183, list.size());
            assertEquals(183, page.getTotal());
        } finally {
            sqlSession.close();
        }
    }
}
