package cn.pingjinsite.yuanshe.community.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

public interface CollectMapper {

    /**
     * @Title: insertCollect
     * @Description: 用户收藏博客
     * @param userId 用户标识
     * @param blogId 博客标识
     * @param action 1收藏 2取消收藏
     */
    public void insertCollect(@Param("userId") int userId, @Param("blogId") int blogId, @Param("action") int action);

    /**
     * @Title: updateCollectNum
     * @Description: 修改博客收藏数量
     * @param blogId 博客id
     * @param num 1 或者 -1
     */
    public void updateCollectNum(@Param("blogId") int blogId, @Param("num") int num);

    /**
     * @Title: collectStatus
     * @Description: 查看用户对此博客的收藏状态
     * @param userId 用户标识
     * @param blogId 博客标识
     * @return String 1已收藏 2取消了收藏 null从没收藏过
     */
    public String collectStatus(@Param("userId") int userId, @Param("blogId") int blogId);
}
