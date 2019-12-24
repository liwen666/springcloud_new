package jrx.batch.dataflow.domain.nacos;

import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class HttpTaskServiceNameSpaceTest {
    @Autowired
    private HttpJobServiceNameSpace httpTaskServiceNameSpace;

    @Test
    public void name() {
        String job_server_test = httpTaskServiceNameSpace.getJobServerUrl("job_serve_test");
        System.out.println(job_server_test);
    }
}