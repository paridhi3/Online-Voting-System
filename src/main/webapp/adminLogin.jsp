<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
     <link rel="stylesheet" href="css/jspStyles.css" />    
</head>

<body>

    <div class="container">
        <h1>ADMIN LOGIN</h1>
                
        <form action="AdminLoginServlet" method="post"> <!-- Change method to "post" -->
            <label for="admin_username">Admin Username:</label>
            <input type="text" placeholder="Enter Admin Username" name="admin_username" required>
            <label for="password">Security Key:</label>
            <input type="password" placeholder="Enter Security Key" name="security_key" required><br>
            <button type="submit">Submit</button>
        </form>
        
        <small><p> This page is for Admin only. If you are a <span class="small">VOTER</span>, Register <a href="register.jsp">here</a> or <a href="login.jsp">Login as Voter</a>.</p></small>
        
        <hr>
        
        <p class="back-to-home"><a href="index.jsp">Back to Home</a></p>


        <%-- Display error message if login fails. This will be visible in login.jsp --%>
        <% String error = request.getParameter("error");
            if (error != null && error.equals("1")) { %>
                <p style="color: red; font-weight: bold; margin-top:15px;">Invalid Admin credentials. Please try again.</p>
        <% } %>

    </div>
</body>

</html>