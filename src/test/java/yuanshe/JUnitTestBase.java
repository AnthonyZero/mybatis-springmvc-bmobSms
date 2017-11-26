package yuanshe;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName: JUnitTestBase
 * @Description: Dao测试基础类 加载环境
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月13日 上午9:42:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-beans.xml" })
public class JUnitTestBase extends AbstractTransactionalJUnit4SpringContextTests {

    @Override
    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        // TODO Auto-generated method stub
        super.setDataSource(dataSource);
    }

}
