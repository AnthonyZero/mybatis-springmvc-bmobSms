package cn.pingjinsite.yuanshe.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.framework.util.DateUtil;
import cn.pingjinsite.yuanshe.community.mybatis.mapper.CommentMapper;
import cn.pingjinsite.yuanshe.community.mybatis.vo.CommentVo;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * @Title: getCommentList
     * @Description: 获取评论列表
     * @param blogId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> getCommentList(int blogId, int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<CommentVo> list = commentMapper.list(blogId, (pageNo - 1) * pageSize, pageSize);
            int count = commentMapper.listCount(blogId);
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取评论成功");
                for (CommentVo comment : list) {
                    comment.setTime(DateUtil.getCustomDateTime(comment.getTime()));
                }
                Pagination page = new Pagination();
                page.setPageNo(pageNo);
                page.setPageSize(pageSize);
                page.setStartNum((pageNo - 1) * pageSize);
                page.setTotalCount(count);
                page.setList(list);
                vo.setData(page);
            } else {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_ERROR, "还没有人评论过");
            }
        } catch (RuntimeException ex) {
            MyLog4j.error(getClass(), "获取评论列表时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: insertComment
     * @Description: 用户评论（包括回复）
     * @param comment
     * @return BasicEntityVo<CommentVo>
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicEntityVo<CommentVo> insertComment(CommentVo comment) {
        BasicEntityVo<CommentVo> vo = null;
        try {
            commentMapper.insertComment(comment); // 插入评论记录
            commentMapper.blogCommentRise(comment.getBlogId()); // 评论数+1
            CommentVo data = commentMapper.getComment(comment.getId()); // 返回评论实体（携带用户头像等信息）
            data.setTime(DateUtil.getCustomDateTime(data.getTime()));
            vo = new BasicEntityVo<CommentVo>(ContextCodeRepostiory.CODE_SUCCESS, "评论成功", data);
        } catch (RuntimeException ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户评论时,数据库异常", true, ex);
            vo = new BasicEntityVo<CommentVo>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }
}
