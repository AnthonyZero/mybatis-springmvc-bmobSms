package cn.pingjinsite.yuanshe.usercenter.service;

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
import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogVo;
import cn.pingjinsite.yuanshe.usercenter.mybatis.mapper.OwnerBlogMapper;

@Service
public class OwnerBlogService {

    @Autowired
    private OwnerBlogMapper blogMapper;

    /**
     * @Title: getOwnerBlogList
     * @Description: 获取我的博客列表 审核通过且没有删除
     * @param id 用户唯一标识
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> getOwnerBlogList(int id, int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<BlogVo> list = blogMapper.listBlog(id, (pageNo - 1) * pageSize, pageSize);
            int count = blogMapper.listBlogCount(id);
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
            MyLog4j.error(getClass(), "获取我的博客列表时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: delOwnerBlog
     * @Description: 用户删除自己的博客文章
     * @param id
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo delOwnerBlog(int id) {
        try {
            blogMapper.deleteBlog(id);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "删除成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "删除博客时,数据库异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }

    /**
     * @Title: insertReadHistory
     * @Description: 添加用户阅读博客的记录
     * @param blogId 博客标识
     * @param userId 用户标识
     */
    public void insertReadHistory(int blogId, int userId) {
        if (blogMapper.isExistHistory(blogId, userId) <= 0) {
            // 用户从来没有阅读过 直接新增记录
            blogMapper.insertBlogReadHistory(blogId, userId);
        } else {
            // 以前阅读过 更新时间和状态 阅读次数
            blogMapper.updateRHStatus(blogId, userId);
        }
    }

    public BasicEntityVo<Pagination> getOwnerReadHistory(int id, int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<BlogVo> list = blogMapper.listReadHistory(id, (pageNo - 1) * pageSize, pageSize);
            int count = blogMapper.listRHistoryCount(id);
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                for (BlogVo blog : list) {
                    // 这里的time是阅读时间 不是博客的创建时间
                    blog.setTime(DateUtil.getCustomDateTime(blog.getTime()));
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
            MyLog4j.error(getClass(), "获取我的阅读历史纪录时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: emptyReadHistory
     * @Description: 清除当前用户阅读历史数据
     * @param id 用户唯一标识
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo emptyReadHistory(int id) {
        try {
            blogMapper.emptyReadHistory(id);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "清除成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "删除用户阅读历史数据时,数据库异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }
}
