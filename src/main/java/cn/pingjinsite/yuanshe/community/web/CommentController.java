package cn.pingjinsite.yuanshe.community.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.yuanshe.community.mybatis.vo.CommentVo;
import cn.pingjinsite.yuanshe.community.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * @Title: getCommentList
     * @Description: 评论列表
     * @param blogId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/list.json")
    public @ResponseBody BasicEntityVo<Pagination> getCommentList(int blogId, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return commentService.getCommentList(blogId, pageNo, pageSize);
    }

    /**
     * @Title: discuss
     * @Description: 用户评论 回复
     * @param comment
     * @return BasicEntityVo<CommentVo>
     */
    @RequestMapping("/discuess.action")
    @ResponseBody
    public BasicEntityVo<CommentVo> discuss(CommentVo comment) {
        return commentService.insertComment(comment);
    }
}
