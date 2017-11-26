package cn.pingjinsite.yuanshe.community.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.yuanshe.community.service.BlogRankService;

@Controller
@RequestMapping("/rank")
public class BlogRankController {

    @Autowired
    private BlogRankService blogRankService;

    @RequestMapping("/read.json")
    @ResponseBody
    public BasicEntityVo<Pagination> listReadRankData(int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return blogRankService.getBlogReadRank(pageNo, pageSize);
    }

    /**
     * @Title: listLikeRankData
     * @Description: 博客点赞榜单
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/like.json")
    @ResponseBody
    public BasicEntityVo<Pagination> listLikeRankData(int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return blogRankService.getBlogLikeRank(pageNo, pageSize);
    }

    /**
     * @Title: listCollectRankData
     * @Description: 博客收藏榜单
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/collect.json")
    @ResponseBody
    public BasicEntityVo<Pagination> listCollectRankData(int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return blogRankService.getBlogCollectRank(pageNo, pageSize);
    }

    /**
     * @Title: listDevoteRankData
     * @Description: 用户贡献排名
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/devote.json")
    @ResponseBody
    public BasicEntityVo<Pagination> listDevoteRankData(int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return blogRankService.getUserDevoteRank(pageNo, pageSize);
    }

    /**
     * @Title: getUserDevoteRanking
     * @Description: 用户自己的贡献排名
     * @param userId
     * @return int
     */
    @RequestMapping("/user-ranking.json")
    @ResponseBody
    public int getUserDevoteRanking(int userId) {
        return blogRankService.getUserDevoteRanking(userId);
    }
}
