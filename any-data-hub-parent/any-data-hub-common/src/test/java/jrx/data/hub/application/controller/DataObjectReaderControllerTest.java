package jrx.data.hub.application.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author peidong.meng
 * @date 2019/11/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DataObjectReaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void read() throws Exception{

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/console/resource/data-object-info/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("includeVersion","true"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONArray objects = JSONArray.parseArray(result);
        System.out.println(JSONObject.toJSONString(objects.get(0),true));
    }

    @Test
    public void readByContent() throws Exception{

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/console/resource/data-object-info/by-content-codes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("includeVersion","true")
                .param("contentCodes", "293896"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONArray objects = JSONArray.parseArray(result);
        System.out.println(JSONObject.toJSONString(objects.get(0),true));
    }

//    @Test
//    public void readByDataType() throws Exception{
//
//        String result = mockMvc.perform(MockMvcRequestBuilders.get("/console/resource/data-object-info/by-data-type")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("includeVersion","true")
//                .param("dataType", ""))
////                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//
//        JSONArray objects = JSONArray.parseArray(result);
//        System.out.println(JSONObject.toJSONString(objects.get(0),true));
//    }

    @Test
    public void readBySourceCode() throws Exception{

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/console/resource/data-object-info/by-datasource-info-code")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("includeVersion","true")
                .param("dataSourceInfoCode", "grad,input_data"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONArray objects = JSONArray.parseArray(result);
        System.out.println(JSONObject.toJSONString(objects.get(0),true));
    }

    @Test
    public void readByIds() throws Exception{

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/console/resource/data-object-info/by-ids")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("includeVersion","true")
                .param("resourceIds", "333899,320923"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONArray objects = JSONArray.parseArray(result);
        System.out.println(JSONObject.toJSONString(objects.get(0),true));
    }

    @Test
    public void readByCodes() throws Exception{

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/console/resource/data-object-info/by-codes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("includeVersion","true")
                .param("objectInfoCodes", "db_AC_INFO_NO_TEST,db_grad_AC_INFO_NO_TEST"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONArray objects = JSONArray.parseArray(result);
        System.out.println(JSONObject.toJSONString(objects.get(0),true));
    }
}
