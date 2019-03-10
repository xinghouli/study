package com.lance.study.aop.filters.entity;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class WrapperResponse extends HttpServletResponseWrapper {

    public WrapperResponse(HttpServletResponse response) throws IOException {
        super(response);

    }
}
