package cn.pingjinsite.yuanshe.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.framework.util.DateUtil;
import cn.pingjinsite.yuanshe.community.mybatis.mapper.BlogMapper;
import cn.pingjinsite.yuanshe.community.mybatis.mapper.CollectMapper;
import cn.pingjinsite.yuanshe.community.mybatis.mapper.LikeMapper;
import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogFullVo;
import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogVo;

/**
 * @ClassName: BlogService
 * @Description: 博客数据持久层
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月22日 下午8:20:39
 */
@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private CollectMapper collectMapper;

    /**
     * @Title: publishBlog
     * @Description: 添加博客文章记录
     * @param blog
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo publishBlog(BlogVo blog) {
        try {
            blogMapper.insertBlog(blog);
            blogMapper.insertBlogStatistics(blog.getId());
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "发布成功,请耐心等候审核结果");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户发布博客文章时,数据库异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }

    /**
     * @Title: getBlogEntity
     * @Description: 获取博客实体
     * @param id
     * @return BlogVo
     */
    public BlogVo getBlogEntity(int id) {
        return blogMapper.getBlog(id);
    }

    /**
     * @Title: getBlogExtraVo
     * @Description: 获取博客全部信息
     * @param userId 用户id
     * @param blogId 博客id
     * @return BlogExtraVo
     */
    public BlogFullVo getBlogFullVo(int userId, int blogId) {
        BlogFullVo vo = new BlogFullVo();
        vo.setBlogVo(blogMapper.getBlog(blogId));
        vo.setExtra(blogMapper.getBlogExtraVo(blogId));
        if ("1".equals(likeMapper.likeStatus(userId, blogId))) {
            vo.setLiked(true);
        } else {
            vo.setLiked(false);
        }
        if ("1".equals(collectMapper.collectStatus(userId, blogId))) {
            vo.setCollected(true);
        } else {
            vo.setCollected(false);
        }
        return vo;
    }

    /**
     * @Title: getBlogList
     * @Description: 获取博客列表数据
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param classify 分类
     * @param query 搜索关键字
     * @return BasicEntityVo<Pagination> 返回结果
     */
    public BasicEntityVo<Pagination> getBlogList(int pageNo, int pageSize, String classify, String query) {
        BasicEntityVo<Pagination> vo = null;
        try {
            if (classify == null) {
                classify = "";
            }
            if (query == null) {
                query = "";
            }
            List<BlogVo> list = blogMapper.listBlog((pageNo - 1) * pageSize, pageSize, classify, query);
            int count = blogMapper.listBlogCount(classify, query);
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                for (BlogVo blog : list) {
                    blog.setTime(DateUtil.getCustomDateTime(blog.getCreatetime()));
                }
                Pagination page = new Pagination();
                page.setPageNo(pageNo);
                page.setPageSize(pageSize);
                page.setStartNum((pageNo - 1) * pageSize);
                page.setTotalCount(count);
                page.setList(list);
                vo.setData(page);
            } else {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_ERROR, "没有获取到数据");
            }
        } catch (RuntimeException ex) {
            MyLog4j.error(getClass(), "获取博客列表时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: blogIsOwnerDelete
     * @Description: 博客是否被作者删除
     * @param id
     * @return boolean
     */
    public boolean blogIsOwnerDelete(int id) {
        return blogMapper.getBlogStatus(id) == 1 ? true : false;
    }
}
