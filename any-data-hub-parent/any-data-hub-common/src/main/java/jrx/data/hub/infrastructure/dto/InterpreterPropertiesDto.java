package jrx.data.hub.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述 创建interceptor的参数传输对象
 * </p>
 *
 * @author LW
 * @since 2020/11/28  15:44
 */
@Getter
@Setter
public class InterpreterPropertiesDto {
    private String name;
    private String group;
    private Map<String, Map> properties;
    private String status;
    private List interpreterGroup;
    private List dependencies;
    private Map option;
}
