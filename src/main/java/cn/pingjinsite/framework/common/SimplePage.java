package cn.pingjinsite.framework.common;

public class SimplePage implements Paginable {

    public static final int DEF_COUNT = 15;

    protected int pageNo = 1;

    protected int pageSize = 20;

    protected int startNum = 0;

    protected int totalCount = 0;

    public SimplePage() {
    }

    /**
     * 构造器
     * 
     * @param pageNo 页码
     * @param pageSize 每页几条数据
     * @param totalCount 总共几条数据
     */
    public SimplePage(int pageNo, int pageSize, int totalCount) {
        setTotalCount(totalCount);
        setPageSize(pageSize);
        setPageNo(pageNo);
        adjustPageNo();
    }

    /**
     * 检查页码 checkPageNo
     * 
     * @param pageNo
     * @return if pageNo==null or pageNo<1 then return 1 else return pageNo
     */
    public static int cpn(Integer pageNo) {
        return (pageNo == null || pageNo < 1) ? 1 : pageNo;
    }

    /**
     * 调整页码，使不超过最大页数
     */
    public void adjustPageNo() {
        if (pageNo == 1) {
            return;
        }
        int tp = getTotalPage();
        if (pageNo > tp) {
            pageNo = tp;
        }
    }

    /**
     * 总共几条数据
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 总共几页
     */
    public int getTotalPage() {
        int totalPage = totalCount / pageSize;
        if (totalPage == 0 || totalCount % pageSize != 0) {
            totalPage++;
        }
        return totalPage;
    }

    /**
     * 每页几条数据
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 获得页码
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 是否第一页 在java对象转变为JSON对象时会执行 诸如此类的都是如此
     */
    public boolean isFirstPage() {
        return pageNo <= 1;
    }

    /**
     * 是否最后一页
     */
    public boolean isLastPage() {
        return pageNo >= getTotalPage();
    }

    /**
     * 下一页页码
     */
    public int getNextPage() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    /**
     * 上一页页码
     */
    public int getPrePage() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }

    /**
     * if pageNo < 1 then pageNo=1
     * 
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
    }

    /**
     * if pageSize< 1 then pageSize=DEF_COUNT
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = DEF_COUNT;
        } else {
            this.pageSize = pageSize;
        }
    }

    /**
     * if totalCount<0 then totalCount=0
     * 
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        if (totalCount < 0) {
            this.totalCount = 0;
        } else {
            this.totalCount = totalCount;
        }
    }

    /**
     * @return startNum
     */
    public int getStartNum() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * @param startNum
     */
    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }
}
