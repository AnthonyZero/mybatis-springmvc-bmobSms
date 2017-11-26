package cn.pingjinsite.yuanshe.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.framework.util.DateUtil;
import cn.pingjinsite.yuanshe.community.mybatis.mapper.BlogRankMapper;
import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogRankVo;
import cn.pingjinsite.yuanshe.community.mybatis.vo.UserRankVo;

@Service
public class BlogRankService {

    @Autowired
    private BlogRankMapper blogRankMapper;

    /**
     * @Title: getBlogReadRank
     * @Description: 博客阅读榜单
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> getBlogReadRank(int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<BlogRankVo> list = blogRankMapper.listReadRank((pageNo - 1) * pageSize, pageSize);
            int count = blogRankMapper.listBlogCount();
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                for (BlogRankVo blog : list) {
                    blog.setCreatetime(DateUtil.getCustomDateTime(blog.getCreatetime()));
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
            MyLog4j.error(getClass(), "获取博客阅读榜单时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: getBlogLikeRank
     * @Description: 博客点赞榜单
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> getBlogLikeRank(int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<BlogRankVo> list = blogRankMapper.listLikeRank((pageNo - 1) * pageSize, pageSize);
            int count = blogRankMapper.listBlogCount();
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                for (BlogRankVo blog : list) {
                    blog.setCreatetime(DateUtil.getCustomDateTime(blog.getCreatetime()));
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
            MyLog4j.error(getClass(), "获取博客点赞榜单时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: getBlogCollectRank
     * @Description: 博客收藏榜单
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> getBlogCollectRank(int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<BlogRankVo> list = blogRankMapper.listCollectRank((pageNo - 1) * pageSize, pageSize);
            int count = blogRankMapper.listBlogCount();
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                for (BlogRankVo blog : list) {
                    blog.setCreatetime(DateUtil.getCustomDateTime(blog.getCreatetime()));
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
            MyLog4j.error(getClass(), "获取博客收藏榜单时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: getUserDevoteRank
     * @Description: 用户贡献榜单
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> getUserDevoteRank(int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<UserRankVo> list = blogRankMapper.listUserDevoteRank((pageNo - 1) * pageSize, pageSize);
            int count = blogRankMapper.listUserCount();
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
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
            MyLog4j.error(getClass(), "获取用户贡献榜单时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: getUserDevoteRanking
     * @Description: 用户贡献排名
     * @param userId
     * @return int 返回0的时候代表在50名开外
     */
    public int getUserDevoteRanking(int userId) {
        List<UserRankVo> list = blogRankMapper.getUserDevoteRanking();
        List<Integer> data = new ArrayList<>();
        for (UserRankVo vo : list) {
            data.add(vo.getUserId());
        }
        return data.indexOf(userId) < 50 ? data.indexOf(userId) + 1 : 0;
    }
}
