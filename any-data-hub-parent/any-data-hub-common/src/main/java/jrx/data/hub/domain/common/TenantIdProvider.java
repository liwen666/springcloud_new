package jrx.data.hub.domain.common;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Slf4j
public class TenantIdProvider {

    private static TransmittableThreadLocal<String> tenantIdHolder = new TransmittableThreadLocal<>();

    public static final String KEY_TENANT_ID_IN_HTTP_HEADER = "tenant_id";

    public static String getTenantId() {
        return tenantIdHolder.get();
    }

    public static void setTenantId(String tenantId) {
        tenantIdHolder.set(tenantId);
    }

}
