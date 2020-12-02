package jrx.data.hub.infrastructure.dto;

import jrx.data.hub.domain.enums.JobExecuteResultType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 任务执行结果
 *
 * @author Songyc5
 * @date: 2020/11/16
 */
@Getter
@Setter
public class JobExecuteResult<E> {

    private String jobInfoId;

    private JobExecuteResultType jobExecuteResultType;

    private List<E> data;



}
