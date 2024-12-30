package com.mycompany.loginhere.listener;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class DataBaseListener implements ServletContextListener, HttpSessionListener {

    ServletContext sc = null;
    static int countUser = 0;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sc = sce.getServletContext();
        String dUrl = sc.getInitParameter("url");
        String dName = sc.getInitParameter("userName");
        String dPassword = sc.getInitParameter("password");
        String mEmail = sc.getInitParameter("email");
        String mPassword = sc.getInitParameter("mpassword");
        sc.setAttribute("e", mEmail);
        sc.setAttribute("p", mPassword);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dUrl, dName, dPassword);
            sc.setAttribute("c", con);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        countUser++;
        sc.setAttribute("scount", countUser);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        countUser--;
        sc.setAttribute("scount", countUser);
    }
}
