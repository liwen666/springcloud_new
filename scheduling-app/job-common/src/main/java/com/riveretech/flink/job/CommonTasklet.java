package com.riveretech.flink.job;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2021/4/7 11:45
 */
@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class CommonTasklet implements Tasklet {
    private String[] args;

    private String name;

    private int size;

    private AtomicInteger counter;


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

        if (counter.get() > 100) {
            return RepeatStatus.FINISHED;
        }

        return RepeatStatus.CONTINUABLE;
    }
}
