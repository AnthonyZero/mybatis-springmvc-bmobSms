package cn.pingjinsite.framework.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ClassName: EncryptUtil
 * @Description: 加密工具类
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 上午11:25:28
 */
public class EncryptUtil {

    /**
     * @Title: encryptSHA
     * @Description: SHA加密 JDK实现
     * @param data
     * @return String
     * @throws Exception
     */
    public static String encryptSHA(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("SHA");
        byte[] value = md5.digest(data);
        return Hex.encodeHexString(value);
    }

    /**
     * @Title: encryptSHAHex
     * @Description: SHA加密 用Commons Codec实现
     * @param data
     * @return String
     * @throws Exception
     */
    public static String encryptSHAHex(byte[] data) throws Exception {
        return DigestUtils.sha1Hex(data);
    }
}
