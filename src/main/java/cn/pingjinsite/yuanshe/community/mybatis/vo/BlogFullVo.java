package cn.pingjinsite.yuanshe.community.mybatis.vo;

/**
 * @ClassName: BlogFullVo
 * @Description: 这里对于用户而言 的博客完整实体
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月26日 下午11:43:38
 */
public class BlogFullVo {

    /**
     * 博客基本实体 包括作者信息
     */
    private BlogVo blogVo;

    /**
     * 博客额外信息 统计数据
     */
    private BlogExtraVo extra;

    /**
     * 是否点赞过
     */
    private boolean liked;

    /**
     * 是否收藏过
     */
    private boolean collected;

    public BlogVo getBlogVo() {
        return blogVo;
    }

    public void setBlogVo(BlogVo blogVo) {
        this.blogVo = blogVo;
    }

    public BlogExtraVo getExtra() {
        return extra;
    }

    public void setExtra(BlogExtraVo extra) {
        this.extra = extra;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}
