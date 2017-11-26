package cn.pingjinsite.yuanshe.index.mybatis.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.pingjinsite.yuanshe.index.mybatis.vo.VersionVo;

public interface IndexMapper {

    /**
     * @Title: clientFeedback
     * @Description: 插入反馈信息
     * @param map void
     */
    public void clientFeedback(Map<String, String> map);

    /**
     * @Title: getAppBaseInfo
     * @Description: 获取应用信息
     * @return Map<String,String>
     */
    public Map<String, String> getAppBaseInfo();

    /**
     * @Title: getUptodateVersion
     * @Description: 获取最新版本信息
     * @param appid
     * @return VersionVo
     */
    public VersionVo getUptodateVersion(@Param("appid") String appid);
}
