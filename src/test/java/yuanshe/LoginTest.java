package yuanshe;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import cn.pingjinsite.framework.util.DateUtil;
import cn.pingjinsite.yuanshe.index.mybatis.mapper.RegisterMapper;

public class LoginTest extends JUnitTestBase {

    @Autowired
    private RegisterMapper registMapper;

    @Test
    @Rollback(false)
    public void findPhoneNum() {
        Date date = new Date();
        String str1 = "2017-04-23 21:20:10";
        System.out.println(DateUtil.getDateFormat(DateUtil.getDateFormat(str1)));
    }
}
