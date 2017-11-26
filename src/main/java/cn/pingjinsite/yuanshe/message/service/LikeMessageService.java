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
import cn.pingjinsite.framework.util.DateUtil;
import cn.pingjinsite.yuanshe.message.mybatis.mapper.LikeMessageMapper;
import cn.pingjinsite.yuanshe.message.mybatis.vo.LikeMessageVo;

@Service
public class LikeMessageService {

    @Autowired
    private LikeMessageMapper likeMessageMapper;

    /**
     * @Title: listLikeMessages
     * @Description: 获取他人点赞我博客的消息列表
     * @param userId 自己的标识
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> listLikeMessages(int userId, int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<LikeMessageVo> list = likeMessageMapper.list(userId, (pageNo - 1) * pageSize, pageSize);
            int count = likeMessageMapper.listCount(userId);
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                for (LikeMessageVo message : list) {
                    message.setCreatetime(DateUtil.getCustomDateTime(message.getCreatetime()));
                }
                Pagination page = new Pagination();
                page.setPageNo(pageNo);
                page.setPageSize(pageSize);
                page.setStartNum((pageNo - 1) * pageSize);
                page.setTotalCount(count);
                page.setList(list);
                vo.setData(page);
            } else {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_ERROR, "还没有人点赞你的文章");
            }
        } catch (RuntimeException ex) {
            MyLog4j.error(getClass(), "获取点赞我的博客信息列表时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: deleteLikeMessage
     * @Description: 删除点赞信息
     * @param id void
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo deleteLikeMessage(int id) {
        try {
            likeMessageMapper.deleteLikeMessage(id);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "删除成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "删除点赞消息时,服务器异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }
}
