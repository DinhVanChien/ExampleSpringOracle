package com.chiendv.examplespringoracle.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class SimpleFilter implements Filter {
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {
        System.out.println("Remote Host: " +request.getRemoteHost());
        System.out.println("Remote Address: " +request.getRemoteAddr());
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURL().toString();
        System.out.println("Url: " + url);
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        if (headerNames != null) {
            String authen = httpRequest.getHeader("Authen");
            System.out.println("Header Authen: " + authen);
            while (headerNames.hasMoreElements()) {
                System.out.println("Header: " + httpRequest.getHeader(headerNames.nextElement()));
            }
        }
        filterchain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
        System.out.println("INIT");
    }
}