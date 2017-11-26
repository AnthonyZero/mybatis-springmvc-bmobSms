package cn.pingjinsite.yuanshe.usercenter.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;

public interface UserMapper {

    /**
     * @Title: getUserById
     * @Description: 根据id获取用户信息
     * @param id
     * @return UserVo
     */
    public UserVo getUserById(int id);

    /**
     * @Title: getUserIdByPhone
     * @Description: 根据手机号码获取用户id
     * @param phone
     * @return int
     */
    public int getUserIdByPhone(@Param("phone") String phone);

    /**
     * @Title: getUserRelation
     * @Description: 查看用户之间的关系
     * @param ownerId 自己唯一标识
     * @param userId 需要判断的他人标识
     * @return String 1为已经关注 2取消关注的状态 null 从没关注过
     */
    public String getUserRelation(@Param("ownerId") int ownerId, @Param("userId") int userId);

    /**
     * @Title: insertUserRelation
     * @Description: 第一次关注的时候 添加用户联系
     * @param ownerId
     * @param userId
     */
    public void insertUserRelation(@Param("ownerId") int ownerId, @Param("userId") int userId);

    /**
     * @Title: updateUserRelation
     * @Description: 修改用户关系
     * @param ownerId
     * @param userId
     * @param relation 1为再次关注 2取消关注
     */
    public void updateUserRelation(@Param("ownerId") int ownerId, @Param("userId") int userId, @Param("relation") int relation);

    /**
     * @Title: listFans
     * @Description: 粉丝列表
     * @param userId
     * @param start
     * @param pageSize
     * @return List<UserVo>
     */
    public List<UserVo> listFans(@Param("userId") int userId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listFansCount
     * @Description: 粉丝数
     * @param userId
     * @return int
     */
    public int listFansCount(@Param("userId") int userId);

    /**
     * @Title: listConcerns
     * @Description: 关注列表
     * @param userId
     * @param start
     * @param pageSize
     * @return List<UserVo>
     */
    public List<UserVo> listConcerns(@Param("userId") int userId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listConcernCount
     * @Description: 关注数
     * @param userId
     * @return int
     */
    public int listConcernCount(@Param("userId") int userId);
}
