package cn.pingjinsite.yuanshe.community.mybatis.vo;

import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;

public class CommentVo {

    private int id;

    private int blogId;

    private int type;

    private String content;

    private String time;

    /**
     * 评论用户
     */
    private UserVo from;

    /**
     * 评论目标用户
     */
    private UserVo to;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public UserVo getFrom() {
        return from;
    }

    public void setFrom(UserVo from) {
        this.from = from;
    }

    public UserVo getTo() {
        return to;
    }

    public void setTo(UserVo to) {
        this.to = to;
    }
}
