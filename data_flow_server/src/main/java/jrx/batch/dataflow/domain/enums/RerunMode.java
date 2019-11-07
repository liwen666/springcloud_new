package jrx.batch.dataflow.domain.enums;

/**
 * 执行状态
 *
 * @author yxy
 * @date 2018/4/11
 */
public enum RerunMode implements IBaseEnum {

    AGAIN(0,"重新执行"),
    CONTINET(1,"继续执行");

    private int code;
    private String desc;

    private RerunMode(int code, String desc) {
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

        RerunMode c = getEnum(code);
        if (null == c) {
            return code + "";
        } else {
            return c.desc;
        }
    }

    public static RerunMode getEnum(Integer code) {
        if (null == code) {
            return null;
        }

        for (RerunMode c : RerunMode.values()) {
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
