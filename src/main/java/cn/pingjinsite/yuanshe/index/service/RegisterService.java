package cn.pingjinsite.yuanshe.index.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.enums.Content;
import cn.pingjinsite.framework.enums.Title;
import cn.pingjinsite.framework.enums.Type;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.framework.util.EncryptUtil;
import cn.pingjinsite.yuanshe.index.mybatis.mapper.RegisterMapper;
import cn.pingjinsite.yuanshe.message.mybatis.mapper.NoticeMapper;
import cn.pingjinsite.yuanshe.message.mybatis.vo.NoticeVo;
import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;

/**
 * @ClassName: RegisterService
 * @Description: 注册相关服务
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 上午12:41:01
 */
@Service
public class RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * @Title: isPhoneRepeat
     * @Description: 判断数据库是否已经存在该手机号
     * @param phone 手机号
     * @return boolean 存在返回true 不存在返回false
     */
    public boolean isPhoneExist(String phone) {
        List<String> list = registerMapper.findPhoneNumber(phone);
        return list.size() > 0 ? true : false;
    }

    /**
     * @Title: isOauthAccountExist
     * @Description: 判断数据库是否已经存在该第三方账号
     * @param uid 第三方账号唯一标识
     * @param type 第三方账号类型 例如sinaweibo qq等
     * @return boolean 存在返回true 不存在返回false
     */
    public boolean isOauthAccountExist(String uid, String type) {
        List<String> list = registerMapper.findOauthAccount(uid, type);
        return list.size() > 0 ? true : false;
    }

    /**
     * @Title: registerByPhone
     * @Description: 用户手机注册
     * @param map
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo registerByPhone(Map map) {
        UserVo vo = new UserVo();
        vo.setIdentify(map.get("phone").toString());
        vo.setPasswordText(map.get("password").toString());
        vo.setNickname(map.get("nickname").toString());
        vo.setType("phone");
        try {
            // 加密密码
            vo.setPassword(EncryptUtil.encryptSHA(vo.getPasswordText().getBytes()));
            // 数据库插入记录进行注册
            registerMapper.insertUser(vo);
            registerMapper.insertUserExtra(vo);
            registerMapper.insertUserInfo(vo);
            // 插入通知
            NoticeVo noticeVo = new NoticeVo();
            noticeVo.setUserId(vo.getId());
            noticeVo.setType(Type.REGISTER.getName());
            noticeVo.setTitle(Title.PHONE_REGISTER.getName());
            noticeVo.setContent(Content.REGISTER.getName());
            noticeMapper.insertNotice(noticeVo);
        } catch (RuntimeException ex) {
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户手机注册时，数据库异常", true, ex);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, "服务器异常，请稍后重新尝试");
        } catch (Exception e) {
            MyLog4j.error(getClass(), "手机注册时密码加密异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, "服务器异常，请稍后重新尝试");
        }
        return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "注册成功");
    }

    /**
     * @Title: registerByOauth
     * @Description: 第三方账号授权注册
     * @param map 参数对 uid access_token type nickname img
     * @return boolean 注册是否成功
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public boolean registerByOauth(Map map) {
        UserVo vo = new UserVo();
        vo.setIdentify(map.get("uid").toString());
        vo.setPassword(map.get("access_token").toString());
        vo.setType(map.get("type").toString());
        vo.setNickname(map.get("nickname").toString());
        vo.setHeadImg(map.get("img").toString());
        try {
            registerMapper.insertUser(vo);
            registerMapper.insertUserInfo(vo);
            // 插入通知
            NoticeVo noticeVo = new NoticeVo();
            noticeVo.setUserId(vo.getId());
            noticeVo.setType(Type.REGISTER.getName());
            noticeVo.setTitle(Title.OAUTH_REGISTER.getName());
            noticeVo.setContent(Content.REGISTER.getName());
            noticeMapper.insertNotice(noticeVo);
        } catch (RuntimeException ex) {
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户第三方账号注册时，数据库异常", true, ex);
            return false;
        }
        return true;
    }
}
