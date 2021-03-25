package tem.inter.analysis.rest;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import groovy.lang.Tuple;
import groovy.lang.Tuple2;
import jrx.data.hub.core.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
public class DemoTestRestTest {

    @Test
    public void name() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o = Class.forName("tem.inter.analysis.rest.DemoTestRest").newInstance();
        Method[] declaredMethods = DemoTestRest.class.getDeclaredMethods();
        for(Method m:declaredMethods){
            System.out.println(m.getName());
            Parameter[] parameters = m.getParameters();
           Object[] args=getParamObjects(parameters);
            Object invoke = m.invoke(o,args);
            System.out.println(invoke);
        }

    }

    private Object[] getParamObjects(Parameter[] parameters) {
        Object[] args= new Object[parameters.length];

        for(int i=0;i<args.length;i++){
            log.info(parameters[i].getName()+"---"+parameters[i].getType().getName());
            args[i]= JsonUtils.string2Obj("2021-01-28 14:48:21",parameters[i].getType());
        }
        return args;
    }

    @Test
    public void date() {
        System.out.println(JsonUtils.obj2String(LocalDateTime.now()));



        System.out.println(JsonUtils.string2Obj(System.currentTimeMillis()+"",Date.class));
    }




    @Test
    public void date2() {
        TestModel testModel = new TestModel();
        testModel.setDate(new Date());

        System.out.println(JsonUtils.obj2String(testModel));
        TestModel testModel1 = JsonUtils.string2Obj("{\"date\":\"2021-01-28 14:58:23\"}\n", TestModel.class);

        System.out.println(testModel1);
    }

    @Test
    public void nameType() throws ParseException {
        String aaa = JsonUtils.string2Obj("AAA", String.class);
        System.out.println(aaa);
        int aaa1 = JsonUtils.string2Obj("1", int.class);
        System.out.println(aaa1);
        long l2= JsonUtils.string2Obj("133333333333333", long.class);
        System.out.println(l2);
        BigDecimal l3= JsonUtils.string2Obj("133333333333333", BigDecimal.class);
        System.out.println(l3);

        Date d = new Date();
        Date date = JsonUtils.string2Obj("2021-01-28 15:07:05", Date.class);
        System.out.println(date);



    }

    @Test
    public void classDate() {
        Class a = Date.class;
        Class b = LocalDateTime.class;

    }

    @Test
    public void tuple() {
        Tuple2<Integer,Object> param = new Tuple2<>(1,"ssss");
        System.out.println(param.getFirst());
        System.out.println(param.getSecond());
        System.out.println(param.get(0));
        System.out.println(param.get(1));
        System.out.println(JsonUtils.obj2String(param));

    }
}
