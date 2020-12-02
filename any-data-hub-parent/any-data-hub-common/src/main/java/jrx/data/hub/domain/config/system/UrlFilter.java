package jrx.data.hub.domain.config.system;

import jrx.data.hub.domain.common.TenantIdProvider;
import jrx.data.hub.domain.constant.DataHubConstant;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:24
 */

@WebFilter(filterName = "data_hub_filter", urlPatterns = "/*")
public class UrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------------------->过滤器被创建");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String tenantId = ((HttpServletRequest) servletRequest).getHeader(TenantIdProvider.KEY_TENANT_ID_IN_HTTP_HEADER);
        if (StringUtils.isNotBlank(tenantId)) {
            TenantIdProvider.setTenantId(tenantId);
        } else {
            tenantId = DataHubConstant.TEST_TENANT;
            if (StringUtils.isNotBlank(tenantId)) {
                TenantIdProvider.setTenantId(tenantId);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}