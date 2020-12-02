package jrx.data.hub.infrastructure.zeppelin;

import jrx.data.hub.domain.enums.DataHubModule;
import jrx.data.hub.domain.exception.InitializationException;
import jrx.data.hub.domain.model.job.MetaJobObject;
import jrx.data.hub.infrastructure.zeppelin.config.ZeppelinClientProperties;
import jrx.data.hub.infrastructure.dto.JobExecuteResult;
import jrx.data.hub.infrastructure.zeppelin.utils.ZeppelinExecuteResultUtils;
import jrx.data.hub.util.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.zeppelin.client.ClientConfig;
import org.apache.zeppelin.client.ZeppelinClient;
import org.apache.zeppelin.client.ParagraphResult;
import org.apache.zeppelin.client.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Songyc5
 * @since 2020/11/25
 */
@Slf4j
public class ZeppelinJobOperator implements IJobOperator {

    private ZeppelinClientProperties properties;

    private ZeppelinClient zeppelinClient;

    private Map<DataHubModule, String> temporaryNoteMap = new HashMap<>(DataHubModule.values().length);

    private Map<String, String> temporaryParagraphMap = new ConcurrentHashMap<>();


    public ZeppelinJobOperator(ZeppelinClient zeppelinClient, ZeppelinClientProperties zeppelinClientProperties) {
        this.zeppelinClient = zeppelinClient;
        this.properties = zeppelinClientProperties;
    }

    public ZeppelinJobOperator(ZeppelinClientProperties zeppelinClientProperties) {
        this.properties = zeppelinClientProperties;

    }

    @Override
    public void open() throws InitializationException {
        try {
            ClientConfig clientConfig = new ClientConfig("http://10.0.20.12:8087/");//http://10.0.20.12:8087/  http://172.16.102.11:8087/
            zeppelinClient = new ZeppelinClient(clientConfig);
//            zeppelinClient.login(properties.getUsername(), properties.getPassword());
            zeppelinClient.login("admin", "pw123");
        } catch (Exception e) {
            throw new InitializationException(e.getMessage(), e);
        }
    }

    @Override
    public DataResponse<JobExecuteResult> submitWork(String workId, List<MetaJobObject> jobObjectList) {
        return null;
    }

    @Override
    public DataResponse<JobExecuteResult> removeWork(String workId) {
        return null;
    }

    @Override
    public DataResponse<JobExecuteResult> submitJob(String workId, MetaJobObject jobObject) {
        return null;
    }

    @Override
    public DataResponse<JobExecuteResult> removeJob(String workId) {
        return null;
    }

    @Override
    public DataResponse<JobExecuteResult> executeTmpJob(DataHubModule module, String paragraphId, String sqlContent) throws Exception {

        String noteId = temporaryNoteMap.get(module);

        if (StringUtils.isEmpty(noteId)) {
            noteId = zeppelinClient.createNote("/" + module.name());
            temporaryNoteMap.put(module, noteId);
        }

        try {
            zeppelinClient.addParagraph(noteId, paragraphId, sqlContent);
        } catch (Exception e) {
            e.printStackTrace();
            zeppelinClient.updateParagraph(noteId, paragraphId, "", sqlContent);
        }

        ParagraphResult paragraphResult = zeppelinClient.executeParagraph(noteId, paragraphId);

        JobExecuteResult jobExecuteResult = ZeppelinExecuteResultUtils.parseResult(paragraphResult);

        DataResponse<JobExecuteResult> response = DataResponse.of();
        if (paragraphResult.getStatus() == Status.ERROR) {
            response = DataResponse.error();
            response.setData(jobExecuteResult);
            return response;
        }

        response.setData(jobExecuteResult);
        return response;
    }

    @Override
    public void close() {

    }
}
