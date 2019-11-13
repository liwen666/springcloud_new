package jrx.batch.dataflow.othe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public class Test {
    @org.junit.Test
    public void name() {
        System.out.println("app.jar".substring(0,"app.jar".lastIndexOf(".")));
    }

    @org.junit.Test
    public void regx() {
        String regx ="[a-z]+.jar";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher("app.1.jar");
        System.out.println(matcher.matches());

    }  @org.junit.Test
    public void os() {
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            System.out.println(os + " can't gunzip");
        }

    }


    @org.junit.Test
    public void testException() {

        try {
            exec();
        } catch (Exception e) {
            System.out.println("捕获到运行时异常！"+e.getMessage());
        }
    }

    public void exec(){
        /**
         * 运行时异常可以被全局拦截不用捕获
         */
        if(1==1){
            throw new PlanExecutionException("jkjkjdka");
        }

    }
}
