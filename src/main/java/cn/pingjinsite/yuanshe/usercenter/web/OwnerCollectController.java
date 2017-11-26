package cn.pingjinsite.yuanshe.usercenter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pingjinsite.framework.basic.BasicEntityVo;
import cn.pingjinsite.framework.common.Pagination;
import cn.pingjinsite.yuanshe.usercenter.service.OwnerCollectService;

@Controller
@RequestMapping("/ownercollect")
public class OwnerCollectController {

    @Autowired
    private OwnerCollectService collectService;

    /**
     * @Title: getCoachList
     * @Description: 获取我收藏列表
     * @param userId 用户标识
     * @param pageNo 页码
     * @param pageSize 每页数据量
     * @return BasicEntityVo<Pagination>
     */
    @RequestMapping("/list.json")
    public @ResponseBody BasicEntityVo<Pagination> getCoachList(int userId, int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 6;
        }
        return collectService.getOwnerCollects(userId, pageNo, pageSize);
    }
}
