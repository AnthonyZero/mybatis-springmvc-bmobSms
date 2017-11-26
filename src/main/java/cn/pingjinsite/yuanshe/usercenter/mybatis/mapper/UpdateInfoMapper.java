package cn.pingjinsite.yuanshe.usercenter.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: UpdateInfoMapper
 * @Description: 修改个人信息
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月19日 下午10:00:33
 */
public interface UpdateInfoMapper {

    /**
     * @Title: updateHeadImg
     * @Description: 修改头像
     * @param userId 用户唯一标识
     * @param url 图片地址
     */
    public void updateHeadImg(@Param("userId") int userId, @Param("url") String url);

    /**
     * @Title: updateUserInfo
     * @Description: 修改用户信息
     * @param userId 用户唯一标识
     * @param key 键 用于判断修改哪个属性
     * @param value 值
     */
    public void updateUserInfo(@Param("userId") int userId, @Param("key") String key, @Param("value") String value);

    /**
     * @Title: updatePassword
     * @Description: 修改用户密文密码
     * @param userId
     * @param password void
     */
    public void updatePassword(@Param("userId") int userId, @Param("password") String password);

    /**
     * @Title: updatePasswordText
     * @Description: 修改用户明文密码
     * @param userId
     * @param text void
     */
    public void updatePasswordText(@Param("userId") int userId, @Param("text") String text);

    /**
     * @Title: changeUserIdentify
     * @Description: 用户更换自己的账号 （手机号）
     * @param userId
     * @param phone
     */
    public void changeUserIdentify(@Param("userId") int userId, @Param("phone") String phone);
}
