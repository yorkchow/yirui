package com.yirui.paginator.test.pagesize;

import com.yirui.paginator.PageHelper;
import com.yirui.paginator.PageInfo;
import com.yirui.paginator.mapper.CountryMapper;
import com.yirui.paginator.model.Country;
import com.yirui.paginator.util.MybatisHelper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PageSizeLessThenOrEqualZeroTest {

    /**
     * 使用Mapper接口调用时，使用PageHelper.startPage效果更好，不需要添加Mapper接口参数
     */
    @Test
    public void testWithStartPage() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        try {
            //pageSize=0,这时候相当于用分页插件求count
            PageHelper.startPage(1, 0);
            List<Country> list = countryMapper.selectAll();
            PageInfo page = new PageInfo(list);
            assertEquals(0, list.size());
            assertEquals(183, page.getTotal());

            //limit<0的时候同上
            PageHelper.startPage(1, -100);
            list = countryMapper.selectAll();
            page = new PageInfo(list);
            assertEquals(0, list.size());
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
        //注意这里是MybatisRowBoundsHelper，会求count
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        try {
            //limit=0,这时候相当于用分页插件求count,但是前提必须是配置rowbounds方式求count，否则都是-1
            List<Country> list = countryMapper.selectAll(new RowBounds(1, 0));
            PageInfo page = new PageInfo(list);
            assertEquals(0, list.size());
            assertEquals(-1, page.getTotal());

            //limit<0的时候同上
            list = countryMapper.selectAll(new RowBounds(1, -100));
            page = new PageInfo(list);
            assertEquals(0, list.size());
            assertEquals(-1, page.getTotal());
        } finally {
            sqlSession.close();
        }
    }
}
