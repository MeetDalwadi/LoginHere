<%@ page contentType="text/html" pageEncoding="UTF-8" import="jakarta.servlet.http.*"%>
<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("name") == null) {
        response.sendRedirect("http://localhost:8082/LoginHere/First.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Successful</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #e0f7fa;
                margin: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            h1 {
                color: #0288d1;
            }
            p {
                color: #555;
            }
            .logout-button {
                background-color: #0288d1;
                color: #ffffff;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                margin-top: 20px;
                transition: background-color 0.3s;
            }
            .logout-button:hover {
                background-color: #0277bd;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Login Successful!</h1>
            <p>Welcome, <%= session.getAttribute("name")%></p>

            <!-- Logout Form -->
            <form action="Log.do" method="get">
                <button type="submit" class="logout-button">Log Out</button>
            </form>
        </div>
    </body>
</html>
