package tem.inter.analysis.rest;

import com.alibaba.fastjson.JSONObject;
import jrx.data.hub.core.constant.DataHubConstant;
import jrx.data.hub.core.utils.TenantIdProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import tem.inter.analysis.utils.TablePropertiesThreadLocalHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:24
 */

@Slf4j
@WebFilter(filterName = "data_hub_filter", urlPatterns = "/*")
public class UrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------------------->过滤器被创建");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        TablePropertiesThreadLocalHolder.addProperties("sequence","jkfdjajfd");
                filterChain.doFilter(servletRequest, servletResponse);
         //删除序列号
        TablePropertiesThreadLocalHolder.remove("sequence");
        //删除链路标记
        TablePropertiesThreadLocalHolder.removeSeq("jkfdjajfd");
    }

    @Override
    public void destroy() {
    }
}
