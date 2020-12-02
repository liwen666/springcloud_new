package jrx.data.hub.domain.complier;

import lombok.Getter;

/**
 * 脚本语言类型
 * @author yxy
 */
@Getter
public enum ScriptLanguage {

    GROOVY("groovy"),
    PYTHON("python"),
    JAVA("java"),
    JAVASCRIPT("javascript"),
    AVIATOR("aviator"),
    SCALA("scala"),
    QLEXPRESS("qlexpress");

    private String lang;

    ScriptLanguage(String lang){
        this.lang = lang;
    }
}
