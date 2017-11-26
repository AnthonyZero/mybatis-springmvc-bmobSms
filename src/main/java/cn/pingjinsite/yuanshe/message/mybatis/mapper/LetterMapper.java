package cn.pingjinsite.yuanshe.message.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.message.mybatis.vo.LetterVo;

public interface LetterMapper {

    /**
     * @Title: sendLetter
     * @Description: 发送信件
     * @param ownerId 发送人id
     * @param userId 收件人id
     * @param content 内容
     */
    public void sendLetter(@Param("ownerId") int ownerId, @Param("userId") int userId, @Param("content") String content);

    /**
     * @Title: listSenderLetter
     * @Description: 我的发件列表
     * @param userId
     * @param start
     * @param pageSize
     * @return List<LetterVo>
     */
    public List<LetterVo> listSenderLetter(@Param("userId") int userId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listReceiverLetter
     * @Description: 我的收件列表
     * @param userId
     * @param start
     * @param pageSize
     * @return List<LetterVo>
     */
    public List<LetterVo> listReceiverLetter(@Param("userId") int userId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * @Title: listSenderLetterCount
     * @Description: 我的发件数量
     * @param userId
     * @return int
     */
    public int listSenderLetterCount(@Param("userId") int userId);

    /**
     * @Title: listReceiverLetterCount
     * @Description: 我的收件数量
     * @param userId
     * @return int
     */
    public int listReceiverLetterCount(@Param("userId") int userId);

    /**
     * @Title: updateLetterStatus
     * @Description: 用户删除自己发出的信件
     * @param id
     */
    public void updateLetterStatus(@Param("id") int id);

    /**
     * @Title: updateLetterRead
     * @Description: 用户对收到的信件 标记已读状态
     * @param id
     */
    public void updateLetterRead(@Param("id") int id);
}
