package com.temp.jpa.service;

import com.alibaba.fastjson.JSON;
import com.temp.jpa.jpa.enums.HandlerParam;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Service("defaultTableDataHandler")
public class DefaultTableDataHandler implements TableDataHandler {
    @Override
    public void conversionData(Map data,HandlerParam param) {

        System.out.println("处理数据-----------"+ JSON.toJSONString(data));
    }
}
