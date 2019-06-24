package com.jsp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author hafiz.zhang
 * @description:
 * @date Created in 2018/6/7 20:07.
 */
public class DemoSpringApplicationRunListener extends EventPublishingRunListener {

    public DemoSpringApplicationRunListener(SpringApplication application, String[] args) {
        super(application, args);
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        super.started(context);
        System.out.println("======================================================");
        System.out.println(1);

    }

    @Override
    public int getOrder() {
        System.out.println("======================================================");
        System.out.println(2);
        return super.getOrder();
    }

    @Override
    public void starting() {
        System.out.println("======================================================");
        System.out.println(3);
        super.starting();
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("======================================================");
        System.out.println(4);
        super.environmentPrepared(environment);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("======================================================");
        System.out.println(5);
        super.contextPrepared(context);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("======================================================");
        System.out.println(6);
        super.contextLoaded(context);
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("======================================================");
        System.out.println(7);
        super.running(context);
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("======================================================");
        System.out.println(8);
        super.failed(context, exception);
    }
}