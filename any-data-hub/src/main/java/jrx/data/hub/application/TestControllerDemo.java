package jrx.data.hub.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestControllerDemo {
    @Value("${test.demo.value:123}")
    private String testProperties;

    @GetMapping("/test")
    public String test() {
        return testProperties;
    }

}
