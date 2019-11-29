package jrx.batch.dataflow.quartz;

import com.alibaba.fastjson.JSON;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
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
@Service
@Slf4j
public class PlanServiceImpll {

    public void execute(){

    }

    public <V, K> void execute(Integer planId, Map<K,V> emptyMap) {
        log.info("开始执行计划{},emptmap{}",planId, JSON.toJSONString(emptyMap));
    }
}
