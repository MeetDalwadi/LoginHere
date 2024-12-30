<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="signup.css">
</head>
<body>
    <div class="container">
        <div class="signup-box">
            <h2>Sign Up</h2>
            <form action="D.M" method="post">
                <input type="hidden" name="action" value="signup">
                
                <label for="firstname">First Name :</label>
                <input type="text" id="first-name" placeholder="Enter First Name" name="firstname" required>
                
                <label for="lastname">Last Name :</label>
                <input type="text" id="last-name" placeholder="Enter Last Name" name="lastname" required>

                <label for="username">Username :</label>
                <input type="text" id="username" placeholder="Enter Username" name="username" required>

                <label for="password">Password :</label>
                <input type="password" id="password" placeholder="Enter Password" name="password" required>
                
                <label for="confirmpassword">Confirm Password :</label>
                <input type="password" id="confirm-password" placeholder="Confirm Password" name="confirmpassword" required>

                <label for="email">Email :</label>
                <input type="email" id="email" placeholder="Enter Email" name="email" required>

                <button type="submit">Sign Up</button>
            </form>
            </div>
        <%  String error = (String) getServletContext().getAttribute("Error"); 
            if(error!=null){ %>
            <p style="color: red"><%=error%></p>
            <% getServletContext().removeAttribute("Error");}%>    
    </div>
        </div>
    </div>
</body>
</html>
