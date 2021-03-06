package test;

import com.temp.jpa.ApplicationStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
@WebAppConfiguration
public class BaseTest {

	protected Logger LOG=LoggerFactory.getLogger(getClass());
	
	@Test
	public void testFoo() {
		System.out.println("hello word");
	}
}