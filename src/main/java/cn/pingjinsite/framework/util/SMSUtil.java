package cn.pingjinsite.framework.util;

import cn.pingjinsite.framework.bmob.restapi.Bmob;

/**
 * @ClassName: SMSUtil
 * @Description: 手机短信相关工具
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 上午12:03:39
 */
public class SMSUtil {

    private static final String SMS_TEMPLATE = "APP注册模板";

    private static final String SMS_UPDATEPASSWORD_TEMPLATE = "APP修改密码模板";

    private static final String SMS_CHANGEPHONE_TEMPLATE = "APP更换手机号模板";

    /**
     * @Title: sendSMS
     * @Description: 发送短信
     * @param phone 电话
     */
    public static boolean sendSMS(String phone) {
        // Bmob初始化
        Bmob.initBmob(BmobSmsConfig.getValue("applicationId"), BmobSmsConfig.getValue("restApiKey"));
        String result = Bmob.requestSmsCode(phone, SMS_TEMPLATE);
        if (result.contains("smsId")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Title: sendUpdatePasswordSMS
     * @Description: 忘记密码功能 发送短信
     * @param phone 电话号码
     */
    public static boolean sendUpdatePasswordSMS(String phone) {
        Bmob.initBmob(BmobSmsConfig.getValue("applicationId"), BmobSmsConfig.getValue("restApiKey"));
        String result = Bmob.requestSmsCode(phone, SMS_UPDATEPASSWORD_TEMPLATE);
        if (result.contains("smsId")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Title: sendChangePhoneSMS
     * @Description: 更换手机号功能 发送短信
     * @param phone
     * @return boolean
     */
    public static boolean sendChangePhoneSMS(String phone) {
        Bmob.initBmob(BmobSmsConfig.getValue("applicationId"), BmobSmsConfig.getValue("restApiKey"));
        String result = Bmob.requestSmsCode(phone, SMS_CHANGEPHONE_TEMPLATE);
        if (result.contains("smsId")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Title: verifySmsCode
     * @Description: 验证验证码是否有效；（验证成功一次后再次验证会失败 或者 时间有效期过后验证会失败）
     * @param phone 手机号
     * @param number 验证码
     * @return boolean
     */
    public static boolean verifySmsCode(String phone, String number) {
        Bmob.initBmob(BmobSmsConfig.getValue("applicationId"), BmobSmsConfig.getValue("restApiKey"));
        String result = Bmob.verifySmsCode(phone, number);
        String flag = "{\"msg\":\"ok\"}";
        if (flag.equals(result)) {
            return true;
        } else {
            return false;
        }
    }
}
