package com.tuzhi.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @program: superManager
 * @description: character
 * @author: 兔子
 * @create: 2021-11-04 22:25
 **/

public class Character implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        System.out.println("进入过滤器");
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
