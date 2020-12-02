package jrx.data.hub.domain.enums;

/**
 * 数据返回状态码
 * @author yxy
 */
public enum RespStatus {
    ERROR(3000, "失败"),
    SUCCESS(1000, "查询成功");


    private int code;
    private String msg;

    RespStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

}
