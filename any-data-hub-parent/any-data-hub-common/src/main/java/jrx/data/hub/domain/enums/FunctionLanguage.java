package jrx.data.hub.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 函数枚举
 */
@Getter
@AllArgsConstructor
public enum FunctionLanguage {
    JAVA("JAVA", "java", "java函数"),
    PYTHON("PYTHON", "python", "python函数"),
    SCALA("SCALA", "scala", "scala函数"),
    SHELL("SHELL", "shell", "scala函数"),
    GROOVY("GROOVY", "groovy", "groovy函数");

    private String name;
    private String code;
    private String description;

}
