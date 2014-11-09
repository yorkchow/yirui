package com.yirui.paginator.test.reasonable;

import com.yirui.paginator.PageHelper;
import com.yirui.paginator.PageInfo;
import com.yirui.paginator.mapper.CountryMapper;
import com.yirui.paginator.model.Country;
import com.yirui.paginator.util.MybatisReasonableHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PageTest {
    /**
     * 使用Mapper接口调用时，使用PageHelper.startPage效果更好，不需要添加Mapper接口参数
     */
    @Test
    public void testMapperWithStartPage() {
        SqlSession sqlSession = MybatisReasonableHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        try {
            //获取第20页，2条内容
            //分页插件会自动改为查询最后一页
            PageHelper.startPage(20, 50);
            List<Country> list = countryMapper.selectAll();
            PageInfo page = new PageInfo(list);
            assertEquals(33, list.size());
            assertEquals(151, page.getStartRow());
            assertEquals(4, page.getPageNum());
            assertEquals(183, page.getTotal());

            //获取第-3页，2条内容
            //由于只有7天数据，分页插件会自动改为查询最后一页
            PageHelper.startPage(-3, 50);
            list = countryMapper.selectAll();
            page = new PageInfo(list);
            assertEquals(50, list.size());
            assertEquals(1, page.getStartRow());
            assertEquals(1, page.getPageNum());
            assertEquals(183, page.getTotal());
        } finally {
            sqlSession.close();
        }
    }
}
