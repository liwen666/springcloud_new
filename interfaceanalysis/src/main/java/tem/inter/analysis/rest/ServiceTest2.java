package tem.inter.analysis.rest;

import org.springframework.stereotype.Service;
import tem.inter.analysis.aop.annotation.InterfaceAnalysisRetry;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/1/27  19:16
 */
@Service
public class ServiceTest2 {


    @InterfaceAnalysisRetry
    public String test3() {
        return "tesst";
    }
}
