package com.example.thymeleafdemo.config;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ConfigurationProperties("suning.rocketmq")
public class RocketMqCfgProperties {
    //    生成组
    private String producerGroup;
    //消费组
    private String conumerGroup;
    //地址
    private String namesrvaddr;

    private String accessKey;
    private String secretKey;
    private String topic;


}
