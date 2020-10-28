package jrx.data.hub.domain.config.system;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/10/22 16:24
 */

@WebFilter(filterName = "data_hub_filter", urlPatterns = "/*")
public class UrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------------------->过滤器被创建");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}