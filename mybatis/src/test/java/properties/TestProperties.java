package properties;

import com.alibaba.fastjson.JSON;
import com.temp.springboot.common.core.YamlUtil;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Properties;

public class TestProperties {
    public static void main(String[] args) throws FileNotFoundException {
        Map<?, ?> map = YamlUtil.loadYaml("application.yml");
        Map<String, String> druidMap = (Map<String, String>) map.get("druid");
        Properties properties = System.getProperties();
        for(Map.Entry me : druidMap.entrySet()){
            Object o = properties.setProperty("druid."+me.getKey(),me.getValue()+"");


        }
        System.out.println(  properties.getProperty("druid.filters"));

    }
}
