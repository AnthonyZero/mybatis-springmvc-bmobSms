package cn.pingjinsite.yuanshe.message.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.message.mybatis.vo.LikeMessageVo;

public interface LikeMessageMapper {

    /**
     * @Title: list
     * @Description: 获取他人点赞我文章消息列表
     * @param userId 自己的标识
     * @param start
     * @param pageSize
     * @return List<LikeMessageVo>
     */
    public List<LikeMessageVo> list(@Param("userId") int userId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listCount
     * @Description: 统计他人点赞我文章消息的数目
     * @param userId
     * @return int
     */
    public int listCount(@Param("userId") int userId);

    /**
     * @Title: deleteLikeMessage
     * @Description: 删除点赞消息
     * @param id
     */
    public void deleteLikeMessage(@Param("id") int id);
}
