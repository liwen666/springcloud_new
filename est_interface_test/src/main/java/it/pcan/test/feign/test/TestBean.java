package it.pcan.test.feign.test;

import feign.RequestTemplate;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Data
@Builder
public class TestBean {
    private  String name;
   static TestInterceptor gzipInterceptor() {
        return template -> template.header("Accept-Encoding", "gzip");
    }

    public static void main(String[] args) {
        TestInterceptor testInterceptor = gzipInterceptor();

    }

    public TestBean test(){
       return TestBean.builder().name("interface").build();
    }

}
