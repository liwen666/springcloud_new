package nacos.temp.client.remote;

import nacos.test.intr.ITestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/1/24  10:14
 */
@RestController
public class RemoteService {
    @Autowired
    private ITestController iTestController;
    @GetMapping("/client")
    public void getName(){
        String kkk = iTestController.getName("kkk");
        Map<String, String> stringStringMap = iTestController.testJob("111");
        System.out.println("-------------"+kkk);
    }

}
