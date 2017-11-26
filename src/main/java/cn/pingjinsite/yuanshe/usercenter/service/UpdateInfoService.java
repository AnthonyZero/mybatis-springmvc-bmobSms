package cn.pingjinsite.yuanshe.usercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.enums.Content;
import cn.pingjinsite.framework.enums.Title;
import cn.pingjinsite.framework.enums.Type;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.framework.util.EncryptUtil;
import cn.pingjinsite.yuanshe.message.mybatis.mapper.NoticeMapper;
import cn.pingjinsite.yuanshe.message.mybatis.vo.NoticeVo;
import cn.pingjinsite.yuanshe.usercenter.mybatis.mapper.UpdateInfoMapper;
import cn.pingjinsite.yuanshe.usercenter.mybatis.mapper.UserMapper;

/**
 * @ClassName: UpdateInfoService
 * @Description: 修改用户信息服务
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月19日 下午10:08:55
 */
@Service
public class UpdateInfoService {

    @Autowired
    private UpdateInfoMapper updateInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * @Title: updateUserHeadImg
     * @Description: 修改头像
     * @param userId
     * @param imgUrl
     */
    public void updateUserHeadImg(int userId, String imgUrl) {
        updateInfoMapper.updateHeadImg(userId, imgUrl);
    }

    /**
     * @Title: updateUserInfo
     * @Description: 更新用户信息
     * @param userId
     * @param key
     * @param value
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo updateUserInfo(int userId, String key, String value) {
        try {
            updateInfoMapper.updateUserInfo(userId, key, value);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "修改成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "修改用户信息,数据库异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }

    /**
     * @Title: updateUserPassword
     * @Description: 用户修改密码
     * @param userId
     * @param passwordText
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public BasicVo updateUserPassword(int userId, String passwordText) {
        try {
            String password = EncryptUtil.encryptSHA(passwordText.getBytes());
            updateInfoMapper.updatePassword(userId, password);
            updateInfoMapper.updatePasswordText(userId, passwordText);
            // 插入通知
            NoticeVo noticeVo = new NoticeVo();
            noticeVo.setUserId(userId);
            noticeVo.setType(Type.ACCOUNT.getName());
            noticeVo.setTitle(Title.UPDATE_PASSWORD.getName());
            noticeVo.setContent(Content.UPDATE_PASSWORD.getName());
            noticeMapper.insertNotice(noticeVo);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "修改成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户修改密码时,出现异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }

    /**
     * @Title: resetUserPassword
     * @Description: 用户忘记密码时 重置密码
     * @param phone
     * @param passwordText
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public BasicVo resetUserPassword(String phone, String passwordText) {
        try {
            int userId = userMapper.getUserIdByPhone(phone); // 根据手机号获取用户id
            String password = EncryptUtil.encryptSHA(passwordText.getBytes());
            updateInfoMapper.updatePassword(userId, password); // 修改用户暗文密码
            updateInfoMapper.updatePasswordText(userId, passwordText); // 修改用户明文密码
            // 插入通知
            NoticeVo noticeVo = new NoticeVo();
            noticeVo.setUserId(userId);
            noticeVo.setType(Type.ACCOUNT.getName());
            noticeVo.setTitle(Title.RESET_PASSWORD.getName());
            noticeVo.setContent(Content.RESET_PASSWORD.getName());
            noticeMapper.insertNotice(noticeVo);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "重置密码成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户重置密码时,出现异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }

    /**
     * @Title: changeUserIdentify
     * @Description: 用户更换 手机号
     * @param userId
     * @param phone
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo changeUserIdentify(int userId, String phone) {
        try {
            updateInfoMapper.changeUserIdentify(userId, phone);
            // 插入通知
            NoticeVo noticeVo = new NoticeVo();
            noticeVo.setUserId(userId);
            noticeVo.setType(Type.ACCOUNT.getName());
            noticeVo.setTitle(Title.CHANGE_PHONE.getName());
            noticeVo.setContent(Content.CHANGE_PHONE.getName());
            noticeMapper.insertNotice(noticeVo);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "更换成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户更换手机号时,数据库异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }
}
