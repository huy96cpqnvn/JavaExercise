package com.demo.servlet;

import com.demo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/register"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        if (user.checkUser()) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            Cookie cookie = new Cookie("user", username);
            response.addCookie(cookie);

            response.sendRedirect("home");
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet doGet");

        request.getRequestDispatcher("login.jsp").forward(request, response);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies ) {
            System.out.println(cookie.getName() + ": " + cookie.getValue());
        }

    }
}
