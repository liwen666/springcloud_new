#创建bean的注解
##直接实例化bean
@Bean  
    - 在配置文件中使用@Bean创建bean时会直接创建
    注：使用@Autowire注入bean时会优先选择以接口名对应的bean  如果没有则选择子类进行注入
@Bean 
@ConditionalOnBean(Billy.class)
    - 在配置文件中使用以上两个注解实例化bean时会根据扫描的的billy.class结果来确定是否实例化
        注：使用@Autowire注入bean时会优先选择以接口名对应的bean  如果没有则选择子类进行注入
@Bean         
@ConditionalOnMissingBean
    - 在配置文件中使用以上两个注解实例化bean时会更具方法返回值确定bean是否有实例化，如果没有自动实例化一个bean,子类的实例化不算
    
@Bean         
@ConditionalOnMissingBean
@ConditionalOnEnabledEndpoint