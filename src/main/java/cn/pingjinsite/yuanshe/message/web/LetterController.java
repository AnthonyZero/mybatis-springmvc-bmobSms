package cn.pingjinsite.yuanshe.message.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.yuanshe.message.service.LetterService;

@Controller
@RequestMapping("/letter")
public class LetterController {

    @Autowired
    private LetterService letterService;

    /**
     * @Title: sendLetter
     * @Description: 发送信件
     * @param ownerId
     * @param userId
     * @param content
     * @return BasicVo
     */
    @RequestMapping("/send.action")
    @ResponseBody
    public BasicVo sendLetter(int ownerId, int userId, String content) {
        return letterService.sendLetter(ownerId, userId, content);
    }

    /**
     * @Title: getSenderLetterList
     * @Description: 我的发送信件列表
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/sender-list.json")
    public @ResponseBody BasicEntityVo<Pagination> getSenderLetterList(int userId, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return letterService.listSenderLetters(userId, pageNo, pageSize);
    }

    /**
     * @Title: updateLetterStatus
     * @Description: 用户删除信件
     * @param letterId
     * @return BasicVo
     */
    @RequestMapping("/delete.action")
    @ResponseBody
    public BasicVo updateLetterStatus(int letterId) {
        return letterService.updateLetterStatus(letterId);
    }

    /**
     * @Title: getReceiverLetterList
     * @Description: 我的收件列表
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/receiver-list.json")
    public @ResponseBody BasicEntityVo<Pagination> getReceiverLetterList(int userId, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return letterService.listReceiverLetters(userId, pageNo, pageSize);
    }

    @RequestMapping("/read.action")
    @ResponseBody
    public BasicVo updateLetterRead(int letterId) {
        return letterService.updateLetterRead(letterId);
    }
}
