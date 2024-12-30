<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%
    Cookie[] cookies = request.getCookies();
    String savedUsername = "";
    String savedPassword = "";
    boolean rememberMe = false;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                savedUsername = cookie.getValue();
                rememberMe = true;
            }
            if (cookie.getName().equals("password")) {
                savedPassword = cookie.getValue();
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="login.css">
    </head>
    <body>
        <div class="admin-link">
            <a href="http://localhost:8082/LoginHere/TotalAdmin.jsp" target="_blank">
                <button type="button">Dashboard</button>
            </a>
        </div>
        <div class="container">
            <div class="login-box">
                <h2>SIGN IN</h2>
                <form action="M.D" method="post">
                    <div class="textbox">
                        <label for="username">Username :</label>
                        <input type="text" id="username" name="username" placeholder="Enter Username" value="<%= savedUsername%>">
                    </div>
                    <div class="textbox">
                        <label for="password">Password :</label>
                        <input type="password" id="password" name="password" placeholder="Enter Password" value="<%= savedPassword%>">
                    </div>
                    <div class="textbox remember-me">
                        <label for="remember">Remember Me</label>
                        <input type="checkbox" id="remember" name="remember" <%= rememberMe ? "checked" : ""%>>
                    </div>


                    <div class="buttons">
                        <button type="submit">Sign In</button>
                        <button type="button" onclick="window.location.href = 'http://localhost:8082/LoginHere/Second.jsp';">Sign Up</button>
                    </div>
                    <p class="note">Note: If you don't have an account, please sign up first.</p>
                </form>

            </div>

            <%  String error = (String) getServletContext().getAttribute("Error");
                if (error != null) {%>
            <p style="color: red"><%=error%></p> <br>
            <p style="color: white">Note : If you Don't Have Account then First Sign Up</p>
            <% getServletContext().removeAttribute("Error");
                }%> 
        </div>

    </body>
</html>
