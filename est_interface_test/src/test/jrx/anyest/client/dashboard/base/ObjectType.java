package jrx.anyest.client.dashboard.base;

/**
 * 对象类型
 * @author
 * @date 2018/9/25
 */
public enum ObjectType {

    TOPIC("主题对象类型","tp_"),
    STAT("统计模型对象类型","st_"),
    SYSTEM("系统类型","sys_"),
    EVENT("事件类型","ev_"),
    MODEL("业务模型类型","md_"),
    PROJECT("资源项目类型",""),
    TEAM("组织团队类型",""),
    DATA("数据类型","db_"),
    COLLECT_DATA("采集数据类型",""),
    COMPOSITE("组合对象",""),
    FEATURE("衍生变量类型",""),
    NODE_FEATURE("衍生变量节点类型",""),
    NODE_GRAPH("关联节点类型",""),
    OTHER("其他","");

    private String desc;
    private String code;

    ObjectType(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return this.desc;
    }
}
