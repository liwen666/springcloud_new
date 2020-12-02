package jrx.data.hub.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import jrx.data.hub.domain.common.ModelUpdateAssistant;
import jrx.data.hub.domain.complier.ScriptLanguage;
import jrx.data.hub.domain.enums.DataHubModule;
import jrx.data.hub.domain.exception.DataException;
import jrx.data.hub.domain.exception.ZeppelinCallExeption;
import jrx.data.hub.domain.service.*;
import jrx.data.hub.infrastructure.entity.*;
import jrx.data.hub.infrastructure.zeppelin.IJobOperator;
import jrx.data.hub.infrastructure.zeppelin.config.ZeppelinClientProperties;
import jrx.data.hub.util.DataResponse;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.zeppelin.client.ParagraphResult;
import org.apache.zeppelin.client.Status;
import org.apache.zeppelin.client.ZeppelinClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 描述和zeppelin交互的服务入口类
 * </p>
 *
 * @author LW
 * @since 2020/11/28  15:59
 */
@Slf4j
@Service
@Transactional
public class ZeppelinServiceImpl implements IZeppelinService {

    private Map<String, String> noteNameIdMap = new ConcurrentHashMap<>();

    private ZeppelinClient zClient;

    @Autowired
    private ZeppelinClientProperties zeppelinClientProperties;

    @Autowired
    private IJobOperator zeppelinJobOperator;

    @Autowired
    private IMetaWorkInfoService iMetaWorkInfoService;
    @Autowired
    private IMetaJobObjectService iMetaJobObjectService;
    @Autowired
    private IMetaJobInfoService iMetaJobInfoService;

    @Autowired
    private IMetaFunctionService functionService;
    @Autowired
    private IMetaFunctionInfoService functionInfoService;

    @Autowired
    private MetaDataSourceInfoServiceImpl metaDataSourceInfoServiceImpl;

