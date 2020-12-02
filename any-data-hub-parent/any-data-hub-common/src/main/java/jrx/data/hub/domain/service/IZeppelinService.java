package jrx.data.hub.domain.service;

import com.alibaba.fastjson.JSONObject;
import jrx.data.hub.domain.enums.DataHubModule;
import jrx.data.hub.infrastructure.entity.MetaDataObjectInfo;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.util.DataResponse;
import kong.unirest.json.JSONArray;
import org.apache.zeppelin.client.ParagraphResult;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2020/11/28  15:58
 */
public interface IZeppelinService {
    /**
     * 获取连接器的列表
     *
     * @return
     * @throws Exception
     */
//    Object listInterpreter() throws Exception;

    /**
     * @param module     job类型
     * @param resourceId jobID
     * @param sqlContent job 内容
     */
    void execJob(DataHubModule module, String resourceId, String sqlContent);

    /**
     * @param note 对应的notebook
     * @param sql  job的内容
     */
    ParagraphResult testJob(String note, String sql);

    /**
     * @param note 对应的notebook
     * @param body job的内容
     */
    DataResponse testFunction(String note, String body) throws Exception;

    /**
     * 创建notebook
     *
     * @param noteName mote名称
     * @return
     */
    DataResponse createNote(String noteName);

    /**
     * 根据notebook名称获取ID
     *
     * @param noteName mote名称
     * @return
     */
    String getNoteId(String noteName, boolean throwException);

    /**
     * 列出所有的notebook
     *
     * @return
     */
    JSONArray listNoteBook();

    /**
     * 创建或者修改JOB
     * 如果zeppelin存在这个job就做修改操作，不存在就新建操作
     *
     * @param noteBookName  zeppelin 的notebook名称
     * @param jobResourceId 数据管理平台job的resourceInfoID
     * @return
     */
    void addOrUpdateJobSql(String noteBookName, String jobResourceId);

    /**
     * 创建或者修改function
     * 如果zeppelin存在这个job就做修改操作，不存在就新建操作
     *
     * @param noteBookName   zeppelin 的notebook名称
     * @param functionInfoId 数据管理平台job的resourceInfoID
     * @return
     */
    void addOrUpdateFunctionSql(String noteBookName, String functionInfoId);


    /**
     * @param noteBookName  zeppelin 的notebook名称
     * @param jobResourceId 数据管理平台job的resourceInfoID
     * @return
     */
    DataResponse updateJobSql(String noteBookName, String jobResourceId);

    /**
     * @param noteBookName  zeppelin 的notebook名称
     * @param jobResourceId 数据管理平台job的resourceInfoID
     * @return
     */
    DataResponse execJob(String noteBookName, String jobResourceId);

    DataResponse execTmpJob(String note, String sql);

    DataResponse execCollectObjectInfoJob(String note, MetaDataObjectInfo metaDataObjectInfo, String sql);

    DataResponse execCollectDataSourceInfoJob(String note, MetaDataSourceInfo metaDataSourceInfo, String sql);

    DataResponse newInterpreter(MetaDataSourceInfo metaDataSourceInfo, JSONObject jsonObject);

    DataResponse listInterpreter() throws Exception;

    void delete(String s, String test);

    Boolean publish(String noteBookName, String resourceId);

    Boolean validFunction(String funName, String scriptsBody);
}
