package com.lance.study.aop.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "commonFilter")
public class CommonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("commonFilter  init");
    }

    @Override
    public void destroy() {
        System.out.println("commonfilter destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long l = System.currentTimeMillis();
    System.out.println("========"+l);
        chain.doFilter(request,response);
        long l1 = System.currentTimeMillis();
        System.out.println("=========="+l1);
    System.out.println(l1-l);
    }
}
