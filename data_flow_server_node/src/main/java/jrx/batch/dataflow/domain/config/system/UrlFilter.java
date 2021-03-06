package jrx.batch.dataflow.domain.config.system;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 *  描述
 *
 * </p>
 *
 * @author lw
 * @since  2019/11/12 16:12
 */
@WebFilter(filterName = "dataflow_filter", urlPatterns = "/*")
public class UrlFilter implements Filter {
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------------------->过滤器被创建");
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        if(requestURI.equals("/dashboard/index.html")){
            servletRequest.getRequestDispatcher("/public"+requestURI).forward(servletRequest, servletResponse);
        }else if(requestURI.equals("/apps")){
            servletRequest.getRequestDispatcher("/spring/batch/apps").forward(servletRequest, servletResponse);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
 
    @Override
    public void destroy() {
    }
}