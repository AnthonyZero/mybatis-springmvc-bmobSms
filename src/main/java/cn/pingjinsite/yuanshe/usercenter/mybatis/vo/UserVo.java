package cn.pingjinsite.yuanshe.usercenter.mybatis.vo;

/**
 * @ClassName: UserVo
 * @Description: 用户信息
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月17日 上午10:12:15
 */
public class UserVo {

    private int id;

    /**
     * 登录账号
     */
    private String identify;

    /**
     * 明文密码
     */
    private String passwordText;

    /**
     * 密文密码
     */
    private String password;

    /**
     * 注册类型
     */
    private String type;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private int sex;

    /**
     * 头像地址
     */
    private String headImg;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 公司
     */
    private String company;

    /**
     * 住址
     */
    private String address;

    /**
     * 简介
     */
    private String synopsis;

    /**
     * 额外信息
     */
    private Object extend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(String passwordText) {
        this.passwordText = passwordText;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }
}
