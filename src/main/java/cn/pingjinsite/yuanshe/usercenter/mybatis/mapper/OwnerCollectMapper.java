package cn.pingjinsite.yuanshe.usercenter.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogVo;

public interface OwnerCollectMapper {

    /**
     * @Title: listCollects
     * @Description: 获取用户自己的收藏文章列表
     * @param id 用户id
     * @param start
     * @param pageSize
     * @return List<BlogVo>
     */
    public List<BlogVo> listCollects(@Param("id") int id, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listCollectsCount
     * @Description: 获取用户自己的收藏数目
     * @param id 用户id
     * @return int
     */
    public int listCollectsCount(@Param("id") int id);
}
