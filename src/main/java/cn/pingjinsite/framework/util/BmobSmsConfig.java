package cn.pingjinsite.framework.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName: BmobSmsConfig
 * @Description: 获取Bmob短信配置参数
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 上午12:16:08
 */
public class BmobSmsConfig {

    public BmobSmsConfig() {

    };

    private static Properties props = new Properties();

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sms/bmob.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }

    public static void updateProperties(String key, String value) {
        props.setProperty(key, value);
    }
}
