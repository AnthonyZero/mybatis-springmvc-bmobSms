package cn.pingjinsite.yuanshe.index.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;

/**
 * @ClassName: RegisterMapper
 * @Description: 用户注册相关接口
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月13日 上午9:52:40
 */
public interface RegisterMapper {

    /**
     * @Title: findPhoneNumber
     * @Description: 返回手机号已经存在的列
     * @param phoneNum
     * @return List<String>
     */
    public List<String> findPhoneNumber(String phoneNum);

    /**
     * @Title: findOauthAccount
     * @Description: 返回第三方账号已经存在的列
     * @param uid 第三方账号唯一标识
     * @param type 类型
     * @return List<String>
     */
    public List<String> findOauthAccount(@Param("uid") String uid, @Param("type") String type);

    /**
     * @Title: insertUser
     * @Description: 用户注册 插入用户表记录
     * @param user void
     */
    public void insertUser(UserVo user);

    /**
     * @Title: insertUserExtra
     * @Description: 插入用户额外信息 明文密码
     * @param user void
     */
    public void insertUserExtra(UserVo user);

    /**
     * @Title: insertUserInfo
     * @Description: 插入用户基础信息 若为第三方登录 额外添加头像记录
     * @param user void
     */
    public void insertUserInfo(UserVo user);

    /**
     * @Title: updateAccessToken
     * @Description: 更新第三方账号的access_token
     * @param uid 唯一标识 不同平台 长度不同
     * @param type 类型
     * @param token 替换之后的access_token
     */
    public void updateAccessToken(@Param("uid") String uid, @Param("type") String type, @Param("token") String token);
}
