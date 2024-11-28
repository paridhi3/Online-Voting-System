<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="css/jspStyles.css" />
</head>

<body>
    <div class="container">
        <h1>Register</h1>
        <form action="RegisterServlet" method="post">
            <input type="text" id="voter_id" placeholder="Enter Voter ID" name="voter_id" required pattern="\d+" title="Only numeric values are allowed">
            <input type="text" id="name" placeholder="Enter Fullname" name="name" required pattern="[A-Za-z\s]+" title="Only letters and spaces are allowed">
            <input type="date" id="dob" placeholder="Enter date of birth" name="dob" required>
            <input type="password" id="password" placeholder="New Password" name="password" required >
            <button type="submit">Register</button>
        </form>

        <small><p>Already have an account? <a href="login.jsp">Login</a>.</p></small>
        
        <hr>
        
        <small><p> To Login as <span class="small">ADMIN</span>, click <a href="adminLogin.jsp">here</a>.</p></small>
        
        <hr>

        <p class="back-to-home"><a href="index.jsp">Back to Home</a></p>

        <%-- Display error message if registration fails. --%>
        <% String error1 = request.getParameter("error");
           if (error1 != null && error1.equals("1")) { %>
            <p style="font-weight:bold; color: red; margin-top:15px;">Registration failed. Please try again.</p>
        <% } %>
        <% String error2 = request.getParameter("error");
           if (error2 != null && error2.equals("2")) { %>
            <p style="font-weight:bold; color: red; margin-top:15px;">In Voter ID, only numeric values are allowed. Please try again.</p>
        <% } %>
        <% String error3 = request.getParameter("error");
           if (error3 != null && error3.equals("3")) { %>
            <p style="font-weight:bold; color: red; margin-top:15px;">Voter must be 18 years or older. Please try again.</p>
        <% } %>
        <% String error4 = request.getParameter("error");
           if (error4 != null && error4.equals("4")) { %>
            <p style="font-weight:bold; color: red; margin-top:15px;">Voter ID must be unique. Please try again.</p>
        <% } %>
    </div>
</body>

</html>
