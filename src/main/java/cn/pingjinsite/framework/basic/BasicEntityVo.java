package cn.pingjinsite.framework.basic;

/**
 * @ClassName: BasicEntityVo
 * @Description: 带返回数据的对象 （数据定义为泛型可以是分页对象或许其他一般对象）
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月13日 下午4:34:55
 */
public class BasicEntityVo<T> {

    /**
     * 返回代码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data = null;

    /**
     * 额外数据
     */
    private String extend;

    public BasicEntityVo() {

    }

    public BasicEntityVo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BasicEntityVo(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BasicEntityVo(String code, String message, T data, String extend) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.extend = extend;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the entityVo
     */
    public T getData() {
        return data;
    }

    /**
     * @param entityVo the entityVo to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return the extend
     */
    public String getExtend() {
        return extend;
    }

    /**
     * @param extend the extend to set
     */
    public void setExtend(String extend) {
        this.extend = extend;
    }
}
