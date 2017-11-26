package cn.pingjinsite.yuanshe.community.mybatis.vo;

/**
 * @ClassName: BlogExtraVo
 * @Description: 博客额外信息 统计数据
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月26日 下午11:45:53
 */
public class BlogExtraVo {

    /**
     * 博客id
     */
    private int id;

    /**
     * 点赞数
     */
    private int like;

    /**
     * 收藏数
     */
    private int collect;

    /**
     * 讨论数
     */
    private int discuss;

    /**
     * 分享数
     */
    private int share;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getDiscuss() {
        return discuss;
    }

    public void setDiscuss(int discuss) {
        this.discuss = discuss;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }
}
