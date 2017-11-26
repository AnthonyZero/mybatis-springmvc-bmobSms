package cn.pingjinsite.yuanshe.community.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogExtraVo;
import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogVo;

/**
 * @ClassName: BlogMapper
 * @Description: 博客相关接口实现
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月22日 下午8:10:55
 */
public interface BlogMapper {

    /**
     * @Title: insertBlog
     * @Description: 插入博客表记录
     * @param blog
     */
    public void insertBlog(BlogVo blog);

    /**
     * @Title: insertBlogStatistics
     * @Description: 初始化博客表统计数据
     * @param id
     */
    public void insertBlogStatistics(int id);

    /**
     * @Title: listBlog
     * @Description: 分页获取博客信息
     * @param start 起止
     * @param pageSize 每页大小
     * @param classify 类别 空字符串代表所有类别
     * @param query 搜索关键字
     * @return List<BlogVo>
     */
    public List<BlogVo> listBlog(@Param("start") int start, @Param("pageSize") int pageSize, @Param("classify") String classify, @Param("query") String query);

    /**
     * @Title: listBlogCount
     * @Description: 博客数量
     * @param classify 类别
     * @param query 搜索关键字
     * @return int
     */
    public int listBlogCount(@Param("classify") String classify, @Param("query") String query);

    /**
     * @Title: getBlog
     * @Description: 获取博客详情
     * @param id 博客标识
     * @return BlogVo
     */
    public BlogVo getBlog(@Param("id") int id);

    /**
     * @Title: getBlogStatus
     * @Description: 获取博客状态
     * @param id
     * @return int
     */
    public int getBlogStatus(@Param("id") int id);

    /**
     * @Title: getBlogExtraVo
     * @Description: 获取博客额外信息 统计数据
     * @param id
     * @return BlogExtraVo
     */
    public BlogExtraVo getBlogExtraVo(@Param("id") int id);

}
