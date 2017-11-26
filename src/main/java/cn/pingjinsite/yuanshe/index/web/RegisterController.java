package cn.pingjinsite.yuanshe.index.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.util.RequestUtil;
import cn.pingjinsite.framework.util.SMSUtil;
import cn.pingjinsite.yuanshe.index.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/sendsms.json")
    @ResponseBody
    public BasicVo sendSms(String phone) {
        if (registerService.isPhoneExist(phone)) {
            // 手机号已经存在
            return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "手机号已被注册");
        } else {
            if (SMSUtil.sendSMS(phone)) {
                return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "短信已发送，请注意查收");
            } else {
                return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "短信发送失败");
            }
        }
    }

    @RequestMapping("/phone-register.json")
    @ResponseBody
    public BasicVo registerByPhone(HttpServletRequest request) {
        // 获取请求参数对
        Map paras = RequestUtil.getParamAsMap(request);
        if (SMSUtil.verifySmsCode(paras.get("phone").toString(), paras.get("number").toString())) {
            // 验证码验证正确 进入数据库业务操作
            return registerService.registerByPhone(paras);
        } else {
            return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "验证码错误或已失效");
        }
    }
}
