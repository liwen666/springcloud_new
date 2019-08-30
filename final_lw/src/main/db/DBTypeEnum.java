//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package vip.dcpay.cache.domain.config.db;

import java.util.Arrays;
import java.util.Optional;

public enum DBTypeEnum {
    PLATFORM,
    MERCHANT,
    ORDER,
    BASE,
    COMMON,
    SCHEDULE,
    SLAVE_BASE;

    private DBTypeEnum() {
    }

    public static DBTypeEnum getEnum(String code) {
        Optional<DBTypeEnum> first = Arrays.stream(values()).filter((e) -> {
            return e.name().equals(code);
        }).findFirst();
        return first.isPresent() ? (DBTypeEnum)first.get() : null;
    }
}
