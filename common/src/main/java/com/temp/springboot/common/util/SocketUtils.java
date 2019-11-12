package com.temp.springboot.common.util;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public class SocketUtils{
    public static void main(String[] args) {
        int availableTcpPort = org.springframework.util.SocketUtils.findAvailableTcpPort(8080);
        System.out.println(availableTcpPort);
    }

}
