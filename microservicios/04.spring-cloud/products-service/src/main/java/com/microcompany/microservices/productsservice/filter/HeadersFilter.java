package com.microcompany.microservices.productsservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collection;
import java.util.Enumeration;

import org.springframework.stereotype.Component;

@Component
public class HeadersFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Enumeration headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = httpRequest.getHeader(key);
            if(key.indexOf("added")>=0) System.out.println("****HeadersFilter request headers->" + key + ":" + value);
        }

        Collection<String> headerresNames = httpResponse.getHeaderNames();
        for (String headerName:headerresNames) {
            String value = httpResponse.getHeader(headerName);
            if(headerName.indexOf("added")>=0) System.out.println("****HeadersFilter response headers->" + headerName + ":" + value);
        }

        filterchain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }
}
