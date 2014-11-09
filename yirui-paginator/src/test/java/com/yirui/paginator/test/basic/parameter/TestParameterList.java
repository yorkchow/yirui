package com.yirui.paginator.test.basic.parameter;

import com.yirui.paginator.Page;
import com.yirui.paginator.PageHelper;
import com.yirui.paginator.mapper.CountryMapper;
import com.yirui.paginator.model.Country;
import com.yirui.paginator.util.MybatisHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestParameterList {

    /**
     * 使用Mapper接口调用时，使用PageHelper.startPage效果更好，不需要添加Mapper接口参数
     */
    @Test
    public void testMapperWithStartPage() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        try {
            //获取第1页，10条内容，默认查询总数count
            List<Integer> paList = new ArrayList<Integer>();
            paList.add(1);
            paList.add(2);
            PageHelper.startPage(1, 10);
            List<Country> list = countryMapper.selectAllOrderByList(paList);
            assertEquals(3, list.get(0).getId());
            assertEquals(10, list.size());
            assertEquals(181, ((Page) list).getTotal());
        } finally {
            sqlSession.close();
        }
    }
}
