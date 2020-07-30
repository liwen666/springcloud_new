package jrx.anyest.table.service.compiler;

import lombok.Getter;

/**
 * 脚本语言类型
 * @author
 * @date 2018/9/25
 */
@Getter
public enum ScriptLanguage {

    GROOVY("groovy"),
    PYTHON("python"),
    JAVA("java"),
    JAVASCRIPT("javascript"),
    AVIATOR("aviator"),
    QLEXPRESS("qlexpress");

    private String lang;

    ScriptLanguage(String lang){
        this.lang = lang;
    }
}
