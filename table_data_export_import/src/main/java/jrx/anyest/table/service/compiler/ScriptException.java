package jrx.anyest.table.service.compiler;

/**
 * 脚本异常
 * @author
 * @date 2018/9/27
 */
public class ScriptException extends RuntimeException {

    public ScriptException(){

    }

    public ScriptException(String msg){
        super(msg);
    }
}
