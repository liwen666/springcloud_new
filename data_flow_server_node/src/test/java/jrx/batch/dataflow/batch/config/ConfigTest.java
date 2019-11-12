package jrx.batch.dataflow.batch.config;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.domain.config.batch.JrxBatchProperties;
import jrx.batch.dataflow.domain.enums.JrxBatchEnums;
import jrx.batch.dataflow.infrastructure.dao.TaskExecutionMapper;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.dataflow.core.AppRegistration;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.cloud.dataflow.core.TaskDefinition;
import org.springframework.cloud.dataflow.core.TaskDeployment;
import org.springframework.cloud.dataflow.registry.repository.AppRegistrationRepository;
import org.springframework.cloud.dataflow.server.config.features.LocalPlatformProperties;
import org.springframework.cloud.dataflow.server.repository.TaskDefinitionRepository;
import org.springframework.cloud.dataflow.server.repository.TaskDeploymentRepository;
import org.springframework.cloud.dataflow.server.service.TaskExecutionService;
import org.springframework.cloud.deployer.spi.core.AppDeploymentRequest;
import org.springframework.cloud.deployer.spi.local.LocalDeployerProperties;
import org.springframework.cloud.deployer.spi.local.LocalTaskLauncher;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
//@EnableConfigurationProperties(LocalDeployerProperties.class)
public class ConfigTest {

    @Test
    public void jrxbat() {
        String jarhome = JrxBatchProperties.properties.get(JrxBatchEnums.JAR_HOME_DEFAULT.name());
        String nodename = JrxBatchProperties.properties.get(JrxBatchEnums.NODE_NAME.name());
        System.out.println(jarhome);
        System.out.println(nodename);
    }
}