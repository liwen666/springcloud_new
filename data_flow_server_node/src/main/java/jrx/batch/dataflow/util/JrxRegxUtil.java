package jrx.batch.dataflow.util;

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
public class JrxRegxUtil {
    /**
     * 匹配文件名
     *
     */
    public static  boolean isAccept(String data){
        String regx ="[A-Za-z0-9]+.jar";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

}
