package cn.pingjinsite.yuanshe.index.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.content.ContextMessageRepostiory;
import cn.pingjinsite.framework.logger.MyLog4j;
import cn.pingjinsite.yuanshe.index.mybatis.mapper.IndexMapper;
import cn.pingjinsite.yuanshe.index.mybatis.vo.VersionVo;

@Service
public class IndexService {

    @Autowired
    private IndexMapper indexMapper;

    /**
     * @Title: userFeedback
     * @Description: 用户反馈意见
     * @param map
     * @return BasicVo
     */
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public BasicVo userFeedback(Map<String, String> map) {
        try {
            indexMapper.clientFeedback(map);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "反馈成功");
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            MyLog4j.error(getClass(), "用户提出反馈意见插入数据时，数据库异常", true, e);
            return new BasicVo(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }

    /**
     * @Title: getBaseAppInfo
     * @Description: 获取应用信息
     * @return Map<String,String>
     */
    public Map<String, String> getBaseAppInfo() {
        return indexMapper.getAppBaseInfo();
    }

    /**
     * @Title: versionCheckUpdate
     * @Description: 版本更新
     * @param appid
     * @param version
     * @return BasicVo
     */
    public BasicEntityVo<VersionVo> versionCheckUpdate(String appid, String version) {
        try {
            VersionVo vo = indexMapper.getUptodateVersion(appid);
            int Dvalue = vo.getVersionCode().compareTo(version);
            if (Dvalue > 0) {
                return new BasicEntityVo<VersionVo>(ContextCodeRepostiory.CODE_SUCCESS, "获取最新版本成功", vo);
            } else {
                return new BasicEntityVo<VersionVo>(ContextCodeRepostiory.CODE_ERROR, "当前版本已是最新");
            }
        } catch (RuntimeException e) {
            MyLog4j.error(getClass(), "获取最新版本信息时，数据库异常", true, e);
            return new BasicEntityVo<VersionVo>(ContextCodeRepostiory.CODE_EXCEPTION, ContextMessageRepostiory.MESSAGE_ERROR_DATABASE);
        }
    }
}
