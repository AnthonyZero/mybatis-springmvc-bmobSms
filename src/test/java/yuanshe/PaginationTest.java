package yuanshe;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.pingjinsite.yuanshe.community.mybatis.mapper.LikeMapper;

public class PaginationTest extends JUnitTestBase {

    @Autowired
    private LikeMapper likeMapper;

    @Test
    public void Test() {

        String o = likeMapper.likeStatus(30, 1);
        System.out.println(o);

    }
}
