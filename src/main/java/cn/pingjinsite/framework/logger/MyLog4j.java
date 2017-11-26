package cn.pingjinsite.framework.logger;

import org.apache.log4j.Logger;

/**
 * @ClassName: MyLog4j
 * @Description: 自定义日志输出类
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月13日 下午4:08:03
 */
public class MyLog4j {

    /**
     * @Title: error
     * @Description: 输出ERROR日志
     * @param c 当前输出日志信息的类
     * @param message 当前输出日志信息的自定义描述
     * @param showExcepton 是否输出抛出的异常信息
     * @param ex void
     */
    public static void error(Class<?> c, Object message, boolean showExcepton, Throwable ex) {
        Logger logger = Logger.getLogger("");
        logger.error(c.getName() + ":" + message, ex);
        if (showExcepton) {
            ex.printStackTrace();
        }
    }

    /**
     * @Title: info
     * @Description: 输出INFO日志
     * @param c 当前输出日志信息的类
     * @param message void 当前输出日志信息的自定义描述
     */
    public static void info(Class<?> c, Object message) {
        Logger logger = Logger.getLogger("");
        logger.info(c.getName() + ":" + message);
    }
}
