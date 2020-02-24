package jrx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.temp.jpa.jpa.dao.ReportFieldDao;
import com.temp.jpa.jpa.entity.ReportFieldEntity;
import com.temp.jpa.jpa.jpautil.DataViewInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class DataViewTest {
    @Test
    public void name() {
        String data ="{\n" +
                "\t\"id\": 82,\n" +
                "\t\"name\": \"用户实体1\",\n" +
                "\t\"code\": \"tp_user1\",\n" +
                "\t\"description\": null,\n" +
                "\t\"sourceId\": 44,\n" +
                "\t\"source\": null,\n" +
                "\t\"type\": 1,\n" +
                "\t\"model\": \"{\\\"用户姓名 (用户实体1)\\\":{\\\"sqlType\\\":\\\"VARCHAR\\\",\\\"visualType\\\":\\\"string\\\",\\\"modelType\\\":\\\"category\\\",\\\"code\\\":\\\"user1.username\\\"},\\\"身份证号 (用户实体1)\\\":{\\\"sqlType\\\":\\\"VARCHAR\\\",\\\"visualType\\\":\\\"string\\\",\\\"modelType\\\":\\\"category\\\",\\\"code\\\":\\\"user1.uuid\\\"},\\\"采集维度2 (用户实体1)\\\":{\\\"sqlType\\\":\\\"VARCHAR\\\",\\\"visualType\\\":\\\"string\\\",\\\"modelType\\\":\\\"category\\\",\\\"code\\\":\\\"user1.cj_wd2\\\"},\\\"采集维度1 (用户实体1)\\\":{\\\"sqlType\\\":\\\"VARCHAR\\\",\\\"visualType\\\":\\\"string\\\",\\\"modelType\\\":\\\"category\\\",\\\"code\\\":\\\"user1.cj_wd1\\\"}}\",\n" +
                "\t\"config\": \"\",\n" +
                "\t\"primary\": true,\n" +
                "\t\"tableCode\": \"user1\"\n" +
                "}";
        DataViewInfo parse = JSON.parseObject(data, DataViewInfo.class);
        String model = parse.getModel();
        JSONObject jsonObject = JSON.parseObject(model);

    }

    @Autowired
    private ReportFieldDao reportFieldDao;

    @Test
    public void insert() {

        String data ="{\n" +
                "\t\"id\": 82,\n" +
                "\t\"name\": \"用户实体1\",\n" +
                "\t\"code\": \"tp_user1\",\n" +
                "\t\"description\": null,\n" +
                "\t\"sourceId\": 44,\n" +
                "\t\"source\": null,\n" +
                "\t\"type\": 1,\n" +
                "\t\"model\": \"{\\\"用户姓名 (用户实体1)\\\":{\\\"sqlType\\\":\\\"VARCHAR\\\",\\\"visualType\\\":\\\"string\\\",\\\"modelType\\\":\\\"category\\\",\\\"code\\\":\\\"user1.username\\\"},\\\"身份证号 (用户实体1)\\\":{\\\"sqlType\\\":\\\"VARCHAR\\\",\\\"visualType\\\":\\\"string\\\",\\\"modelType\\\":\\\"category\\\",\\\"code\\\":\\\"user1.uuid\\\"},\\\"采集维度2 (用户实体1)\\\":{\\\"sqlType\\\":\\\"VARCHAR\\\",\\\"visualType\\\":\\\"string\\\",\\\"modelType\\\":\\\"category\\\",\\\"code\\\":\\\"user1.cj_wd2\\\"},\\\"采集维度1 (用户实体1)\\\":{\\\"sqlType\\\":\\\"VARCHAR\\\",\\\"visualType\\\":\\\"string\\\",\\\"modelType\\\":\\\"category\\\",\\\"code\\\":\\\"user1.cj_wd1\\\"}}\",\n" +
                "\t\"config\": \"\",\n" +
                "\t\"primary\": true,\n" +
                "\t\"tableCode\": \"user1\"\n" +
                "}";
        DataViewInfo parse = JSON.parseObject(data, DataViewInfo.class);
        String model = parse.getModel();
        JSONObject jsonObject = JSON.parseObject(model);
//        jsonObject.get
        ReportFieldEntity build = new ReportFieldEntity();
        build.setResourceId(parse.getId());
//        build.setResourceType(parse.get);



    }
}
