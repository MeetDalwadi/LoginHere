package com.mycompany.loginhere.servlet;

import com.mycompany.loginhere.modal.*;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, MessagingException {
        Connection con = (Connection)getServletContext().getAttribute("c");
        String eMail = (String)getServletContext().getAttribute("e");
        String ePassword = (String)getServletContext().getAttribute("p");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");

            if ("signup".equals(action)) {
                String fname = request.getParameter("firstname");
                String lname = request.getParameter("lastname");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String cpassword = request.getParameter("confirmpassword");
                String email = request.getParameter("email");
                if(password.equals(cpassword)){
                TakeData td = new TakeData();
                td.insert(fname, lname, username, password, email,con);
                Mail m = new Mail();
                m.sendVerificationEmail(email, eMail,ePassword,con);
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>Sign Up Successful</title>");
                out.println("<style>");
                out.println("body {");
                out.println("  font-family: Arial, sans-serif;");
                out.println("  background-color: #e0f7fa;");
                out.println("  margin: 0;");
                out.println("  display: flex;");
                out.println("  justify-content: center;");
                out.println("  align-items: center;");
                out.println("  height: 100vh;");
                out.println("}");
                out.println(".container {");
                out.println("  background-color: #ffffff;");
                out.println("  padding: 20px;");
                out.println("  border-radius: 8px;");
                out.println("  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
                out.println("  text-align: center;");
                out.println("}");
                out.println("h1 {");
                out.println("  color: #0288d1;");
                out.println("}");
                out.println("p {");
                out.println("  color: #555;");
                out.println("}");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h1>Sign Up Successful!</h1>");
                out.println("<p>Please check your email for verification.</p>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
                }else{
                     getServletContext().setAttribute("Error","Please Check Password and Confirm Password");
                response.sendRedirect("http://localhost:8082/LoginHere/Second.jsp");
                }

            } else if ("verify".equals(action)) {
                String em = request.getParameter("email");
                String token = request.getParameter("token");
                if (em != null && token != null) {

                    Mail vs = new Mail();
                    boolean isVerified = vs.verifyEmailToken(em, token,con);

                    if (isVerified) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html lang='en'>");
                        out.println("<head>");
                        out.println("<meta charset='UTF-8'>");
                        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                        out.println("<title>Email Verification</title>");
                        out.println("<style>");
                        out.println("body {");
                        out.println("  font-family: Arial, sans-serif;");
                        out.println("  background-color: #e0f7fa;");
                        out.println("  margin: 0;");
                        out.println("  display: flex;");
                        out.println("  justify-content: center;");
                        out.println("  align-items: center;");
                        out.println("  height: 100vh;");
                        out.println("}");
                        out.println(".container {");
                        out.println("  background-color: #ffffff;");
                        out.println("  padding: 20px;");
                        out.println("  border-radius: 8px;");
                        out.println("  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
                        out.println("  text-align: center;");
                        out.println("}");
                        out.println("h1 {");
                        out.println("  color: #0288d1;");
                        out.println("}");
                        out.println("p {");
                        out.println("  color: #555;");
                        out.println("}");
                        out.println("a {");
                        out.println("  color: #0288d1;");
                        out.println("  text-decoration: none;");
                        out.println("}");
                        out.println("a:hover {");
                        out.println("  text-decoration: underline;");
                        out.println("}");
                        out.println("</style>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div class='container'>");
                        out.println("<h1>Verification Successful!</h1>");
                        out.println("<p>Your email has been verified. You can now <a href='First.jsp'>login</a>.</p>");
                        out.println("</div>");
                        out.println("</body>");
                        out.println("</html>");

                    } else {

                        out.println("<h1>Verification failed!</h1>");
                        out.println("<p>Invalid verification link or email.</p>");
                    }
                } else {
                    out.println("<h1>Verification failed!</h1>");
                    out.println("<p>Invalid verification link or email.</p>");
                }
            } else {
                out.println("<h1>Invalid action!</h1>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(SingUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(SingUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
