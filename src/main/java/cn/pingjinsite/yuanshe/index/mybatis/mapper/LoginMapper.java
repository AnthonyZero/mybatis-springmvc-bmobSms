package cn.pingjinsite.yuanshe.index.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;

/**
 * @ClassName: LoginMapper
 * @Description: 登录相关接口
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 下午4:33:17
 */
public interface LoginMapper {

    /**
     * @Title: getUserAccount
     * @Description: 获取手机注册之后的账号
     * @param account 手机号码
     * @param password 加密之后的密码
     * @return UserVo 用户信息
     */
    public UserVo getUserAccount(@Param("account") String account, @Param("password") String password);

    /**
     * @Title: getOauthAccount
     * @Description: 返回第三方账号注册之后的账号
     * @param uid 第三方唯一标识
     * @param type 类型
     * @return UserVo
     */
    public UserVo getOauthAccount(@Param("uid") String uid, @Param("type") String type);
}
