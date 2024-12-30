<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
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
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            h1, h3 {
                color: #0288d1;
            }
            p {
                color: #555;
                font-size: 16px;
            }
            .active-users {
                font-size: 24px;
                font-weight: bold;
                color: #0288d1;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Dashboard</h1>
            <h3>Total number of active users:</h3>
            <% HttpSession s = request.getSession(false);
                if (s == null) {%>
            <p class="active-users">0</p>
            <% } else {%>
            <p class="active-users"><%=(Integer) getServletContext().getAttribute("scount")%></p>
            <% }%> 



        </div>
    </body>
</html>