    @Autowired
    private MetaDataObjectInfoServiceImpl metaDataObjectInfoServiceImpl;

//    @Override
//    public Object listInterpreter() {
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = zClient.listInterpreter();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }

    @Override
    public void execJob(DataHubModule module, String resourceId, String sqlContent) {
        try {
            zeppelinJobOperator.executeTmpJob(module, resourceId, sqlContent);
        } catch (Exception e) {
        }
    }

    @Override
    public ParagraphResult testJob(String note, String sql) {
        try {
            MetaWorkInfo metaWorkInfo = checkNote(note);
            String paragraphId = zClient.addParagraph(metaWorkInfo.getZplNotebookId(), "temp", sql);
            ParagraphResult paragraphResult = zClient.executeParagraph(metaWorkInfo.getZplNotebookId(), paragraphId);
            zClient.deleteParagraph(metaWorkInfo.getZplNotebookId(), paragraphId);
            log.info(JSON.toJSONString(paragraphResult));
            return paragraphResult;
        } catch (Exception e) {
            log.error("----job测试异常----{}", e);
        }
        return null;
    }

    @Override
    public DataResponse testFunction(String note, String body) {
        DataResponse dataResponse = DataResponse.of();
        try {
            String zplNotebookId = getNoteId(note, false);
            String paragraphId = zClient.addParagraph(zplNotebookId, "temp", body);
            ParagraphResult paragraphResult = zClient.executeParagraph(zplNotebookId, paragraphId);
            if (paragraphResult.getStatus() == Status.ERROR) {
                throw new DataException(paragraphResult.getResultInText());
            }
            zClient.deleteParagraph(zplNotebookId, paragraphId);
            dataResponse.setData("OK");
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
        return dataResponse;
    }

    @Override
    public DataResponse createNote(String noteName) {
        String note;
        try {
            note = zClient.createNote(noteName);
            log.info("create notebook success ! noteName:{},noteId:{}", noteName, note);
        } catch (Exception e) {
            log.error("创建notebook异常：noteBookName {},error{}", noteName, e);
            return DataResponse.error(e);
        }
        return DataResponse.success(note);
    }

    @Override
    public String getNoteId(String noteName, boolean throwException) {
        if (!noteName.startsWith("/")) {
            noteName = "/" + noteName;
        }
        AtomicReference<String> noteId = new AtomicReference<>(noteNameIdMap.get(noteName));
        if (StringUtils.isEmpty(noteId.get())) {
            JSONArray objects = listNoteBook();
            String finalNoteName = noteName;
            objects.forEach(e -> {
                if (finalNoteName.equals(((JSONObject) e).get("path"))) {
                    noteId.set((String) ((JSONObject) e).get("id"));
                }
            });
        }
        if (noteId.get() == null) {
            if (throwException) {
                throw new ZeppelinCallExeption("zeppelin不存在对应的notebook noteName:" + noteName);
            } else {
                createNote(noteName);
            }
        }

        noteNameIdMap.put(noteName, noteId.get());
        return noteId.get();
    }

    @Override
    public JSONArray listNoteBook() {
        JSONArray noteList;
        try {
            noteList = zClient.getNoteList();
        } catch (Exception e) {
            log.error("---调用zeppelin 查询notebook列表异常---");
            throw new ZeppelinCallExeption("调用zeppelin 查询notebook列表异常");
        }
        return noteList;
    }


    @Override
    public void addOrUpdateJobSql(String noteBookName, String jobResourceId) {
        MetaWorkInfo one = iMetaWorkInfoService.getOne(Wrappers.<MetaWorkInfo>lambdaQuery().eq(MetaWorkInfo::getNotePath, noteBookName));
        String noteId = getNoteId(noteBookName, true);
        if (null == noteId) {
            throw new ZeppelinCallExeption("zeppelin 不存在对应的note book" + noteBookName);
        }
        if (null == one) {
            log.error("data-hub do not exist notebook " + noteBookName);
            throw new DataException("data-hub do not exist metawork " + noteBookName);
        }
        String zplNotebookId = one.getZplNotebookId();
        MetaJobInfo metaJobInfo = iMetaJobInfoService.getOne(Wrappers.<MetaJobInfo>lambdaQuery().eq(MetaJobInfo::getResourceId, jobResourceId));
        MetaJobObject jobObject = iMetaJobObjectService.getOne(Wrappers.<MetaJobObject>lambdaQuery().eq(MetaJobObject::getResourceId, jobResourceId).orderBy(true, false, MetaJobObject::getCreateTime).last(" limit 1"));
        try {
            if (null == metaJobInfo.getZplJobId()) {
                String jobId = zClient.addParagraph(zplNotebookId, noteBookName, jobObject.getSqlContent());
                metaJobInfo.setZplJobId(jobId);
                iMetaJobInfoService.update(metaJobInfo);
                log.info("添加JOB到zeppelin成功！ zepJobId:{}", jobId);
            } else {
                zClient.updateParagraph(zplNotebookId, metaJobInfo.getZplJobId(), noteBookName, jobObject.getSqlContent());
                log.info("修改job 到zeppelin成功！zepJobId:{}", metaJobInfo.getZplJobId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdateFunctionSql(String noteBookName, String functionInfoId) {

    }

    @Override
    public DataResponse updateJobSql(String noteBookName, String jobResourceId) {
        return null;
    }

    @Override
    public DataResponse execJob(String noteBookName, String jobResourceId) {
        return null;
    }

    public DataResponse execTmpJob(String note, String sql) {
        DataResponse dataResponse = DataResponse.of();
        try {
            MetaWorkInfo metaWorkInfo = checkNote(note);
            String paragraphId = zClient.addParagraph(metaWorkInfo.getZplNotebookId(), "temp", sql);
            ParagraphResult paragraphResult = zClient.executeParagraph(metaWorkInfo.getZplNotebookId(), paragraphId);
            if (paragraphResult.getStatus() == Status.ERROR) {
                log.error("----execTmpJob执行异常----{}", paragraphResult.getResultInText());
                dataResponse.setReason(paragraphResult.getResultInText());
                dataResponse = DataResponse.error();
                return dataResponse;
            }
            dataResponse.setData(paragraphResult.getResults());
            zClient.deleteParagraph(metaWorkInfo.getZplNotebookId(), paragraphId);
            return dataResponse;
        } catch (Exception e) {
            log.error("----execTmpJob执行异常----{}", e);
            dataResponse = DataResponse.error();
            dataResponse.setReason(e.getMessage());
        }
        return dataResponse;
    }

    /**
     * 执行固定的段落
     * show create table
     *
     * @param note
     * @param sql
     * @return
     */
    public DataResponse execCollectObjectInfoJob(String note, MetaDataObjectInfo metaDataObjectInfo, String sql) {
        DataResponse dataResponse = DataResponse.of();
        try {
            String noteId = getNoteId(note, false);
            if (null == noteId) {
                throw new ZeppelinCallExeption("zeppelin 不存在对应的note book" + note);
            }
            if (StringUtils.isEmpty(metaDataObjectInfo.getZplJobId())) {
                String paragraphId = zClient.addParagraph(noteId, "temp", sql);
                metaDataObjectInfo.setZplJobId(paragraphId);
                metaDataObjectInfoServiceImpl.update(metaDataObjectInfo);
            }
            ParagraphResult paragraphResult = zClient.executeParagraph(noteId, metaDataObjectInfo.getZplJobId());
            if (paragraphResult.getStatus() == Status.ERROR) {
                log.error("----execFixObjectInfoJob执行异常----{}", paragraphResult.getResultInText());
                dataResponse.setReason(paragraphResult.getResultInText());
                dataResponse = DataResponse.error();
                return dataResponse;
            }
            dataResponse.setData(paragraphResult.getResults());
            return dataResponse;
        } catch (Exception e) {
            log.error("----execTmpJob执行异常----{}", e);
            dataResponse = DataResponse.error();
            dataResponse.setReason(e.getMessage());
        }
        return dataResponse;
    }

    public DataResponse execCollectDataSourceInfoJob(String note, MetaDataSourceInfo metaDataSourceInfo, String sql) {
        DataResponse dataResponse = DataResponse.of();
        try {
            String noteId = getNoteId(note, false);
            if (null == noteId) {
                throw new ZeppelinCallExeption("zeppelin 不存在对应的note book" + note);
            }
            if (StringUtils.isEmpty(metaDataSourceInfo.getZplJobId())) {
                String paragraphId = zClient.addParagraph(noteId, "temp", sql);
                metaDataSourceInfo.setZplJobId(paragraphId);
                metaDataSourceInfoServiceImpl.update(metaDataSourceInfo);
            }
            ParagraphResult paragraphResult = zClient.executeParagraph(noteId, metaDataSourceInfo.getZplJobId());
            if (paragraphResult.getStatus() == Status.ERROR) {
                log.error("----execFixDataSourceInfoJob执行异常----{}", paragraphResult.getResultInText());
                dataResponse.setReason(paragraphResult.getResultInText());
                dataResponse = DataResponse.error();
                return dataResponse;
            }
            dataResponse.setData(paragraphResult.getResults());
            return dataResponse;
        } catch (Exception e) {
            log.error("----execTmpJob执行异常----{}", e);
            dataResponse = DataResponse.error();
            dataResponse.setReason(e.getMessage());
        }
        return dataResponse;
    }

    private MetaWorkInfo checkNote(String note) {
        MetaWorkInfo metaWorkInfo = null;
        String noteId = getNoteId(note, true);
        if (null != noteId) {
            metaWorkInfo = new MetaWorkInfo();
            metaWorkInfo.setNotePath(note);
            metaWorkInfo.setZplNotebookId(noteId);
            return metaWorkInfo;
        }
        try {
            metaWorkInfo = iMetaWorkInfoService.getOne(Wrappers.<MetaWorkInfo>lambdaQuery().eq(MetaWorkInfo::getNotePath, note));
            if (metaWorkInfo == null) {
                String note1 = zClient.createNote(note);
                metaWorkInfo = new MetaWorkInfo();
                metaWorkInfo.setNotePath(note);
                metaWorkInfo.setZplNotebookId(note1);
                ModelUpdateAssistant.setCreate(metaWorkInfo);
                iMetaWorkInfoService.save(metaWorkInfo);
                log.info("----创建notebook---note-book:{}", JSON.toJSONString(metaWorkInfo));
            }
            return metaWorkInfo;
        } catch (Exception e) {
            log.error("检查zeppelin notebook 异常error:{}", e);
        }
        return metaWorkInfo;
    }

    @Override
    public DataResponse newInterpreter(MetaDataSourceInfo metaDataSourceInfo, com.alibaba.fastjson.JSONObject jsonObject) {
        DataResponse response = DataResponse.of();

        com.alibaba.fastjson.JSONObject dbConfigJson = com.alibaba.fastjson.JSONObject.parseObject(metaDataSourceInfo.getDbConfigJson());
        com.alibaba.fastjson.JSONObject jdbcTemple = new com.alibaba.fastjson.JSONObject();
        com.alibaba.fastjson.JSONObject perpertiesJson = null;
        com.alibaba.fastjson.JSONObject urlJson = null;
        com.alibaba.fastjson.JSONObject userJson = null;
        com.alibaba.fastjson.JSONObject passwordJson = null;
        com.alibaba.fastjson.JSONObject driverJson = null;
        switch (metaDataSourceInfo.getDbType()) {
            case MYSQL:
                jdbcTemple = jsonObject.getJSONObject("jdbc");
                perpertiesJson = jdbcTemple.getJSONObject("properties");
                perpertiesJson = converJson(perpertiesJson);

                jdbcTemple.put("id", metaDataSourceInfo.getSourceCode());
                jdbcTemple.put("name", metaDataSourceInfo.getSourceName());
                urlJson = perpertiesJson.getJSONObject("default.url");
                urlJson.put("value", dbConfigJson.getString("url"));

                userJson = perpertiesJson.getJSONObject("default.user");
                userJson.put("value", dbConfigJson.getString("user"));

                passwordJson = perpertiesJson.getJSONObject("default.password");
                passwordJson.put("value", dbConfigJson.getString("password"));

                driverJson = perpertiesJson.getJSONObject("default.driver");
                driverJson.put("value", "com.mysql.jdbc.Driver");

                com.alibaba.fastjson.JSONArray dependenciesArr = jdbcTemple.getJSONArray("dependencies");
                com.alibaba.fastjson.JSONObject dependenciesJson = new com.alibaba.fastjson.JSONObject();
                dependenciesJson.put("groupArtifactVersion", zeppelinClientProperties.getJdbcLibUrl());
                dependenciesJson.put("local", false);
                dependenciesArr.add(dependenciesJson);

                break;
            case GREENPLUM:
                jdbcTemple = jsonObject.getJSONObject("jdbc");
                perpertiesJson = jdbcTemple.getJSONObject("properties");
                perpertiesJson = converJson(perpertiesJson);

                jdbcTemple.put("id", metaDataSourceInfo.getSourceCode());
                jdbcTemple.put("name", metaDataSourceInfo.getSourceName());
                urlJson = perpertiesJson.getJSONObject("default.url");
                urlJson.put("value", dbConfigJson.getString("url"));

                userJson = perpertiesJson.getJSONObject("default.user");
                userJson.put("value", dbConfigJson.getString("user"));

                passwordJson = perpertiesJson.getJSONObject("default.password");
                passwordJson.put("value", dbConfigJson.getString("password"));

                driverJson = perpertiesJson.getJSONObject("default.driver");
                driverJson.put("value", "org.postgresql.Driver");
            case FLINK:

            default:
                break;
        }
        String messageJSON = jdbcTemple.toJSONString();
        log.info("向zeppelin发送请求添加interpreters数据:{}", messageJSON);
        try {
            zClient.newInterpreterSettings(messageJSON);
        } catch (Exception e) {
            log.error("添加interpreters数据失败", e);
            response = DataResponse.error();
        }
        return response;
    }

    /**
     * propertyName-->name
     * defaultValue-->value
     *
     * @return
     */
    private com.alibaba.fastjson.JSONObject converJson(com.alibaba.fastjson.JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            com.alibaba.fastjson.JSONObject value = jsonObject.getJSONObject(key);
            value.put("name", value.getString("propertyName"));
            value.put("value", value.getString("defaultValue"));
        }
        return jsonObject;
    }

    @Override
    public DataResponse listInterpreter() {
        DataResponse dataResponse = DataResponse.of();
        List list = Lists.newArrayList();
        JSONObject json = null;
        try {
            json = zClient.listInterpreter();
            log.info("获取Interpreter模板:{}", json);
            Map map = json.toMap();
            list.add(map);
            dataResponse.setData(list);
        } catch (Exception e) {
            log.error("获取Interpreter模板失败", e);
            dataResponse = DataResponse.error();
        }
        return dataResponse;
    }

    @Override
    public void delete(String nodeName, String jobResourceId) {
        String noteId = getNoteId(nodeName, true);
        MetaJobInfo metaJobInfo = iMetaJobInfoService.getOne(Wrappers.<MetaJobInfo>lambdaQuery().eq(MetaJobInfo::getResourceId, jobResourceId));
        try {
            zClient.deleteParagraph(noteId, metaJobInfo.getZplJobId());
            iMetaJobInfoService.removeJobInfo(metaJobInfo.getResourceId());
        } catch (Exception e) {
            log.error("删除job 异常！e:{}", e);
            throw new ZeppelinCallExeption("删除job异常：");
        }
    }

    @Override
    public Boolean publish(String noteBookName, String resourceId) {
        String noteId = getNoteId(noteBookName, true);
        MetaFunctionInfo functionInfo = functionInfoService.getById(resourceId);
        MetaFunction function = functionService.getOne(Wrappers.<MetaFunction>lambdaQuery().eq(MetaFunction::getResourceId, resourceId).orderBy(true, false, MetaFunction::getCreateTime).last("limit 1"));
        String zplFunctionId = functionInfo.getZplFunctionId();
        validFunction(functionInfo.getName(), function.getScripts());
        try {
            if (org.apache.commons.lang3.StringUtils.isBlank(zplFunctionId)) {
                if (function.getLanguage().equals(ScriptLanguage.SCALA)) {
                    String script = function.getScripts();
                    zplFunctionId = zClient.addParagraph(noteId, DataHubModule.FUNCTION.name(), script);
                    functionInfo.setZplFunctionId(zplFunctionId);
                    functionInfoService.update(functionInfo);
                } else {
                    throw new DataException("暂不支持函数语言: " + function.getLanguage());
                }
                log.info("添加function到zeppelin成功！ zplFunctionId:{}", zplFunctionId);
            } else {
                zClient.updateParagraph(noteId, functionInfo.getZplFunctionId(), noteBookName, function.getScripts());
                log.info("修改function到zeppelin成功！ZplFunctionId:{}", functionInfo.getZplFunctionId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZeppelinCallExeption("发布function异常");
        }
        return true;
    }

    public void setzClient(ZeppelinClient zClient) {
        this.zClient = zClient;
    }

    public ZeppelinClient getzClient() {
        return zClient;
    }

    public Boolean validFunction(String funName, String scriptsBody) {
        int lastIndex = scriptsBody.lastIndexOf("\"");
        String functionName = scriptsBody.substring(scriptsBody.lastIndexOf("\"", lastIndex-1) + 1, lastIndex);
        if (!funName.equals(functionName)) {
            throw new DataException("函数的名称不对成，请修正");
        }
        return true;
    }

}
