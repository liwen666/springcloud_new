package jrx.data.hub.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 表类型
 */
@AllArgsConstructor
@Getter
public enum FunctionType {
    UDAF("UDAF", "UDAF函数"),
    BUILDIN("build-in", "内置函数"),
    UDTF("UDTF", "UDTF函数"),
    UDF("UDF", "UDF函数");
    private String code;
    private String name;
}
