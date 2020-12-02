//package jrx.data.hub.domain.complier;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.script.Invocable;
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import java.util.Arrays;
//
///**
// * scala执行器
// * 脚本执行器中的脚本为执行过程，不包含函数封装
// TODO: scala待实现
// *
// * @author yxy
// * @date 2019/1/7
// */
//@Slf4j
//public class ScalaExecutor extends AbstractCodeExecutor {
//
//    private ScriptEngine scriptEngine;
//
//    public ScalaExecutor(String script, String functionName, String... paramCode) {
//        super(script, functionName, paramCode);
//        initialize();
//    }
//
//    @Override
//    public Object execute(Object... args) {
//        try {
//            //脚本执行,groovy and javascript
//            return ((Invocable) scriptEngine).invokeFunction(functionName, args);
//        } catch (Exception e) {
//            log.error(e.getMessage() + "functionName:{} , args: {} ,", functionName, Arrays.toString(args));
//            throw new ScriptException("脚本执行异常，请检查,functionName: " + functionName);
//        }
//        //return null;
//    }
//
//    @Override
//    public String getFunctionName() {
//        return functionName;
//    }
//
//    @Override
//    public void initialize() {
//        buildSource();
//        ScriptEngineManager engineManager = new ScalaEngineManager();
//        scriptEngine = engineManager.getEngineByName(ScriptLanguage.JAVASCRIPT.getLang());
//    }
//
//
//    private void buildSource() {
//        this.source = "def ";
//        this.source += functionName;
//        this.source += " (";
//        for (int i = 0; i < paramCode.length; i++) {
//            this.source += paramCode[i] + ":" + paramType[i];
//        }
//        source += "){";
//        source += script;
//        source += "}";
//    }
//
//
//}
