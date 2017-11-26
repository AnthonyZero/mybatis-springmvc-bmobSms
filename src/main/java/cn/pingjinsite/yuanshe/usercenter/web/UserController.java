package cn.pingjinsite.yuanshe.usercenter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.basic.BasicVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.framework.content.ContextCodeRepostiory;
import cn.pingjinsite.yuanshe.usercenter.mybatis.vo.UserVo;
import cn.pingjinsite.yuanshe.usercenter.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Title: getUserVo
     * @Description: 根据id获取用户信息
     * @param id
     * @return UserVo
     */
    @RequestMapping("/info.json")
    @ResponseBody
    public UserVo getUserVo(int id) {
        return userService.getUserVoById(id);
    }

    /**
     * @Title: userConcern
     * @Description: 用户关注事件
     * @param ownerId
     * @param userId
     * @return BasicVo
     */
    @RequestMapping("/concern.action")
    @ResponseBody
    public BasicVo userConcern(int ownerId, int userId) {
        String flag = userService.getUserRelation(ownerId, userId);
        if (flag == null) {
            // 从来都没有关注过的记录
            return userService.buildUserRelation(ownerId, userId);
        } else if (flag.equals("1")) {
            // 已经关注了
            return new BasicVo(ContextCodeRepostiory.CODE_SUCCESS, "已经关注过");
        } else {
            // 取消关注的状态
            return userService.updateUserRelation(ownerId, userId, 1);
        }
    }

    /**
     * @Title: userCancalConcern
     * @Description: 用户取消关注
     * @param ownerId
     * @param userId
     * @return BasicVo
     */
    @RequestMapping("/cancel-concern.action")
    @ResponseBody
    public BasicVo userCancalConcern(int ownerId, int userId) {
        return userService.updateUserRelation(ownerId, userId, 2);
    }

    /**
     * @Title: getFansList
     * @Description: 粉丝列表
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/fans-list.json")
    public @ResponseBody BasicEntityVo<Pagination> getFansList(int userId, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return userService.getOwnerFansList(userId, pageNo, pageSize);
    }

    /**
     * @Title: getConcernsList
     * @Description: 关注列表
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/concerns-list.json")
    public @ResponseBody BasicEntityVo<Pagination> getConcernsList(int userId, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return userService.getOwnerConcernsList(userId, pageNo, pageSize);
    }
}
