package cn.pingjinsite.framework.enums;

/**
 * @ClassName: Title
 * @Description: 消息标题 枚举
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 下午3:43:28
 */
public enum Title {

    PHONE_REGISTER("手机注册"), OAUTH_REGISTER("第三方账号注册"), UPDATE_PASSWORD("修改密码"), RESET_PASSWORD("找回密码"), CHANGE_PHONE("更换手机号");

    private String name;

    Title(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
