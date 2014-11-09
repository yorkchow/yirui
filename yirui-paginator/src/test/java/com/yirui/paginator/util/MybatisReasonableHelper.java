package com.yirui.paginator.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/9
 * Time: 0:21
 */
public class MybatisReasonableHelper {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //创建SqlSessionFactory
            Reader reader = Resources.getResourceAsReader(TestUtil.getXmlPath() + "/mybatis-config-reasonable.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
            if (TestUtil.getXmlPath().equalsIgnoreCase("hsqldb")) {
                //创建数据库
                SqlSession session = null;
                try {
                    session = sqlSessionFactory.openSession();
                    Connection conn = session.getConnection();
                    reader = Resources.getResourceAsReader(TestUtil.getXmlPath() + "/" + TestUtil.getXmlPath() + ".sql");
                    ScriptRunner runner = new ScriptRunner(conn);
                    runner.setLogWriter(null);
                    runner.runScript(reader);
                    reader.close();
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Session
     * @return
     */
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
