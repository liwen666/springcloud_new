package jrx.batch.dataflow.domain.mq;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.domain.pojo.BatchJobExecutionPojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RocketMQMessageListener(
        topic = "${jrx.node.rocketmq.topic}",
        consumerGroup = "${jrx.node.rocketmq.conumer-group}",
        accessKey = "${jrx.node.rocketmq.access-key}",
        secretKey = "${jrx.node.rocketmq.secret-key}"

)
public class JobResultStatusMsgListener implements RocketMQListener<String> {
    @Autowired
    @Qualifier("scheduleCenterJdbcTemplete")
    private JdbcTemplate scheduleCenterJdbcTemplete;


    @Override
    public void onMessage(String jobResultMsg) {
        log.info("---------接收到job状态更新消息 -->jobResultMsg：{}",jobResultMsg);
        try {
            BatchJobExecutionPojo batchJobExecutionPojo = JSON.parseObject(jobResultMsg, BatchJobExecutionPojo.class);
            if(StringUtils.isEmpty(batchJobExecutionPojo.getTaskParentExecutionId())){
                    log.error("--------接收到的jobResultMsg消息异常，缺少taskParentExecutionId 参数值");
                    return;
            }
//            scheduleCenterJdbcTemplete.up
        } catch (Exception e) {
            log.error("--------分区状态更新异常：-->jobResultMsg:{} ",jobResultMsg);
            e.printStackTrace();
        }

    }
}
