package com.demo.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("AuthFilter doFilter");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        getData();
        String user = (String) session.getAttribute("username");
        String servletPath = request.getServletPath(); ///home -> /login
        if (user == null && !servletPath.contains("login")) {
            response.sendRedirect("login");
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void getData() {
        System.out.println("Hello");
        System.out.println("Hello");
        System.out.println("Hello");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
