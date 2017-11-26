package cn.pingjinsite.yuanshe.community.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.framework.util.JsoupHtmlUtil;
import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogFullVo;
import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogVo;
import cn.pingjinsite.yuanshe.community.service.BlogService;
import cn.pingjinsite.yuanshe.community.service.CollectService;
import cn.pingjinsite.yuanshe.community.service.LikeService;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private CollectService collectService;

    /**
     * @Title: publishBlog
     * @Description: 用户分享博客文章到APP
     * @param vo 博客信息
     * @param encodeURL 编码之后的原文链接地址
     * @return BasicVo
     */
    @RequestMapping("/user-publish.json")
    @ResponseBody
    public BasicVo publishBlog(BlogVo vo, @RequestParam("encodeURL") String encodeURL) {
        // 给博客实体的缩略图赋值
        vo.setThumbnail(JsoupHtmlUtil.getThumbnailImage(encodeURL));
        BasicVo result = blogService.publishBlog(vo);
        return result;
    }

    /**
     * @Title: getCoachList
     * @Description: 博客列表信息
     * @param pageNo 页码
     * @param pageSize 页数
     * @param classify 类别 例如1001
     * @param query 关键字
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/list.json")
    public @ResponseBody BasicEntityVo<Pagination> getCoachList(int pageNo, int pageSize, String classify, String query) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 6;
        }
        return blogService.getBlogList(pageNo, pageSize, classify, query);
    }

    /**
     * @Title: getBlogEntity
     * @Description: 根据博客id获取详情
     * @param id
     * @return BlogVo
     */
    @RequestMapping("/detail.json")
    @ResponseBody
    public BlogVo getBlogEntity(String id) {
        return blogService.getBlogEntity(Integer.parseInt(id));
    }

    /**
     * @Title: getBlogFullEntity
     * @Description: 博客全部数据 用于构造博客详情页面的方法
     * @param userId 用户id
     * @param blogId 博客id
     * @return BlogFullVo
     */
    @RequestMapping("/full.json")
    @ResponseBody
    public BlogFullVo getBlogFullEntity(int userId, int blogId) {
        return blogService.getBlogFullVo(userId, blogId);
    }

    /**
     * @Title: blogIsDelete
     * @Description: 博客是否已经被作者删除
     * @param id
     * @return boolean
     */
    @RequestMapping("/delete-status.json")
    @ResponseBody
    public boolean blogIsDelete(String id) {
        return blogService.blogIsOwnerDelete(Integer.parseInt(id));
    }

    /**
     * @Title: userPraiseBlog
     * @Description: 用户对博客点 取消赞
     * @param userId 用户id
     * @param blogId 博客id
     * @param action 1点赞 2取消点赞
     */
    @RequestMapping("/like.action")
    @ResponseBody
    public BasicVo userPraiseBlog(int userId, int blogId, int action) {
        return likeService.userPraiseBlog(userId, blogId, action);
    }

    /**
     * @Title: userCollectBlog
     * @Description: 用户对博客 收藏
     * @param userId 用户标识
     * @param blogId 博客标识
     * @param action 1收藏 2取消收藏
     * @return BasicVo
     */
    @RequestMapping("/collect.action")
    @ResponseBody
    public BasicVo userCollectBlog(int userId, int blogId, int action) {
        return collectService.userCollectBlog(userId, blogId, action);
    }
}
