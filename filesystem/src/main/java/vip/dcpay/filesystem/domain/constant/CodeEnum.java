package vip.dcpay.filesystem.domain.constant;

public enum CodeEnum {
    SUCCESS(200,"成功"),
    CLIENT_ERROR(400,"客户端错误"),
    AUTH_ERROR(401,"权限错误"),
    PARAM_ERROR(402,"参数错误"),
    BUSINESS_ERROR(500,"业务错误"),
    INNER_ERROR(501,"内部服务错误");

    int code;
    String desc;

    private CodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
