package com.lw.common.log.service;

import com.lw.common.log.domain.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
* Description:    java类作用描述<br>
* author:     lw
* date:     2019/5/26 23:34
* Version:        1.0
*/
public interface LoggingService {

    /**
     * 新增日志
     * @param joinPoint
     * @param logging
     */
    @Async
    void save(ProceedingJoinPoint joinPoint, Log logging);
}
