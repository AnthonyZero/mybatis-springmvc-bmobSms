package cn.pingjinsite.yuanshe.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.framework.util.EncryptUtil;
import cn.pingjinsite.yuanshe.index.mybatis.mapper.LoginMapper;
import cn.pingjinsite.yuanshe.index.mybatis.mapper.RegisterMapper;
import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;
import cn.pingjinsite.yuanshe.usercenter.service.UserService;

/**
 * @ClassName: LoginService
 * @Description: 登录相关服务
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 下午5:02:29
 */
@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private UserService userService;

    /**
     * @Title: loginByPhone
     * @Description: 用手机和密码 进行登陆
     * @param phone 手机号
     * @param password 原文密码
     * @return BasicEntityVo<UserVo>
     * @throws Exception
     */
    public BasicEntityVo<UserVo> loginByPhone(String phone, String password) {
        List<String> list = registerMapper.findPhoneNumber(phone);
        if (list.size() == 0) {
            return new BasicEntityVo<UserVo>(ContextCodeRepostiory.CODE_ERROR, "用户不存在");
        } else {
            try {
                // 对原文密码 加密
                password = EncryptUtil.encryptSHA(password.getBytes());
            } catch (Exception e) {
                MyLog4j.error(getClass(), "用户登录时密码加密出现异常", true, e);
                return new BasicEntityVo<UserVo>(ContextCodeRepostiory.CODE_EXCEPTION, "服务器异常,请稍后重新尝试");
            }
            UserVo vo = loginMapper.getUserAccount(phone, password);
            if (vo == null) {
                return new BasicEntityVo<UserVo>(ContextCodeRepostiory.CODE_ERROR, "密码错误");
            } else {
                vo.setExtend(userService.getUserStatistics(vo.getId()));
                return new BasicEntityVo<UserVo>(ContextCodeRepostiory.CODE_SUCCESS, "登录成功", vo);
            }
        }
    }

    /**
     * @Title: loginByOauth
     * @Description: 返回第三方账号登陆信息
     * @param uid
     * @param type
     * @return BasicEntityVo<UserVo>
     */
    public BasicEntityVo<UserVo> loginByOauth(String uid, String type) {
        UserVo data = loginMapper.getOauthAccount(uid, type);
        data.setExtend(userService.getUserStatistics(data.getId()));
        return new BasicEntityVo<UserVo>(ContextCodeRepostiory.CODE_SUCCESS, "登录成功", data);
    }

    /**
     * @Title: updateAccessToken
     * @Description: 更新access_token
     * @param uid
     * @param type
     * @param token
     */
    public void updateAccessToken(String uid, String type, String token) {
        registerMapper.updateAccessToken(uid, type, token);
    }
}
