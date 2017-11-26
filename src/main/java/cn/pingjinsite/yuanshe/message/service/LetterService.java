package cn.pingjinsite.yuanshe.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.yuanshe.message.mybatis.mapper.LetterMapper;
import cn.pingjinsite.yuanshe.message.mybatis.vo.LetterVo;

@Service
public class LetterService {

    @Autowired
    private LetterMapper letterMapper;

    /**
     * @Title: sendLetter
     * @Description: 新增信件记录
     * @param ownerId 发件人id
     * @param userId 收件人id
     * @param content 内容
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo sendLetter(int ownerId, int userId, String content) {
        try {
            letterMapper.sendLetter(ownerId, userId, content);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "发送成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "新增信件记录时,数据库插入失败", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }

    /**
     * @Title: listSenderLetters
     * @Description: 发信列表
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> listSenderLetters(int userId, int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<LetterVo> list = letterMapper.listSenderLetter(userId, (pageNo - 1) * pageSize, pageSize);
            int count = letterMapper.listSenderLetterCount(userId);
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                Pagination page = new Pagination();
                page.setPageNo(pageNo);
                page.setPageSize(pageSize);
                page.setStartNum((pageNo - 1) * pageSize);
                page.setTotalCount(count);
                page.setList(list);
                vo.setData(page);
            } else {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_ERROR, "您还没发过私信呢");
            }
        } catch (RuntimeException ex) {
            MyLog4j.error(getClass(), "获取我发出的私信列表时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: updateLetterStatus
     * @Description: 用户删除自己发出的信件
     * @param letterId
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo updateLetterStatus(int letterId) {
        try {
            letterMapper.updateLetterStatus(letterId);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "删除成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "删除信件时,数据库操作失败", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }

    /**
     * @Title: listReceiverLetters
     * @Description: 我的收信列表
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> listReceiverLetters(int userId, int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<LetterVo> list = letterMapper.listReceiverLetter(userId, (pageNo - 1) * pageSize, pageSize);
            int count = letterMapper.listReceiverLetterCount(userId);
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                Pagination page = new Pagination();
                page.setPageNo(pageNo);
                page.setPageSize(pageSize);
                page.setStartNum((pageNo - 1) * pageSize);
                page.setTotalCount(count);
                page.setList(list);
                vo.setData(page);
            } else {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_ERROR, "您还没收到私信呢");
            }
        } catch (RuntimeException ex) {
            MyLog4j.error(getClass(), "获取我收到的私信列表时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: updateLetterRead
     * @Description: 标识信件已读
     * @param letterId
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo updateLetterRead(int letterId) {
        try {
            letterMapper.updateLetterRead(letterId);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "删除成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "标识信件已读时,数据库操作失败", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }
}
