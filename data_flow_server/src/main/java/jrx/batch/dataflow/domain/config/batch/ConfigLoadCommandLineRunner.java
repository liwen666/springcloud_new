package jrx.batch.dataflow.domain.config.batch;

import jrx.batch.dataflow.domain.enums.JrxBatchEnums;
import jrx.batch.dataflow.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.deployer.spi.app.AppDeployer;
import org.springframework.cloud.deployer.spi.core.RuntimeEnvironmentInfo;
import org.springframework.cloud.deployer.spi.task.TaskLauncher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yxy
 * @date 2018/10/25
 */
@Component
public class ConfigLoadCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ConfigurableApplicationContext applicationContext;
    private static final Logger logger = LoggerFactory.getLogger(ConfigLoadCommandLineRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        Assert.notNull(applicationContext, "上下文内容不为空");
        logger.info("加载监听器");
        AppDeployer appDeployer = applicationContext.getBean(AppDeployer.class);
        Map<String, Object> env = new HashMap<>();
        RuntimeEnvironmentInfo info = appDeployer.environmentInfo();
        env.put("ImplementationName", info.getImplementationName());
        env.put("ImplementationVersion", info.getImplementationVersion());
        env.put("JavaVersion", info.getJavaVersion());
        env.put("PlatformApiVersion", info.getPlatformApiVersion());
        env.put("PlatformClientVersion", info.getPlatformClientVersion());
        env.put("PlatformHostVersion", info.getPlatformHostVersion());
        env.put("PlatformType", info.getPlatformType());
        env.put("SpiVersion", info.getSpiVersion());
        env.put("SpringBootVersion", info.getSpringBootVersion());
        env.put("PlatformSpecificInfo", info.getPlatformSpecificInfo());
        logger.info("使用AppDeployer环境：\n{}", env);
        TaskLauncher taskLauncher = applicationContext.getBean(TaskLauncher.class);
        logger.info("使用taskLauncher，{}", taskLauncher);
        boolean win = SystemUtils.isWin();
        String filePath = "";
        if (win) {
            filePath = JrxBatchProperties.properties.get(JrxBatchEnums.WIN_JAR_HOME.name()); // 上传后的路径
        } else{
            filePath = JrxBatchProperties.properties.get(JrxBatchEnums.LINUX_JAR_HOME.name()); // 上传后的路径

        }
        if(StringUtils.isEmpty(filePath)){
            filePath = JrxBatchProperties.properties.get(JrxBatchEnums.JAR_HOME_DEFAULT.name()); // 上传后的路径
        }
        logger.info("===APP 上传到节点的路径是：{}",filePath);

    }
}
