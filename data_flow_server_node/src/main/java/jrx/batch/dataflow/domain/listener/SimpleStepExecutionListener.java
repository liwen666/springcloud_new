package jrx.batch.dataflow.domain.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @author yxy
 * @date 2018/8/1
 */
public class SimpleStepExecutionListener implements StepExecutionListener {


    private static final Logger logger = LoggerFactory.getLogger(SimpleStepExecutionListener.class);


    @Override
    public void beforeStep(StepExecution stepExecution) {

        logger.info("=====接口step监听器，在开始step之前,{}",stepExecution);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("=====接口step监听器，在step结束之后,{}",stepExecution);
        //可以基于StepExecution中的内容，设定退出状态值
        return null;
    }

}
