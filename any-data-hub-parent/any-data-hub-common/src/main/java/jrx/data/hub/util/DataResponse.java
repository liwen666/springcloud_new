package jrx.data.hub.util;

import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.enums.CodeEnums;
import jrx.data.hub.domain.enums.RespStatus;

import java.time.Instant;

/**
 * @author yxy
 */
public class DataResponse<T> {

    /**
     * 响应码
     */
    private RespStatus status;
    /**
     * 数据
     */
    private T data;
    /**
     * 耗时查询耗时，毫秒
     */
    @ApiModelProperty(value = "查询耗时", example = "0")
    private long consume;
    /**
     * 响应时间戳
     */
    @ApiModelProperty(value = "响应时间", example = "0")
    private long responseTimestamp = Instant.now().toEpochMilli();
    /**
     * 响应信息
     */
    private String reason;
    // TODO: 2020/6/4 临时添加用作测试 等三方服务同步状态码后 修改
    @ApiModelProperty(value = "测试", example = "0")
    private Integer code;

    public DataResponse() {
    }

    public static <T> DataResponse<T> of() {
        DataResponse<T> dataResponse = getSuccessDataResponse();
        return dataResponse;
    }

    public static <T> DataResponse<T> error() {
        DataResponse<T> dataResponse = gettDataResponse();
        return dataResponse;
    }

    private static <T> DataResponse<T> gettDataResponse() {
        DataResponse<T> dataResponse = new DataResponse<T>();
        dataResponse.setStatus(RespStatus.ERROR);
        dataResponse.setCode(RespStatus.ERROR.getCode());
        dataResponse.setReason(RespStatus.ERROR.getMsg());
        dataResponse.setResponseTimestamp(Instant.now().toEpochMilli());
        return dataResponse;
    }

    public static <T> DataResponse<T> error(T t) {
        DataResponse<T> dataResponse = gettDataResponse();
        dataResponse.setData(t);
        return dataResponse;
    }

    /**
     * 返回时创建
     */
    public DataResponse(T data, long startTime) {
        this.data = data;
        this.responseTimestamp = System.currentTimeMillis();
        this.consume = this.responseTimestamp - startTime;
    }

    public static <T> DataResponse success(T note) {
        DataResponse<T> dataResponse = getSuccessDataResponse();
        dataResponse.setData(note);
        return dataResponse;
    }

    private static <T> DataResponse<T> getSuccessDataResponse() {
        DataResponse<T> dataResponse = new DataResponse<T>();
        dataResponse.setStatus(RespStatus.SUCCESS);
        dataResponse.setCode(RespStatus.SUCCESS.getCode());
        dataResponse.setResponseTimestamp(Instant.now().toEpochMilli());
        return dataResponse;
    }

    public DataResponse<T> end(long startTime) {
        this.responseTimestamp = System.currentTimeMillis();
        this.consume = this.responseTimestamp - startTime;
        return this;
    }

    public RespStatus getStatus() {
        return status;
    }

    public DataResponse<T> setStatus(RespStatus status) {
        this.status = status;
        return this;
    }

    public T getData() {
        return data;
    }

    public DataResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public DataResponse<T> setData(T data, long startTime) {
        this.data = data;
        return end(startTime);
    }

    public long getConsume() {
        return consume;
    }

    public DataResponse<T> setConsume(long consume) {
        this.consume = consume;
        return this;
    }

    public long getResponseTimestamp() {
        return responseTimestamp;
    }

    public DataResponse<T> setResponseTimestamp(long responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
