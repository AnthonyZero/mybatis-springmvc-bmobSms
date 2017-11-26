package cn.pingjinsite.yuanshe.usercenter.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogVo;

public interface OwnerBlogMapper {

    /**
     * @Title: listBlog
     * @Description: 我的博客列表
     * @param id
     * @param start
     * @param pageSize
     * @return List<BlogVo>
     */
    public List<BlogVo> listBlog(@Param("id") int id, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listBlogCount
     * @Description: 我的博客数量
     * @param id
     * @return int
     */
    public int listBlogCount(@Param("id") int id);

    /**
     * @Title: deleteBlog
     * @Description: 删除自己的文章
     * @param id
     * @param userId
     */
    public void deleteBlog(@Param("id") int id);

    /**
     * @Title: isExistHistory
     * @Description: 返回用户阅读此博客的记录条数
     * @param blogId
     * @param userId
     * @return int
     */
    public int isExistHistory(@Param("blogId") int blogId, @Param("userId") int userId);

    /**
     * @Title: insertBlogReadHistory
     * @Description: 当阅读记录不存在 新增一条
     * @param blogId 博客标识
     * @param userId 用户标识
     */
    public void insertBlogReadHistory(@Param("blogId") int blogId, @Param("userId") int userId);

    /**
     * @Title: updateRHStatus
     * @Description: 当阅读记录存在 更新状态（时间和status）
     * @param blogId 博客标识
     * @param userId 用户标识
     */
    public void updateRHStatus(@Param("blogId") int blogId, @Param("userId") int userId);

    /**
     * @Title: listReadHistory
     * @Description: 获取用户阅读历史纪录
     * @param id 用户标识
     * @param start
     * @param pageSize
     * @return List<BlogVo>
     */
    public List<BlogVo> listReadHistory(@Param("id") int id, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listRHistoryCount
     * @Description: 用户阅读历史纪录的数量
     * @param id
     * @return int
     */
    public int listRHistoryCount(@Param("id") int id);

    /**
     * @Title: emptyReadHistory
     * @Description: 清空阅读历史纪录
     * @param id
     */
    public void emptyReadHistory(@Param("id") int id);
}
