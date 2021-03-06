package jrx.data.hub.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:53
 */
public class JrxRegxUtil {
    /**
     * 匹配文件名
     */
    public static boolean isAccept(String data) {
        String regx = "[A-Za-z0-9_-]+.jar";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    /**
     * 匹配文件名
     */
    public static boolean isAcceptTaskdefineName(String name) {
        String regx = "^[a-zA-Z][a-zA-Z0-9_-]*$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
