package java8test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public class TestStream
{


    @Test
    public void testConver() {

        Province province = new Province();
        province.setName("bendi");
        province.setAddr("http");
        List<Province> list = new ArrayList<>();
        list.add(province);
        System.out.println(JSON.toJSONString(list));
        List<Province> collect = list.stream().map(e -> {
            e.setName(e.getName() + "_" + e.getAddr());
            return e;
        }).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
    }
}
