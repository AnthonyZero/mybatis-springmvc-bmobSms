package cn.pingjinsite.yuanshe.community.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

public interface LikeMapper {

    /**
     * @Title: insertLikeAction
     * @Description: 用户点赞 和取消点赞
     * @param userId 用户id
     * @param blogId 博客id
     * @param action 行为 1点赞 2取消点赞
     */
    public void insertLikeAction(@Param("userId") int userId, @Param("blogId") int blogId, @Param("action") int action);

    /**
     * @Title: updateLikeNum
     * @Description: 修改博客点赞数
     * @param blogId 博客id
     * @param num 1 或者 -1
     */
    public void updateLikeNum(@Param("blogId") int blogId, @Param("num") int num);

    /**
     * @Title: likeStatus
     * @Description: 获取用户点赞此微博的状态
     * @param userId 用户标识
     * @param blogId 博客标识
     * @return String 1点赞状态 2未点赞状态 或者null从没点赞过
     */
    public String likeStatus(@Param("userId") int userId, @Param("blogId") int blogId);
}
