package cn.pingjinsite.yuanshe.message.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.message.mybatis.vo.CommentMessageVo;

public interface CommentMessageMapper {

    /**
     * @Title: list
     * @Description: 获取评论（不包括回复）消息
     * @param userId
     * @param start
     * @param pageSize
     * @return List<CommentMessageVo>
     */
    public List<CommentMessageVo> list(@Param("userId") int userId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listCount
     * @Description: 评论消息数量
     * @param userId
     * @return int
     */
    public int listCount(@Param("userId") int userId);

    /**
     * @Title: deleteCommentMessage
     * @Description: 删除评论消息
     * @param id
     */
    public void deleteCommentMessage(@Param("id") int id);
}
