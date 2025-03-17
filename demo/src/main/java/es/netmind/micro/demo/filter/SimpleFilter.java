package es.netmind.micro.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@Component
@WebFilter(urlPatterns = {"/product"})
public class SimpleFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {

        log.info("Remote Host:" + request.getRemoteHost());
        log.info("Remote Address:" + request.getRemoteAddr());
        filterchain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }
}