package com.logicbig.example.servlet;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qiu
 */
@WebServlet(name = "PageServlet", urlPatterns = {"*.page"})
public class PageServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(PageServlet.class);
    private ServletConfig config = null;

    private String page = "/template/template_defautl.jsp";
    /**
     * 处理portal框架初始化设置
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {

            pageserver( request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void pageserver(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -10);

        // 设置返回内容文档格式
        response.setContentType("text/html;application/xhtml+xml;charset=UTF-8");

        String contextPath = request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();
        String page_head = "设置返回内容文档格式";
        String page_body = "设置返回内容文档格式";
        request.setAttribute("page_head", page_head);
        request.setAttribute("page_body", page_body);
        request.getRequestDispatcher(page).include(request, response);
        response.getWriter().append("<script>alert('"+contextPath+"')</script>");
        response.getWriter().flush();
    }



    @Override
    public ServletConfig getServletConfig() {
        return config;
    }
}
