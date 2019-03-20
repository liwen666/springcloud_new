package com.temp.springcloud.monitor.domin;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 郑杰
 * @date 2018-12-24
 */
@Data
@AllArgsConstructor
public class LogMessage {

    private String body;
    private String timestamp;
    private String threadName;
    private String className;
    private String level;
    private String exception;
}
