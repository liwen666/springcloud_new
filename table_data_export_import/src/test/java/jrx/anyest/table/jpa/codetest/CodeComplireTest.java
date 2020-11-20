package jrx.anyest.table.jpa.codetest;

import jrx.anyest.table.service.compiler.ScriptLanguage;
import jrx.anyest.table.service.compiler.code.CodeExecutorBuilder;
import jrx.anyest.table.service.compiler.code.ICodeExecutor;
import org.junit.Test;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class CodeComplireTest {
    @Test
    public void groovy() {
        String []paramsCode=new String[]{"num1","num2","num3","num4"};
        String content="def list = [num1,num2,num3,num4]\n" +
                "return list.max();";
        Class[] paramsType = new Class[]{Double.class,Double.class,Double.class,Double.class};
        ICodeExecutor build = CodeExecutorBuilder.build(
                ScriptLanguage.GROOVY,
                content,
                paramsCode,
                paramsType);

        Object execute = build.execute(1, 2, 3, 4);
        System.out.println(execute);
    }
    @Test
    public void java() {
        String []paramsCode=new String[]{"dddd","ssss"};
        String content=" return dddd+ssss;";
        Class[] paramsType = new Class[]{Integer.class,Integer.class};
        ICodeExecutor build = CodeExecutorBuilder.build(
                ScriptLanguage.JAVA,
                content,
                paramsCode,
                paramsType);

        Object execute = build.execute(1,2);
        System.out.println(execute);
    }

//    @Test
//    public void testOnce(){
//        long start = System.nanoTime();
//        Object[] args = new Object[]{"jackson",new Integer(18),new Integer(95)};
//        Object res = javaCodeExecutor.execute(args);
//        System.out.println(res);
//        long end = System.nanoTime();
//        System.out.println("java ,consume: " + (end -start)/1000000d + " ms.");
//
//        start = System.nanoTime();
//        res = groovyCodeExecutor.execute(args);
//        System.out.println(res);
//        end = System.nanoTime();
//        System.out.println("groovy ,consume: " + (end -start)/1000000d + " ms.");
//
//        start = System.nanoTime();
//        res = aviatorCodeExector.execute(args);
//        System.out.println(res);
//        end = System.nanoTime();
//        System.out.println("aviator ,consume: " + (end -start)/1000000d + " ms.");
//
//        start = System.nanoTime();
//        res = jPythonExector.execute(args);
//        System.out.println(res);
//        end = System.nanoTime();
//        System.out.println("python ,consume: " + (end -start)/1000000d + " ms.");
//    }

}
