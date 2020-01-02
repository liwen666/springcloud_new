#通过${} 的方式创建监听器是可以通过java类的方式覆盖掉配置文件的属性

@Configuration
public class EnvironmentConfig implements EnvironmentAware, BeanDefinitionRegistryPostProcessor, ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentConfig.class);

    @Override
    public void setEnvironment(Environment env) {
        StandardEnvironment standardEnvironment = (StandardEnvironment) env;
            String topic = standardEnvironment.getProperty("suning.rocketmq.topic");
        System.out.println("topic---->"+topic);
        MutablePropertySources propertySources = standardEnvironment.getPropertySources();
        PropertySource backupSource = new MapPropertySource("overideCfg", new HashMap<String, Object>(){{put("suning.rocketmq.topic","ANY_BATCH_JOB_RESULT_STATUE_TOPIC");}});
        propertySources.addFirst(backupSource);
    }
