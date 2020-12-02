package jrx.data.hub.util;

import lombok.experimental.UtilityClass;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/10/22 17:05
 */
@UtilityClass
public class SystemUtils {
    public static boolean isWin() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return true;
        }
        return false;
    }
}
