package cn.pingjinsite.framework.basic;

/**
 * @ClassName: BasicVo
 * @Description: 基础对象以code-message对应
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月13日 下午4:33:14
 */
public class BasicVo {

    /**
     * 返回代码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    public BasicVo() {

    }

    public BasicVo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
