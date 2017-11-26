package cn.pingjinsite.yuanshe.usercenter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.yuanshe.usercenter.service.OwnerBlogService;

@Controller
@RequestMapping("/ownerblog")
public class OwnerBlogController {

    @Autowired
    private OwnerBlogService blogService;

    /**
     * @Title: getCoachList
     * @Description: 个人中心我的文章列表
     * @param id
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/list.json")
    public @ResponseBody BasicEntityVo<Pagination> getCoachList(int id, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return blogService.getOwnerBlogList(id, pageNo, pageSize);
    }

    /**
     * @Title: deleteOwnerBlog
     * @Description: 选择删除我的文章
     * @param id
     * @return BasicVo
     */
    @RequestMapping("/delete.json")
    @ResponseBody
    public BasicVo deleteOwnerBlog(String id) {
        return blogService.delOwnerBlog(Integer.parseInt(id));
    }

    /**
     * @Title: readBlog
     * @Description: 用户阅读博客行为 添加纪录
     * @param blogId 博客标识
     * @param userId 用户标识
     */
    @RequestMapping("/read.json")
    public void readBlog(int blogId, int userId) {
        blogService.insertReadHistory(blogId, userId);
    }

    /**
     * @Title: getReadHistoryList
     * @Description: 阅读历史列表
     * @param id
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/read-history.json")
    public @ResponseBody BasicEntityVo<Pagination> getReadHistoryList(int id, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return blogService.getOwnerReadHistory(id, pageNo, pageSize);
    }

    @RequestMapping("/empty-history.json")
    @ResponseBody
    public BasicVo emptyReadHistory(int id) {
        return blogService.emptyReadHistory(id);
    }
}
