package cn.pingjinsite.yuanshe.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.yuanshe.community.mybatis.mapper.LikeMapper;

@Service
public class LikeService {

    @Autowired
    private LikeMapper likeMapper;

    /**
     * @Title: userLikeBlog
     * @Description: 用户对博客进行点赞
     * @param userId
     * @param blogId
     * @param action 1点赞 2取消点赞
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo userPraiseBlog(int userId, int blogId, int action) {
        try {
            likeMapper.insertLikeAction(userId, blogId, action);
            if (action == 1) {
                likeMapper.updateLikeNum(blogId, 1);
                return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "点赞成功");
            } else {
                likeMapper.updateLikeNum(blogId, -1);
                return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "取消成功");
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户点赞博客文章时,数据库异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }
}
