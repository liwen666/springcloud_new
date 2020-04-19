package com.nacos;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class YamlConfig {
    @Test
    public void name() throws IOException {
        File file = new File("D:\\idea2018workspace\\springcloud_new\\data_flow_server_node\\src\\main\\resources\\bootstrap.yaml");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[]cache = new byte[fileInputStream.available()];
        fileInputStream.read(cache);
        String context = new String(cache,"utf8");
        Map map = YamlUtil.collatingCfg(context);
        Map<String, Object> stringObjectMap = YamlUtil.converMapToProperties(map);
        System.out.println(JSON.toJSONString(stringObjectMap));
    }
}
