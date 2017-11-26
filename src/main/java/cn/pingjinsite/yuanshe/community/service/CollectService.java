package cn.pingjinsite.yuanshe.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.yuanshe.community.mybatis.mapper.CollectMapper;

@Service
public class CollectService {

    @Autowired
    private CollectMapper collectMapper;

    /**
     * @Title: userCollectBlog
     * @Description: 用户收藏 或者 取消收藏博客
     * @param userId 用户id
     * @param blogId 博客id
     * @param action 1收藏 2取消收藏
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo userCollectBlog(int userId, int blogId, int action) {
        try {
            collectMapper.insertCollect(userId, blogId, action);
            if (action == 1) {
                collectMapper.updateCollectNum(blogId, 1);
                return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "收藏成功");
            } else {
                collectMapper.updateCollectNum(blogId, -1);
                return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "已取消收藏");
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户收藏博客文章时,数据库异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }
}
