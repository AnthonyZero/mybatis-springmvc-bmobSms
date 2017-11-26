/**   
* @Package com.jiapei.framework.commom.content 
* @Description: TODO(用一句话描述该文件做什么) 
* @author xfwang   
* @date 2016年8月3日 上午11:28:02 
* @version V1.0   
*/
package cn.pingjinsite.framework.content;

/**
 * @ClassName: ContextMessageRepostiory
 * @Description: 后台返回JSON时操作信息
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月13日 下午4:21:16
 */
public class ContextMessageRepostiory {

    /**
     * 操作数据库时，因数据库引起的[RuntimeException]异常，统一用该信息提示！
     */
    public static String MESSAGE_ERROR_DATABASE = "系统异常，请稍后重新尝试";

    /**
     * 参数错误，请刷新页面重试！
     */
    public static String MESSAGE_ERROR_PARAM = "参数错误，请重新输入！";
}
