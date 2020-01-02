package com.lance.study.aop.config;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return new ModelAndView();
    }
}
