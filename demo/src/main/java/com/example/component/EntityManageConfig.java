package com.example.component;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import java.util.Properties;


@Configuration
@AutoConfigureAfter(DataSourceAutoConfig.class)
public class EntityManageConfig {

    @Bean(value = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean manEntityManagerFactory(
            @Named(value = "routeDateSource") HandlerDataSource handlerDataSource
    ) {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(handlerDataSource);
        factory.setPackagesToScan("com");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.enable_lazy_load_no_trans",true);
        factory.setJpaProperties(jpaProperties);
        return factory;
    }

    @Bean(value = "transactionManager")
    public PlatformTransactionManager manTransactionManager(
            @Named(value = "entityManagerFactory") LocalContainerEntityManagerFactoryBean managerFactoryBean) {
        EntityManagerFactory factory = managerFactoryBean.getObject();
        return new JpaTransactionManager(factory);

    }

}
