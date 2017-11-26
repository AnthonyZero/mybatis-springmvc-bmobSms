package cn.pingjinsite.yuanshe.usercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.framework.util.DateUtil;
import cn.pingjinsite.yuanshe.community.mybatis.vo.BlogVo;
import cn.pingjinsite.yuanshe.usercenter.mybatis.mapper.OwnerCollectMapper;

@Service
public class OwnerCollectService {

    @Autowired
    private OwnerCollectMapper collectMapper;

    /**
     * @Title: getOwnerCollects
     * @Description: 获取用户自己的收藏列表
     * @param userId 用户标识
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> getOwnerCollects(int userId, int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<BlogVo> list = collectMapper.listCollects(userId, (pageNo - 1) * pageSize, pageSize);
            int count = collectMapper.listCollectsCount(userId);
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                for (BlogVo blog : list) {
                    blog.setTime(DateUtil.getCustomDateTime(blog.getCreatetime()));
                }
                Pagination page = new Pagination();
                page.setPageNo(pageNo);
                page.setPageSize(pageSize);
                page.setStartNum((pageNo - 1) * pageSize);
                page.setTotalCount(count);
                page.setList(list);
                vo.setData(page);
            } else {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_ERROR, "没有获取到数据");
            }
        } catch (RuntimeException ex) {
            MyLog4j.error(getClass(), "获取我的收藏博客列表时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }
}
