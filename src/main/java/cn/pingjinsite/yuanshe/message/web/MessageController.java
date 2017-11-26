package cn.pingjinsite.yuanshe.message.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.yuanshe.message.mybatis.vo.NoticeVo;
import cn.pingjinsite.yuanshe.message.service.CollectMessageService;
import cn.pingjinsite.yuanshe.message.service.CommentMessageService;
import cn.pingjinsite.yuanshe.message.service.LikeMessageService;
import cn.pingjinsite.yuanshe.message.service.NoticeService;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private LikeMessageService likeMessageService;

    @Autowired
    private CollectMessageService collectMessageService;

    @Autowired
    private CommentMessageService commentMessageService;

    @Autowired
    private NoticeService noticeService;

    /**
     * @Title: getLikeMessageList
     * @Description: 获取他人点赞我博客的消息列表
     * @param userId 自己的唯一标识
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/like-list.json")
    public @ResponseBody BasicEntityVo<Pagination> getLikeMessageList(int userId, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 6;
        }
        return likeMessageService.listLikeMessages(userId, pageNo, pageSize);
    }

    /**
     * @Title: deleteLikeMessage
     * @Description: 删除点赞消息
     * @param id
     * @return BasicVo
     */
    @RequestMapping("/like-delete.action")
    @ResponseBody
    public BasicVo deleteLikeMessage(int id) {
        return likeMessageService.deleteLikeMessage(id);
    }

    /**
     * @Title: getCollectMessageList
     * @Description: 获取他人收藏我博客的消息列表
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/collect-list.json")
    public @ResponseBody BasicEntityVo<Pagination> getCollectMessageList(int userId, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 6;
        }
        return collectMessageService.listCollectMessages(userId, pageNo, pageSize);
    }

    /**
     * @Title: deleteCollectMessage
     * @Description: 删除收藏消息
     * @param id
     * @return BasicVo
     */
    @RequestMapping("/collect-delete.action")
    @ResponseBody
    public BasicVo deleteCollectMessage(int id) {
        return collectMessageService.deleteCollectMessage(id);
    }

    /**
     * @Title: getCommentMessageList
     * @Description: 获取他人评论我博客的消息列表
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/comment-list.json")
    public @ResponseBody BasicEntityVo<Pagination> getCommentMessageList(int userId, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 6;
        }
        return commentMessageService.listCommentMessages(userId, pageNo, pageSize);
    }

    /**
     * @Title: deleteCommentMessage
     * @Description: 删除评论消息
     * @param id
     * @return BasicVo
     */
    @RequestMapping("/comment-delete.action")
    @ResponseBody
    public BasicVo deleteCommentMessage(int id) {
        return commentMessageService.deleteCommentMessage(id);
    }

    /**
     * @Title: listSystemNotice
     * @Description: 系统通知
     * @param userId
     * @return List<NoticeVo>
     */
    @RequestMapping("/notice.json")
    @ResponseBody
    public List<NoticeVo> listSystemNotice(int userId) {
        return noticeService.list(userId);
    }

    /**
     * @Title: deleteNotice
     * @Description: 删除系统通知
     * @param id
     * @return BasicVo
     */
    @RequestMapping("/notice-delete.action")
    @ResponseBody
    public BasicVo deleteNotice(int id) {
        return noticeService.updateNoticeStatus(id);
    }
}
