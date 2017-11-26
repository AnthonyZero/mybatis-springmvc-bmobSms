package cn.pingjinsite.yuanshe.message.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.message.mybatis.vo.CollectMessageVo;

public interface CollectMessageMapper {

    /**
     * @Title: list
     * @Description: 收藏消息
     * @param userId 用户标识
     * @param start
     * @param pageSize
     * @return List<CollectMessageVo>
     */
    public List<CollectMessageVo> list(@Param("userId") int userId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listCount
     * @Description: 收藏消息数量
     * @param userId
     * @return int
     */
    public int listCount(@Param("userId") int userId);

    /**
     * @Title: deleteCollectMessage
     * @Description: 删除收藏信息
     * @param id void
     */
    public void deleteCollectMessage(@Param("id") int id);
}
