package com.example.demo;

import com.example.component.HandlerDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

class DemoApplicationTests {

    @Test
    void contextLoads() {
        try {
            DataSource dataSource = DataSourceBuilder.create().build();
            Map<Object, Object> map = new HashMap<>();

            HandlerDataSource handlerDataSource = new HandlerDataSource(dataSource,map);
            Class<?> superclass = handlerDataSource.getClass().getSuperclass();

            superclass.getDeclaredField("");

            System.out.println(superclass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
