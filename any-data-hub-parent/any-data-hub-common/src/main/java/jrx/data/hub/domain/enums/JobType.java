package jrx.data.hub.domain.enums;

/**
 * @author Songyc5
 * @date: 2020/11/8
 */
public enum JobType implements IBaseEnum {

    BATCH("批次"),
    REALTIME("实时");

    private String des;

    JobType(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    @Override
    public int code() {
        return 0;
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public String getDesc(Integer code) {
        return null;
    }
}
