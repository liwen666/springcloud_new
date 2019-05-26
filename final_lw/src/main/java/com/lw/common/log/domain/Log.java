package com.lw.common.log.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* Description:    java类作用描述<br>
* author:     lw
* date:     2019/5/26 23:34
* Version:        1.0
*/
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("createTime")
    private LocalDateTime createTime;

    private String description;

    @TableField("exceptionDetail")
    private String exceptionDetail;

    @TableField("logType")
    private String logType;

    private String method;

    private String params;

    @TableField("requestIp")
    private String requestIp;

    private String time;

    private String username;

    public Log() {
    }
    public Log(String logType, String time) {
        this.logType = logType;
        this.time = time;
    }
}
