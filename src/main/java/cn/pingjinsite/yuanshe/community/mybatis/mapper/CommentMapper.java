package cn.pingjinsite.yuanshe.community.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.community.mybatis.vo.CommentVo;

public interface CommentMapper {

    /**
     * @Title: list
     * @Description: 获取评论列表
     * @param blogId 博客标识
     * @param start
     * @param pageSize
     * @return List<CommentVo>
     */
    public List<CommentVo> list(@Param("blogId") int blogId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listCount
     * @Description: 评论数量
     * @param blogId
     * @return int
     */
    public int listCount(@Param("blogId") int blogId);

    /**
     * @Title: getComment
     * @Description: 获取评论对象
     * @param id
     * @return CommentVo
     */
    public CommentVo getComment(@Param("id") int id);

    /**
     * @Title: insertComment
     * @Description: 插入评论记录
     * @param vo
     */
    public void insertComment(CommentVo vo);

    /**
     * @Title: blogCommentRise
     * @Description: 博客评论数 +1
     * @param blogId
     */
    public void blogCommentRise(@Param("blogId") int blogId);
}
