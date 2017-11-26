package cn.pingjinsite.yuanshe.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.yuanshe.message.mybatis.mapper.NoticeMapper;
import cn.pingjinsite.yuanshe.message.mybatis.vo.NoticeVo;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * @Title: list
     * @Description: 系统通知
     * @param userId
     * @return List<NoticeVo>
     */
    public List<NoticeVo> list(int userId) {
        return noticeMapper.list(userId);
    }

    /**
     * @Title: updateLetterStatus
     * @Description: 删除系统通知
     * @param id
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo updateNoticeStatus(int id) {
        try {
            noticeMapper.deleteNotice(id);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "删除成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户删除系统通知时,数据库操作失败", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }
}
