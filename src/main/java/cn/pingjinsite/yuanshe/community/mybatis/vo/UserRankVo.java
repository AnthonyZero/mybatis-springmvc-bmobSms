package cn.pingjinsite.yuanshe.community.mybatis.vo;

public class UserRankVo {

    private int userId;

    private String nickname;

    private String headImg;

    /**
     * 博客数量
     */
    private String blogNum;

    /**
     * 评论次数
     */
    private String commentNum;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(String blogNum) {
        this.blogNum = blogNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }
}
