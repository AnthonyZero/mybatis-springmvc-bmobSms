package cn.pingjinsite.yuanshe.message.mybatis.vo;

/**
 * @ClassName: NoticeVo
 * @Description: 通知
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 下午3:14:38
 */
public class NoticeVo {

    private int id;

    private int userId;

    private String type;

    private String title;

    private String content;

    private String createtime;

    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
