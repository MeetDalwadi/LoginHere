package com.mycompany.loginhere.servlet;

import com.mycompany.loginhere.modal.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.util.logging.*;

public class SignInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con = (Connection) getServletContext().getAttribute("c");

        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");
            GiveData gd = new GiveData();
            boolean flag = gd.check(username, password, con);

            if (flag) {
                HttpSession session = request.getSession();
                session.setAttribute("name", username);

                // If "Remember Me" is checked, create cookies
                if ("on".equals(remember)) {
                    Cookie userCookie = new Cookie("username", username);
                    Cookie passCookie = new Cookie("password", password);

                    // Set cookies to last for 7 days (in seconds)
                    userCookie.setMaxAge(7 * 24 * 60 * 60);
                    passCookie.setMaxAge(7 * 24 * 60 * 60);

                    response.addCookie(userCookie);
                    response.addCookie(passCookie);
                } else {
                    // If "Remember Me" is unchecked, remove cookies
                    Cookie userCookie = new Cookie("username", "");
                    Cookie passCookie = new Cookie("password", "");

                    userCookie.setMaxAge(0); // Immediate removal
                    passCookie.setMaxAge(0);

                    response.addCookie(userCookie);
                    response.addCookie(passCookie);
                }

                RequestDispatcher rd = request.getRequestDispatcher("Success.jsp");
                rd.forward(request, response);
            } else {
                getServletContext().setAttribute("Error", "Invalid Username Or Password");
                response.sendRedirect("http://localhost:8082/LoginHere/First.jsp");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check for cookies and populate fields if present
        Cookie[] cookies = request.getCookies();
        String savedUsername = null;
        String savedPassword = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    savedUsername = cookie.getValue();
                }
                if ("password".equals(cookie.getName())) {
                    savedPassword = cookie.getValue();
                }
            }
        }

        if (savedUsername != null && savedPassword != null) {
            request.setAttribute("savedUsername", savedUsername);
            request.setAttribute("savedPassword", savedPassword);
        }

        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SignInServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SignInServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
