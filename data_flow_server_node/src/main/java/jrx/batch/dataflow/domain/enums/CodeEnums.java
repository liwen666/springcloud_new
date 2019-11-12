package jrx.batch.dataflow.domain.enums;

public enum CodeEnums implements IBaseEnum{
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
    EXIST_DATA (2,"exist_data","数据不能重复"),
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
    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }


    @Override
    public String getDesc(Integer code) {
        if (null == code) {
            return null;
        }

        CodeEnums c = getEnum(code);
        if (null == c) {
            return code + "";
        } else {
            return c.desc;
        }
    }

    public String getCnDesc() {
        return this.cnDesc;
    }


    public static CodeEnums getEnum(Integer code) {
        if (null == code) {
            return null;
        }

        for (CodeEnums c : CodeEnums.values()) {
            if (c.code() == code.intValue()) {
                return c;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int code = CodeEnums.FILE_NOT_ACCEPT.code();
        System.out.println(code);
    }

}
