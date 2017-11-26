package cn.pingjinsite.yuanshe.community.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogRankVo;
import cn.pingjinsite.yuanshe.community.mybatis.vo.UserRankVo;

public interface BlogRankMapper {

    /**
     * @Title: listReadRank
     * @Description: 阅读榜单
     * @param start
     * @param pageSize
     * @return List<BlogRankVo>
     */
    public List<BlogRankVo> listReadRank(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listLikeRank
     * @Description: 点赞榜单
     * @param start
     * @param pageSize
     * @return List<BlogRankVo>
     */
    public List<BlogRankVo> listLikeRank(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listCollectRank
     * @Description: 收藏榜单
     * @param start
     * @param pageSize
     * @return List<BlogRankVo>
     */
    public List<BlogRankVo> listCollectRank(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listBlogCount
     * @Description: 获取审核通过且用户未删除的 博客数量
     * @return int
     */
    public int listBlogCount();

    /**
     * @Title: listUserDevoteRank
     * @Description: 用户贡献榜
     * @param start
     * @param pageSize
     * @return List<UserRankVo>
     */
    public List<UserRankVo> listUserDevoteRank(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listUserCount
     * @Description: 用户数量)
     * @return int
     */
    public int listUserCount();

    /**
     * @Title: getUserDevoteRanking
     * @Description: 获取贡献排名前50名的人
     * @return List<UserRankVo>
     */
    public List<UserRankVo> getUserDevoteRanking();
}
