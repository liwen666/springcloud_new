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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	@Test
	public void test(){
		String len="^[A-Z].*[\\.]$";//表示以字母开始句号结尾
		String aaa= "asss111";
		System.out.println(aaa.matches("^[a-z].*"));
	}


	@Test
	public void testStream() {
		List<Integer> aaa = new ArrayList<>();
		aaa.add(2);
		aaa.add(1);
		aaa.add(100);;
		System.out.println(aaa.stream().sorted().collect(Collectors.toList()));


		for(int a:aaa){
			System.out.println(a);
			break;
		}

	}
}