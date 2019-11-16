package jrx.batch.dataflow.domain.config.batch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Date: 2018/12/17 09:29
 * @Description:
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        int threadCount = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(threadCount * 2);
        // 设置最大线程数
        executor.setMaxPoolSize(threadCount * 2);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 设置队列容量
        executor.setQueueCapacity(200);
        // 设置默认线程名称
        executor.setThreadNamePrefix("taskExecutor");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程等待时间
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
