package com.yirui.paginator.test.basic.cache;

import com.yirui.paginator.PageHelper;
import com.yirui.paginator.mapper.CountryMapper;
import com.yirui.paginator.model.Country;
import com.yirui.paginator.util.MybatisHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 针对将ms缓存后的测试
 *
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 23:44
 */
public class CacheTest {

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
            List<Country> list = countryMapper.selectGreterThanId(10);
            assertEquals(10, list.size());
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(2, 10);
            list = countryMapper.selectGreterThanIdAndNotEquelContryname(10, "china");
            assertEquals(10, list.size());
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(3, 10);
            list = countryMapper.selectGreterThanIdAndNotEquelContryname(10, "china");
            assertEquals(10, list.size());
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(4, 10);
            list = countryMapper.selectGreterThanIdAndNotEquelContryname(10, "china");
            assertEquals(10, list.size());
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(5, 10);
            list = countryMapper.selectGreterThanIdAndNotEquelContryname(10, "china");
            assertEquals(10, list.size());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 使用Mapper接口调用时，使用PageHelper.startPage效果更好，不需要添加Mapper接口参数
     */
    @Test
    public void testThreads() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        sqlSession.close();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread1 = new Thread(new CacheThread());
        Thread thread2 = new Thread(new CacheThread());
        Thread thread3 = new Thread(new CacheThread());
        Thread thread4 = new Thread(new CacheThread());
        Thread thread5 = new Thread(new CacheThread());
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class CacheThread implements Runnable {
        private CacheThread() {
        }

        public void run() {
            SqlSession sqlSession = MybatisHelper.getSqlSession();
            System.out.println(Thread.currentThread().getId() + "开始运行...");
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(1, 10);
            List<Country> list = countryMapper.selectGreterThanIdAndNotEquelContryname(10, "china");
            assertEquals(10, list.size());
            //获取第2页，10条内容，默认查询总数count
            PageHelper.startPage(2, 10);
            list = countryMapper.selectGreterThanIdAndNotEquelContryname(10, "china");
            assertEquals(10, list.size());
            sqlSession.close();
        }
    }
}
