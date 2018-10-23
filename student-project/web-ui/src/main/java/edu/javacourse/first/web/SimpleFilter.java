package edu.javacourse.first.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SimpleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter START");
        HttpServletRequest htr = (HttpServletRequest) req;
        resp.getWriter().append("Filter 1 before.");
        if (filterChain != null) {
            filterChain.doFilter(req, resp);
        }
        resp.getWriter().append("Filter 1 after.");
        System.out.println("Filter FINISH");
    }

    @Override
    public void destroy() {

    }

}
