package com.example.thymeleafdemo;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.net.URL;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ThymeleafDemoApplication {

    public static void main(String[] args) {

        String configPath = getConfigPath();
        System.out.println(configPath);

//        /D:/idea2018workspace/springcloud_new/html/bin/config
//file:/D:/idea2018workspace/springcloud_new/html/target/html-1.0-SNAPSHOT.jar!/BOOT-INF/classes!/bin/config

        ConfigurableApplicationContext run = SpringApplication.run(ThymeleafDemoApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
//        run.close();
        DataSource bean = beanFactory.getBean(DataSource.class);
        System.out.println(bean);



    }



    public static void initSystemCfg() {

        String os = System.getProperty("os.name");
        URL resource = ThymeleafDemoApplication.class.getResource("");
        String dataPath = resource.getPath();
        System.out.println("原始路径"+dataPath);
        if(os.toLowerCase().startsWith("win")){
            String replace1 = dataPath.substring(0, dataPath.indexOf("/com/")).replace("file:","").replace("/target", "").replace("/classes","");
            String config= replace1+"/bin/config";

            JrxMaxwellConfig.globleConfig.put("jrxconfig",config);
            System.out.println("系统启动配置文件路径是"+config);
        }else{
            String configPath =dataPath.replace("lib","")+"bin/config";
            JrxMaxwellConfig.globleConfig.put("jrxconfig",configPath);
            System.out.println("系统启动配置文件路径是"+configPath);
        }
    }
    public static String getConfigPath() {
        if(null==JrxMaxwellConfig.globleConfig.get("jrxconfig")){
            initSystemCfg();
        }
        return JrxMaxwellConfig.globleConfig.getProperty("jrxconfig");
    }

}
