package jrx.batch.dataflow.domain.enums;

public enum CodeEnums {
    EXCEPTION(999, "exception", "异常"),
    PAGE_NOT_FOUND(404, "page_not_found", "页面不存在"),
    ILLEGALARGUMENT(400, "illegalargument", "参数校验异常"),
    PARAM_EMPTY(1001, "param_empty", "参数为空"),
    PARAM_VALUE_ERROR(1016, "param_value_error", "参数设置错误"),
    USER_NOT_EXIST(1018, "USER_NOT_EXIST", "用户不存在"),
    SUCCESS(1000, "SUCCESS", "成功");

    private int code;
    private String desc;
    private String cnDesc;

    private CodeEnums(int code, String desc, String cnDesc) {
        this.code = code;
        this.desc = desc;
        this.cnDesc = cnDesc;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCnDesc() {
        return this.cnDesc;
    }

    public void setCnDesc(String cnDesc) {
        this.cnDesc = cnDesc;
    }
}
