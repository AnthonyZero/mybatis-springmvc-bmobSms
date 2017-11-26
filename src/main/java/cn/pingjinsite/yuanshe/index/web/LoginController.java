package cn.pingjinsite.yuanshe.index.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.util.RequestUtil;
import cn.pingjinsite.yuanshe.index.service.LoginService;
import cn.pingjinsite.yuanshe.index.service.RegisterService;
import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;

/**
 * @ClassName: LoginController
 * @Description: 用户登录
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 下午5:15:26
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    /**
     * @Title: loginByPhone
     * @Description: 手机账号登陆
     * @param phone
     * @param password
     * @return BasicEntityVo<UserVo>
     */
    @RequestMapping("/phone-login.json")
    @ResponseBody
    public BasicEntityVo<UserVo> loginByPhone(String phone, String password) {
        return loginService.loginByPhone(phone, password);
    }

    /**
     * @Title: loginByOauth
     * @Description: 第三方账号登陆
     * @param request 请求参数对 uid access_token type nickname img
     * @return BasicEntityVo<UserVo>
     */
    @RequestMapping("/oauth-login.json")
    @ResponseBody
    public BasicEntityVo<UserVo> loginByOauth(HttpServletRequest request) {
        Map map = RequestUtil.getParamAsMap(request);
        String uid = map.get("uid").toString();
        String type = map.get("type").toString();
        if (registerService.isOauthAccountExist(uid, type)) {
            // 如果数据库已经存在该第三方账号
            // 数据库更新access_token
            loginService.updateAccessToken(uid, type, map.get("access_token").toString());
            // 返回账号对象
            return loginService.loginByOauth(uid, type);
        } else {
            // 不存在 利用第三方账号进行注册
            boolean flag = registerService.registerByOauth(map);
            if (flag) {
                // 注册成功 返回账号对象
                return loginService.loginByOauth(uid, type);
            }
            return new BasicEntityVo<>(ContextCodeRepostiory.CODE_ERROR, "服务器异常,请稍后重新尝试");
        }
    }

}
