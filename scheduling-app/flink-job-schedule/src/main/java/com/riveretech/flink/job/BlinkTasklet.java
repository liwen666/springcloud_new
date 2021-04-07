package com.riveretech.flink.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2021/4/7 11:45
 */

public class BlinkTasklet implements Tasklet {
    
    private static final Logger logger = LoggerFactory.getLogger(BlinkTasklet.class);

    private String name;

    private int size;

    private AtomicInteger counter = new AtomicInteger();

    public BlinkTasklet(String name, int size){
        this.name = name;
        this.size = size;
    }

    /**
     * 当RepeatStatus等于CONTINUABLE时，任务不会终止，在stepContext中会记录一次提交次数
     * 当tasklet的处理时长太长，不能够做标准数据处理拆分时，将RepeatStatus在不同处理量上做定量的返回CONTINUABLE
     *
     * @param contribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        int time = size;
        while(time > 0){
            Thread.sleep(20);
            counter.incrementAndGet();
            contribution.incrementReadCount();
            time--;
        }

//        if (true) {
//            int i = 1 / 0;
//        }
        //当处理数量超过100的时候任务完成，否侧循环调用execute方法
        logger.info("process counter: {},===  jobName:{}",counter,name);
        if(counter.get() > 100){
            return RepeatStatus.FINISHED;
        }

        return RepeatStatus.CONTINUABLE;
    }
}
