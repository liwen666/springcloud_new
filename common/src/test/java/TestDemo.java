import com.alibaba.fastjson.JSON;
import com.temp.springboot.common.ThymeleafDemoApplication;
import com.temp.springboot.common.util.DateUtils;
import com.temp.springboot.common.util.YamlUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.*;

//@RestController()
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = ThymeleafDemoApplication.class)
public class TestDemo {

    @Test
    public void test() throws FileNotFoundException {
        Map<?, ?> map = YamlUtil.loadYaml("application.yml");
        System.out.println(JSON.toJSONString(map));
        Map<String, String> druidMap = (Map<String, String>) map.get("druid");
        Properties properties = System.getProperties();
        for(Map.Entry me : druidMap.entrySet()){
            Object o = properties.setProperty("druid."+me.getKey(),me.getValue()+"");

        }
        System.out.println(JSON.toJSONString(properties));

    }

    /**
     *
     */
    @Test
    public void isNull() {
        Map map = new HashMap();
        map.put("aaa","bbb");
        System.out.println(map.get("aaa"));
        System.out.println(getKey(map,"bbb"));
    }


    public static Object getKey(Map map, Object value){
        List<Object> keyList = new ArrayList<>();
        for(Object key: map.keySet()){
            if(map.get(key).equals(value)){
                keyList.add(key);
            }
        }
        return keyList;
    }

    @Test
    public void name() {

//        Date date = new Date(1593773707000l);
        Date date = new Date(1593773710000L);
        System.out.println(DateUtils.format(date,DateUtils.YYYY_MM_DD_HH_MM_SS));

    }
}
