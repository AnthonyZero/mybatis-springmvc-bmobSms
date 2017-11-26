package cn.pingjinsite.yuanshe.community.mybatis.vo;

import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;

/**
 * @ClassName: BlogVo
 * @Description: 博客文章类
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月22日 下午8:02:19
 */
public class BlogVo {

    private int id;

    // 发布者
    private UserVo user;

    // 博客原文地址
    private String url;

    private String title;

    // 缩略图地址
    private String thumbnail;

    // 内容简述
    private String resume;

    private String classify;

    private String createtime;

    // 自定义时间 根据createtime得出
    private String time;

    private int audit;

    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
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

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
