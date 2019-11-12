package jrx.batch.dataflow.domain.enums;


public enum JrxBatchEnums implements IBaseEnum {

    JAR_HOME_DEFAULT(1, "默认文件上传到节点路径"),
    WIN_JAR_HOME(2, "window系统jar文件上传路径"),
    LINUX_JAR_HOME(3, "linux系统jar文件上传路径"),
    NODE_NAME(4, "节点名称");

    private int code;
    private String desc;

    private JrxBatchEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getDesc(Integer code) {
        if (null == code) {
            return null;
        }

        JrxBatchEnums c = getEnum(code);
        if (null == c) {
            return code + "";
        } else {
            return c.desc;
        }
    }

    public static JrxBatchEnums getEnum(Integer code) {
        if (null == code) {
            return null;
        }

        for (JrxBatchEnums c : JrxBatchEnums.values()) {
            if (c.code() == code.intValue()) {
                return c;
            }
        }
        return null;
    }

    public static boolean isValid(Integer code) {
        if (null == code) {
            return false;
        }

        return null != getEnum(code);
    }



}