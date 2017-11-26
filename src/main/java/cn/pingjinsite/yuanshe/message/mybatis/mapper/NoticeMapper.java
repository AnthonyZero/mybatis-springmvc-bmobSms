package cn.pingjinsite.yuanshe.message.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.message.mybatis.vo.NoticeVo;

/**
 * @ClassName: NoticeMapper
 * @Description: 通知接口
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 下午4:01:54
 */
public interface NoticeMapper {

    /**
     * @Title: insertNotice
     * @Description: 插入一条通知
     * @param vo
     */
    public void insertNotice(NoticeVo vo);

    /**
     * @Title: list
     * @Description: 系统通知
     * @param userId
     * @return List<NoticeVo>
     */
    public List<NoticeVo> list(@Param("userId") int userId);

    /**
     * @Title: deleteNotice
     * @Description: 删除系统通知 标志已读
     * @param id
     */
    public void deleteNotice(@Param("id") int id);
}
