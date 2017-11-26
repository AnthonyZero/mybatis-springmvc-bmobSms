package cn.pingjinsite.yuanshe.index.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.framework.util.FileUploadUtil;
import cn.pingjinsite.framework.util.RequestUtil;
import cn.pingjinsite.yuanshe.index.mybatis.vo.VersionVo;
import cn.pingjinsite.yuanshe.index.service.IndexService;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * @Title: feedback
     * @Description: 用户反馈
     * @param request
     * @param file
     * @return
     * @throws IOException BasicVo
     */
    @RequestMapping("/feedback")
    @ResponseBody
    public BasicVo feedback(HttpServletRequest request, @RequestParam(required = false, value = "file") MultipartFile file) throws IOException {
        Map<String, String> map = RequestUtil.getParamAsMap(request);
        if (file == null) {
            // 没上传截图
            map.put("url", "");
            return indexService.userFeedback(map);
        } else {
            // 上传了截图
            BasicVo vo = FileUploadUtil.uploadImageFile("feedback" + map.get("userId") + ".jpg", file.getInputStream());
            if ("success".equals(vo.getCode())) {
                map.put("url", vo.getMessage());
                return indexService.userFeedback(map);
            } else {
                return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "图片上传服务器失败");
            }
        }
    }

    /**
     * @Title: getBaseAppInfo
     * @Description: 应用信息
     * @return Map<String,String>
     */
    @RequestMapping("/app")
    @ResponseBody
    public Map<String, String> getBaseAppInfo() {
        return indexService.getBaseAppInfo();
    }

    /**
     * @Title: versionCheckUpdate
     * @Description: 版本更新
     * @param appid
     * @param version
     * @return BasicVo
     */
    @RequestMapping("/check-update")
    @ResponseBody
    public BasicEntityVo<VersionVo> versionCheckUpdate(String appid, String version) {
        return indexService.versionCheckUpdate(appid, version);
    }
}
