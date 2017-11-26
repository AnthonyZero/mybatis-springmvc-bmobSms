package cn.pingjinsite.yuanshe.usercenter.web;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.util.FileUploadUtil;
import cn.pingjinsite.framework.util.SMSUtil;
import cn.pingjinsite.yuanshe.index.service.RegisterService;
import cn.pingjinsite.yuanshe.usercenter.service.UpdateInfoService;

@Controller
@RequestMapping("/update")
public class UpdateInfoController {

    @Autowired
    private UpdateInfoService updateInfoService;

    @Autowired
    private RegisterService registerService;

    /**
     * @Title: updateHeadImg
     * @Description: 修改用户头像信息
     * @param userId 用户唯一标识
     * @param file 图片文件
     * @return BasicVo
     */
    @RequestMapping("/user-head.json")
    @ResponseBody
    public BasicVo updateHeadImg(@RequestParam("userId") String userId, @RequestParam("file") MultipartFile file) {
        String fileName = userId + ".png";
        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "上传图片失败");
        }
        BasicVo vo = FileUploadUtil.uploadImageFile(fileName, in);
        if (ContextCodeRepostiory.CODE_SUCCESS.equals(vo.getCode())) {
            // 上传图片成功 更新数据库用户头像信息
            updateInfoService.updateUserHeadImg(Integer.parseInt(userId), vo.getMessage());
        }
        return vo;
    }

    /**
     * @Title: updateUserInfo
     * @Description: 修改基础用户信息
     * @param userId
     * @param key
     * @param value
     * @return BasicVo
     */
    @RequestMapping("/user-info.json")
    @ResponseBody
    public BasicVo updateUserInfo(@RequestParam("userId") String userId, @RequestParam("key") String key, @RequestParam("value") String value) {
        return updateInfoService.updateUserInfo(Integer.parseInt(userId), key, value);
    }

    /**
     * @Title: updateUserPassword
     * @Description: 用户修改密码
     * @param userId
     * @param password
     * @return BasicVo
     */
    @RequestMapping("/user-password.action")
    @ResponseBody
    public BasicVo updateUserPassword(int userId, String password) {
        return updateInfoService.updateUserPassword(userId, password);
    }

    /**
     * @Title: sendForgetPasswordSms
     * @Description: 忘记密码 发送短信
     * @param phone
     * @return BasicVo
     */
    @RequestMapping("/send-pawordsms.json")
    @ResponseBody
    public BasicVo sendForgetPasswordSms(String phone) {
        if (!registerService.isPhoneExist(phone)) {
            // 手机号如果不存在
            return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "账号不存在");
        } else {
            if (SMSUtil.sendUpdatePasswordSMS(phone)) {
                return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "验证码已发送，请注意查收");
            } else {
                return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "短信发送失败,请稍后重试");
            }
        }
    }

    /**
     * @Title: resetUserPassword
     * @Description: 用户重置密码
     * @param phone 手机号
     * @param code 验证码
     * @param password 密码
     * @return BasicVo
     */
    @RequestMapping("/reset-password.action")
    @ResponseBody
    public BasicVo resetUserPassword(String phone, String code, String password) {
        if (SMSUtil.verifySmsCode(phone, code)) {
            // 验证码验证正确 进入数据库业务操作
            return updateInfoService.resetUserPassword(phone, password);
        } else {
            return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "验证码错误或已失效");
        }
    }

    /**
     * @Title: sendChangePhoneSms
     * @Description: 用户更换手机号 发送短信
     * @param phone
     * @return BasicVo
     */
    @RequestMapping("/sendsms-changephone.json")
    @ResponseBody
    public BasicVo sendChangePhoneSms(String phone) {
        if (SMSUtil.sendChangePhoneSMS(phone)) {
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "验证码已发送，请注意查收");
        } else {
            return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "短信发送失败,请稍后重试");
        }
    }

    /**
     * @Title: changeUserIdentify
     * @Description: 用户更换手机号
     * @param userId
     * @param phone
     * @param code
     * @return BasicVo
     */
    @RequestMapping("/user-identify.action")
    @ResponseBody
    public BasicVo changeUserIdentify(int userId, String phone, String code) {
        if (SMSUtil.verifySmsCode(phone, code)) {
            // 验证码验证正确 进入数据库业务操作
            return updateInfoService.changeUserIdentify(userId, phone);
        } else {
            return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "验证码错误或已失效");
        }
    }
}
