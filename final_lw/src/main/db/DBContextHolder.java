//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package vip.dcpay.cache.domain.config.db;

import java.util.concurrent.atomic.AtomicInteger;

public class DBContextHolder {
    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal();
    private static final AtomicInteger counter = new AtomicInteger(-1);

    public DBContextHolder() {
    }

    public static void setDB(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum getDB() {
        return (DBTypeEnum)contextHolder.get();
    }

    public static void clearDB() {
        contextHolder.remove();
    }

    public static void platform() {
        setDB(DBTypeEnum.PLATFORM);
    }

    public static void merchant() {
        setDB(DBTypeEnum.MERCHANT);
    }

    public static void order() {
        setDB(DBTypeEnum.ORDER);
    }

    public static void base() {
        setDB(DBTypeEnum.BASE);
    }

    public static void common() {
        setDB(DBTypeEnum.COMMON);
    }

    public static void schedule() {
        setDB(DBTypeEnum.SCHEDULE);
    }

    public static void slaveBase() {
        setDB(DBTypeEnum.SLAVE_BASE);
    }
}
