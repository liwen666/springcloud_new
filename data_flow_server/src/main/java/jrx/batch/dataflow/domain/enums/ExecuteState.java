package jrx.batch.dataflow.domain.enums;

/**
 * 执行状态
 *
 * @author yxy
 * @date 2018/4/11
 */
public enum ExecuteState implements IBaseEnum {

    STARTING(0,"开始启动"),
    RUNNING(1,"运行中"),
    COMPLETE(2,"完成"),
    CLOSING(3,"关闭中"),
    TERMINATE(4,"终止"),
    RERUNNINGERROR(5,"异常错误重跑"),
    FAIL(6,"失败"),
    PAUSE(7,"暂停"),
    CLOSED(8,"关闭"),
    NOCOMPLETE(2,"未完成");

    private int code;
    private String desc;

    private ExecuteState(int code, String desc) {
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

        ExecuteState c = getEnum(code);
        if (null == c) {
            return code + "";
        } else {
            return c.desc;
        }
    }

    public static ExecuteState getEnum(Integer code) {
        if (null == code) {
            return null;
        }

        for (ExecuteState c : ExecuteState.values()) {
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

    public static void main(String[] args) {
        System.out.println(ExecuteState.COMPLETE.name());
    }
}
