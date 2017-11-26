package cn.pingjinsite.yuanshe.usercenter.mybatis.vo;

public class UserStatisticsVo {

    /**
     * 文章数目
     */
    private int blogNum;

    /**
     * 关注数
     */
    private int concernNum;

    /**
     * 粉丝数
     */
    private int fansNum;

    /**
     * 收藏数
     */
    private int collectNum;

    public int getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(int blogNum) {
        this.blogNum = blogNum;
    }

    public int getConcernNum() {
        return concernNum;
    }

    public void setConcernNum(int concernNum) {
        this.concernNum = concernNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }
}
