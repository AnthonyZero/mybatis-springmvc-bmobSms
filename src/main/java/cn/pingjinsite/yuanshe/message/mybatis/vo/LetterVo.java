package cn.pingjinsite.yuanshe.message.mybatis.vo;

import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;

public class LetterVo {

    private int id;

    /**
     * 发件人
     */
    private UserVo sender;

    /**
     * 收件人
     */
    private UserVo receiver;

    private String content;

    private String createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserVo getSender() {
        return sender;
    }

    public void setSender(UserVo sender) {
        this.sender = sender;
    }

    public UserVo getReceiver() {
        return receiver;
    }

    public void setReceiver(UserVo receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
