package cn.pingjinsite.yuanshe.community.mybatis.vo;

/**
 * @ClassName: BlogRankVo
 * @Description: 博客排行实体类
 * @author pingjin(736252868@qq.com)
 * @date 2017年5月15日 下午4:42:45
 */
public class BlogRankVo {

    private int id;

    private String url;

    private String title;

    private String thumbnail;

    private String resume;

    private String createtime;

    private String nickname;

    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
