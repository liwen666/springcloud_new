package jrx.data.hub.domain.runner;

import com.alibaba.fastjson.JSON;
import jrx.data.hub.domain.enums.DataHubModule;
import jrx.data.hub.domain.service.impl.ZeppelinServiceImpl;
import jrx.data.hub.infrastructure.zeppelin.IJobOperator;
import jrx.data.hub.infrastructure.zeppelin.config.ZeppelinClientProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.zeppelin.client.ClientConfig;
import org.apache.zeppelin.client.ZeppelinClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ZeppelinRunner implements ApplicationRunner {
    @Autowired
    private IJobOperator jobOperator;

    @Autowired
    private ZeppelinServiceImpl zeppelinService;

    @Autowired
    private ZeppelinClientProperties zeppelinClientProperties;

    @Override
    public void run(ApplicationArguments args) {

        try {
            initZClient();
            initFunctionNoteBook();
//            jobOperator.open();
            log.info("zepplin connect  and initial success!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void initZClient() throws Exception {
        ClientConfig clientConfig = new ClientConfig(zeppelinClientProperties.getZeppelinServerUrl());
        log.info("----创建zeppelin client开始 zeppelinClientProperties,{}----", JSON.toJSONString(zeppelinClientProperties));
        ZeppelinClient zClient = new ZeppelinClient(clientConfig);
        zClient.login(zeppelinClientProperties.getUsername(), zeppelinClientProperties.getPassword());
        zeppelinService.setzClient(zClient);
    }

    private void initFunctionNoteBook() throws Exception {
        zeppelinService.getNoteId("/" + DataHubModule.FUNCTION.name(), false);
//        zeppelinService.getzClient().
//        zeppelinService.getzClient().createNote("/" + DataHubModule.FUNCTION.name());
    }
}
