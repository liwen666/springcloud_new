package it.pcan.test.feign.client;

public enum CodeEnums {
    /**
     * 1-1000
     * 通用
     * 页面相关
     * 1001-2000
     * 文件相关
     * 2001-3000
     *  用户权限相关
     *  3001-5000
     */
    SUCCESS (1,"success","成功"),
    EROOR (0,"error","错误"),
    EXCEPTION(999, "exception", "异常"),
    PAGE_NOT_FOUND(404, "page_not_found", "页面不存在"),
    ILLEGALARGUMENT(400, "illegalargument", "参数校验异常"),
    PARAM_EMPTY(1001, "param_empty", "参数为空"),
    PARAM_VALUE_ERROR(1016, "param_value_error", "参数设置错误"),
    FILE_NULL(2001, "file_null", "文件为空"),
    FILE_NOT_ACCEPT(2001, "file_not_accept", "文件不合法");


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
