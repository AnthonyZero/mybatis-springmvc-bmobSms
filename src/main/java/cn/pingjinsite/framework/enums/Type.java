package cn.pingjinsite.framework.enums;

/**
 * @ClassName: Type
 * @Description: 消息类型 枚举
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 下午3:38:51
 */
public enum Type {

    REGISTER("注册"), ACCOUNT("账号与安全");

    /**
     * name 指代括号中内容
     */
    private String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
