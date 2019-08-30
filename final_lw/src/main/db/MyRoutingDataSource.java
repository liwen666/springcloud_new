//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package vip.dcpay.cache.domain.config.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

public class MyRoutingDataSource extends AbstractRoutingDataSource {
    public MyRoutingDataSource() {
    }

    @Nullable
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.getDB();
    }
}
