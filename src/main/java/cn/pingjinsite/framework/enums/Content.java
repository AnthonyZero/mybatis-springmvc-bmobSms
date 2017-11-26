package cn.pingjinsite.framework.enums;

/**
 * @ClassName: Content
 * @Description: 消息内容枚举
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 下午3:57:10
 */
public enum Content {

    REGISTER("欢迎使用猿社APP,您已成为猿社社区的一员."), UPDATE_PASSWORD("您已成功修改密码,请妥善保管"), RESET_PASSWORD("您已通过手机号成功重置了密码"), CHANGE_PHONE("您已成功更换了新手机号作为登录账号");

    private String name;

    Content(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
