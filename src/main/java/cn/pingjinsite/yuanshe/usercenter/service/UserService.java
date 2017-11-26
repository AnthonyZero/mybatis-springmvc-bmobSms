package cn.pingjinsite.yuanshe.usercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.yuanshe.usercenter.mybatis.mapper.OwnerBlogMapper;
import cn.pingjinsite.yuanshe.usercenter.mybatis.mapper.OwnerCollectMapper;
import cn.pingjinsite.yuanshe.usercenter.mybatis.mapper.UserMapper;
import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserStatisticsVo;
import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OwnerBlogMapper ownerBlogMapper;

    @Autowired
    private OwnerCollectMapper ownerCollectMapper;

    /**
     * @Title: getUserVoById
     * @Description: 根据id获取用户信息
     * @param id
     * @return UserVo
     */
    public UserVo getUserVoById(int id) {
        return userMapper.getUserById(id);
    }

    /**
     * @Title: getUserRelation
     * @Description: 查看用户之间的关系
     * @param ownerId
     * @param userId
     * @return String 1为已经关注 2取消关注的状态 返回为null对象从没关注过
     */
    public String getUserRelation(int ownerId, int userId) {
        return userMapper.getUserRelation(ownerId, userId);
    }

    /**
     * @Title: buildUserRelation
     * @Description: 第一次建立（新增）关注关系
     * @param ownerId
     * @param userId
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo buildUserRelation(int ownerId, int userId) {
        try {
            userMapper.insertUserRelation(ownerId, userId);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "关注成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "关注用户时,数据库异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }

    /**
     * @Title: updateUserRelation
     * @Description: 修改用户关系
     * @param ownerId
     * @param userId
     * @param relation 1为再次关注 2取消关注
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo updateUserRelation(int ownerId, int userId, int relation) {
        try {
            userMapper.updateUserRelation(ownerId, userId, relation);
            if (relation == 1) {
                return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "关注成功");
            } else {
                return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "已取消关注");
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "修改用户关系时,数据库异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }

    /**
     * @Title: getOwnerFansList
     * @Description: 获取粉丝列表
     * @param userId 用户标识
     * @param pageNo 页码数
     * @param pageSize 每页大小
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> getOwnerFansList(int userId, int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<UserVo> list = userMapper.listFans(userId, (pageNo - 1) * pageSize, pageSize);
            int count = userMapper.listFansCount(userId);
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                Pagination page = new Pagination();
                page.setPageNo(pageNo);
                page.setPageSize(pageSize);
                page.setStartNum((pageNo - 1) * pageSize);
                page.setTotalCount(count);
                page.setList(list);
                vo.setData(page);
            } else {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_ERROR, "还没有人关注过你");
            }
        } catch (RuntimeException ex) {
            MyLog4j.error(getClass(), "获取粉丝列表时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: getOwnerConcernsList
     * @Description: 关注列表
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    public BasicEntityVo<Pagination> getOwnerConcernsList(int userId, int pageNo, int pageSize) {
        BasicEntityVo<Pagination> vo = null;
        try {
            List<UserVo> list = userMapper.listConcerns(userId, (pageNo - 1) * pageSize, pageSize);
            int count = userMapper.listConcernCount(userId);
            if (list != null && list.size() > 0) {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_SUCCESS, "获取数据成功");
                Pagination page = new Pagination();
                page.setPageNo(pageNo);
                page.setPageSize(pageSize);
                page.setStartNum((pageNo - 1) * pageSize);
                page.setTotalCount(count);
                page.setList(list);
                vo.setData(page);
            } else {
                vo = new BasicEntityVo<>(ContextCodeRepostiory.CODE_ERROR, "您还没关注过任何人");
            }
        } catch (RuntimeException ex) {
            MyLog4j.error(getClass(), "获取关注列表时,数据库异常", true, ex);
            vo = new BasicEntityVo<Pagination>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
        return vo;
    }

    /**
     * @Title: getUserStatistics
     * @Description: 获取用户额外信息统计数据
     * @param userId
     * @return UserStatisticsVo
     */
    public UserStatisticsVo getUserStatistics(int userId) {
        UserStatisticsVo vo = new UserStatisticsVo();
        vo.setBlogNum(ownerBlogMapper.listBlogCount(userId));
        vo.setConcernNum(userMapper.listConcernCount(userId));
        vo.setFansNum(userMapper.listFansCount(userId));
        vo.setCollectNum(ownerCollectMapper.listCollectsCount(userId));
        return vo;
    }
}
