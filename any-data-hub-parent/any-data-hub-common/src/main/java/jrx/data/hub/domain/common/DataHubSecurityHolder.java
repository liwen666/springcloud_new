package jrx.data.hub.domain.common;

import jrx.data.hub.infrastructure.entity.McUser;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2020/12/2  11:29
 */
public class DataHubSecurityHolder {
    public static McUser user() {
        McUser mcUser = new McUser();
        mcUser.setNickName("系统测试用户");
        return mcUser;
    }
}
