package jrx.data.hub.zeppelin;

import com.alibaba.fastjson.JSON;
import jrx.data.hub.AnyDataHubApplication;
import jrx.data.hub.domain.enums.DataHubModule;
import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.domain.service.IZeppelinService;
import jrx.data.hub.domain.service.impl.ZeppelinServiceImpl;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.util.DataResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * zeppelin接口测试类
 */
@ActiveProfiles("local_zch")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnyDataHubApplication.class)
public class ZeppelinServiceTest {
    @Autowired
    private IZeppelinService zeppelinService;

    @Autowired
    private ZeppelinServiceImpl zeppelinServiceImpl;

//    @Test
//    public void name() {
//        try {
//            JSONObject o = (JSONObject) zeppelinService.listInterpreter();
//            Map<String, Object> stringObjectMap = o.toMap();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void testExecJob() {
//        zeppelinService.execJob(DataHubModule.DATASOURCE,);
    }

    @Test
    public void createJob() {
        zeppelinService.testJob(DataHubModule.DATASOURCE.name(), "%flink\n" +
                "\n" +
                "val data = benv.fromElements(\"hello world\", \"hello flink\", \"hello hadoop\")\n" +
                "data.flatMap(line => line.split(\"\\\\s\"))\n" +
                "             .map(w => (w, 1))\n" +
                "             .groupBy(0)\n" +
                "             .sum(1)\n" +
                "             .print()");
    }

    @Test
    public void executeScala() {
        zeppelinService.testJob(DataHubModule.DATASOURCE.name(), "%flink\n" +
                "\n" +
                "class ScalaUpper extends ScalarFunction {\n" +
                "  def eval(str: String) = str.toUpperCase\n" +
                "}\n" +
                "\n" +
                "btenv.registerFunction(\"scala_upper\", new ScalaUpper())");
    }

    @Before
    public void init() throws Exception {
//        ClientConfig clientConfig = new ClientConfig("http://10.0.20.12:8087/");
////        ClientConfig clientConfig = new ClientConfig("http://192.168.137.111:10000/");
//        ZeppelinClient zClient = new ZeppelinClient(clientConfig);
////        zClient.login("admin", "admin");
//        zClient.login("admin", "pw123");
//        this.zClient = zClient;
    }

    @Test
    public void testCreateAndRemoveInterpreter() throws Exception {
//        zClient.listInterpreter();
//        zClient.listInterpreterSettings();
//        zClient.getInterpreterSetting("flink");
//        String settingId = RandomStringUtils.randomNumeric(4);
//
//        zClient.removeInterpreterSetting(settingId);
    }

    @Test
    public void addInterpreters() throws Exception {
        //mysql
        DataResponse dataResponse = zeppelinService.listInterpreter();
        com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseArray(JSON.toJSONString(dataResponse.getData()));
        System.out.println(jsonArray.toJSONString());

        MetaDataSourceInfo metaDataSourceInfo = new MetaDataSourceInfo();
        metaDataSourceInfo.setDataSourceId("123456");
        metaDataSourceInfo.setSourceName("mysql_test_zhang");
        metaDataSourceInfo.setDbType(DbType.MYSQL);
        metaDataSourceInfo.setDbConfigJson("{\"url\":\"jdbc:mysql://10.0.8.10:3306/anyest3_financial_cloud_000\",\"user\":\"any\",\"password\":\"anywd1234\",\"extend\":{\"param1\":\"value1\"}}");
        zeppelinService.newInterpreter(metaDataSourceInfo, jsonArray.getJSONObject(0));
        //greenplum
//        DataResponse dataResponse = zeppelinService.listInterpreter();
//        com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseArray(JSON.toJSONString(dataResponse.getData()));
//        System.out.println(jsonArray.toJSONString());
//
//        MetaDataSourceInfo metaDataSourceInfo = new MetaDataSourceInfo();
//        metaDataSourceInfo.setDataSourceId("123456");
//        metaDataSourceInfo.setSourceName("gp_test_zhang");
//        metaDataSourceInfo.setDbType(DbType.GREENPLUM);
//        metaDataSourceInfo.setDbConfigJson("{\"url\":\"jdbc:postgresql://172.16.103.105:5432/aaaaaa\",\"user\":\"gpadmin\",\"password\":\"gpadmin\",\"extend\":{\"param1\":\"value1\"}}");
//        zeppelinService.newInterpreter(metaDataSourceInfo, jsonArray.getJSONObject(0));
    }

    @Test
    public void connectTest() throws Exception {
//        DataResponse dataResponse =  zeppelinServiceImpl.execTmpJob("/mysql_test_zhang","%mysql_test_zhang\n "+
//                "\n" +
//                "select 1 from dual");
//        System.out.println("================================"+JSON.toJSONString(dataResponse));

        DataResponse dataResponse = zeppelinServiceImpl.execTmpJob("/gp_test_zhang", "%gp_test_zhang\n " +
                "\n" +
                "SELECT table_name,column_name,udt_name,character_maximum_length,numeric_precision FROM information_schema.columns WHERE table_name='md_cm_bp_age'");
        System.out.println("================================" + JSON.toJSONString(dataResponse));
    }

    @Test
    public void getMysqlDDL() throws Exception {
        DataResponse dataResponse = zeppelinServiceImpl.execTmpJob("/mysql_test_zhang", "%mysql_test_zhang\n " +
                "\n" +
                "show create table anyest3_financial_cloud_000.meta_function_info");
        System.out.println("================================" + JSON.toJSONString(dataResponse));
    }


    /**
     * 创建notebook
     */
    @Test
    public void createNote() {
        zeppelinService.createNote("test/note/aaa");
    }

    /**
     * 列出所有notebook
     */
    @Test
    public void listNote() {
        zeppelinService.listNoteBook();
    }

    /**
     * 根据notename获取id
     */
    @Test
    public void getNodeId() {
        String noteId = zeppelinService.getNoteId("test/note/aaa", true);
        System.out.println(noteId);
    }

    @Test
    public void addjob() {
        zeppelinService.addOrUpdateJobSql("test/note/aaa", "test");
    }

    @Test
    public void delete() {
        zeppelinService.delete("test/note/aaa", "test");
    }

}