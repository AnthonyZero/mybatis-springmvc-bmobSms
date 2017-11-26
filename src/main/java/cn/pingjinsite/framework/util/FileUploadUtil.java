package cn.pingjinsite.framework.util;

import java.io.InputStream;

import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.bmob.bson.BSONException;
import cn.pingjinsite.framework.bmob.bson.BSONObject;
import cn.pingjinsite.framework.bmob.restapi.Bmob;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;

/**
 * @ClassName: FileUploadUtil
 * @Description: 文件上传工具类 上传到云存储
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月19日 下午10:09:53
 */
public class FileUploadUtil {

    /**
     * @Title: uploadImageFile
     * @Description: 上传图片到Bmob云端
     * @param fileName 文件名
     * @param in 文件字节流
     * @return BasicVo 返回{code:'',message:''} 如果code为success则message携带图片url
     */
    public static BasicVo uploadImageFile(String fileName, InputStream in) {
        // Bmob初始化
        Bmob.initBmob(BmobSmsConfig.getValue("applicationId"), BmobSmsConfig.getValue("restApiKey"));
        String result = Bmob.uploadBaseFile(fileName, in);
        try {
            BSONObject bo = new BSONObject(result);
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, bo.getString("url"));
        } catch (BSONException e) {
            return new BasicVo(ContextCodeRepostiory.CODE_ERROR, "上传图片失败");
        }
    }
}
