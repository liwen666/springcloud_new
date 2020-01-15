package it.pcan.test.feign.test;

import feign.RequestTemplate;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class TestInterceptorImpl implements  TestInterceptor {
    RequestTemplate requestTemplate;

    @Override
    public void apply2(RequestTemplate var1) {

        requestTemplate= var1;
    }
}
