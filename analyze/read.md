##修改启动配置文件
   （1）java -jar -Dspring.config.location=D:\config\config.properties springbootrestdemo-0.0.1-SNAPSHOT.jar 
   （2） @SpringBootApplication
   @PropertySource(value={"file:config.properties"})
   public class SpringbootrestdemoApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(SpringbootrestdemoApplication.class, args);
       }
   }
    (3) 启动时指定生效的配置文件  java -jar xxx.jar --spring.profiles.active=prod  
       配置文件以以下方式命名
        application-dev.properties