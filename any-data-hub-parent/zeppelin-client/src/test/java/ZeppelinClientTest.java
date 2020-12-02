import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.zeppelin.client.ClientConfig;
import org.apache.zeppelin.client.ZeppelinClient;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author Songyc5
 * @date: 2020/11/19
 */
public class ZeppelinClientTest {


    private ZeppelinClient zClient;

    @Before
    public void init() throws Exception {
        ClientConfig clientConfig = new ClientConfig("http://10.0.20.12:8087/");
//        ClientConfig clientConfig = new ClientConfig("http://192.168.137.111:10000/");
        ZeppelinClient zClient = new ZeppelinClient(clientConfig);
        zClient.login("admin", "admin");
//        zClient.login("admin", "pw123");
        this.zClient = zClient;
    }

    @Test
    public void testCreateAndRemoveInterpreter() throws Exception {
        zClient.listInterpreter();
        zClient.listInterpreterSettings();
        zClient.getInterpreterSetting("flink");
        String settingId = RandomStringUtils.randomNumeric(4);

        String messageJSON = getInterpreterSettingMessage(settingId);
        zClient.newInterpreterSettings(messageJSON);
        zClient.removeInterpreterSetting(settingId);
    }

    public static String getInterpreterSettingMessage(String randomKey) throws IOException {
        String settingsTemplateJSON = FileUtils.readFileToString(new File(ZeppelinClient.class.getResource("/settings-template.json").getPath()), "UTF-8");
//        JSONObject settings = JSON.parseObject(settingsTemplateJSON);
        JSONObject settings = JSON.parseObject(settingsTemplateJSON);
        settings.put("name", randomKey);
        System.out.println("settings = " + settings);
        return settings.toJSONString();
    }
}
