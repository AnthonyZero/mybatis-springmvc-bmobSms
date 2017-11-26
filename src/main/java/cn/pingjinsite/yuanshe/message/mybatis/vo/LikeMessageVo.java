package cn.pingjinsite.yuanshe.message.mybatis.vo;

/**
 * @ClassName: LikeMessageVo
 * @Description: 他人点赞消息
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月27日 下午7:13:34
 */
public class LikeMessageVo {

    /**
     * 点赞表的唯一标识
     */
    private int id;

    /**
     * 博客标识
     */
    private int blogId;

    private String title;

    private String thumbnail;

    /**
     * 点赞人的信息
     */
    private int userId;

    private String nickname;

    private String headImg;

    private String occupation;

    private String company;

    /**
     * 点赞时间
     */
    private String createtime;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

}
