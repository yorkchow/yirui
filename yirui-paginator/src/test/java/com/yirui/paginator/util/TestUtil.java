package com.yirui.paginator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author YorkChow<york.chow@actionsky.com>
 * @since 2014/11/8
 * Time: 22:59
 */
public class TestUtil {
    public static String XML_PATH;

    public synchronized static String getXmlPath() throws IOException {
        if (XML_PATH != null) {
            return XML_PATH;
        }
        Properties properties = new Properties();
        InputStream is = null;
        try {
            is = TestUtil.class.getResourceAsStream("/test.properties");
            properties.load(is);
            XML_PATH = properties.getProperty("database");
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return XML_PATH;
    }
}
