package jrx.data.hub.domain.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:22
 */

@EnableTransactionManagement
@Configuration
@MapperScan("jrx.data.hub.infrastructure.dao")
public class MyBatisPlusConfig {

    // mybatis-plus分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}