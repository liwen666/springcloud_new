package test;

import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.jpa.entity.TableCodeRelation;
import jrx.anyest.table.utils.ReflectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
@WebAppConfiguration
public class BaseTest {

	protected Logger LOG=LoggerFactory.getLogger(getClass());
	
	@Test
	public void testFoo() {

		System.out.println("hello word");
	}

	@Test
	public void name() {
		TableCodeRelation tableCodeRelation = new TableCodeRelation();
		tableCodeRelation.setPrimaryCodeKey("category_id");
		tableCodeRelation.setPrimaryTableChinaName("规则信息表");
		tableCodeRelation.setPrimaryTableName("res_rule_info");
		tableCodeRelation.setSlaveTableChinaName("分类表");
		tableCodeRelation.setSlaveTableName("meta_category");
//		ReflectionUtils.getFieldValue(tableCodeRelation,)
	}
}