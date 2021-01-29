package tem.inter.analysis.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tem.inter.analysis.aop.annotation.InterfaceAnalysisRetry;

import java.util.Date;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/1/27  19:16
 */
@Service
public class ServiceTest {
    @Autowired
    private ServiceTest2 serviceTest2;
    @InterfaceAnalysisRetry
    public String test(Date date, int i, long l, int aggregate) {
        test2();
        serviceTest2.test3();
        return "tesst";
    }


    @InterfaceAnalysisRetry
    public String test2() {
        return "tesst";
    }
}
