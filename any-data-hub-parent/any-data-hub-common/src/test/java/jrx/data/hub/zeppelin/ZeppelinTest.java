package jrx.data.hub.zeppelin;


import com.alibaba.fastjson.JSON;
import jrx.data.hub.domain.exception.InitializationException;
import org.apache.zeppelin.client.ClientConfig;
import org.apache.zeppelin.client.ZeppelinClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZeppelinTest {
    private ZeppelinClient zeppelinClient;

//    @Before
    public void connectZeppelin() {
        try {
            ClientConfig clientConfig = new ClientConfig("http://127.0.0.1:8087/");
            ZeppelinClient zeppelinClient = new ZeppelinClient(clientConfig);
            zeppelinClient.login("admin", "admin");
        } catch (Exception e) {
            throw new InitializationException(e.getMessage(), e);
        }
    }

    @Test
    public void execute() {
//        zeppelinClient.executeParagraph()
        String json = "{\n" +
                "\n" +
                "  \"language\": \"JAVA\", \n" +
                "  \"scripts\": \"def eval(str: String) = str.toUpperCase\"\n" +
                "}";
        JSON json1 = JSON.parseObject(json);
        System.out.println(json1);
    }


    @After
    public void close() {
        zeppelinClient = null;
    }
}
