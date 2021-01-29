package tem.inter.analysis;

import jrx.data.hub.core.utils.DataHupContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;
/**
 * <p>
 * 描述同一个类之间的方法互相调用只有入口方法会加上切面
 * </p>
 *
 * @author LW
 * @since 2021/1/26  11:23
 */
@ServletComponentScan
@SpringBootApplication
@Import(DataHupContextUtils.class)
public class InterAnalysisApp {
    public static void main(String[] args) {
        SpringApplication.run(InterAnalysisApp.class);
    }
}
