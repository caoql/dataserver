import java.sql.Connection;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-dao.xml" })
public class DBConnTest {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void test() {
		Connection conn = sqlSessionFactory.openSession().getConnection();
		Assert.assertNotNull("链接为空!!!", conn);
	}

}
