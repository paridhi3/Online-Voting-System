<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
     <link rel="stylesheet" href="css/jspStyles.css" />
     
     <script type="text/javascript">
        window.onload = function() {
            var urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('registration') && urlParams.get('registration') === 'success') {
                alert('Registration successful!');
                urlParams.delete('registration');
                window.history.replaceState({}, document.title, window.location.pathname + '?' + urlParams.toString());
            }
        };
	</script>
     
</head>

<body>

    <div class="container">
        <h1>LOGIN</h1>
        
        <form action="LoginServlet" method="post"> <!-- Change method to "post" -->
            <label for="voter_id">Voter ID:</label>
            <input type="text" placeholder="Enter Your Voter ID" name="voter_id" required>
            <label for="password">Password:</label>
            <input type="password" placeholder="Enter Password" name="password" required><br>
            <button type="submit">Login</button>
        </form>
        
        <small><p>Create an account? <a href="register.jsp">Register</a>.</p></small>
        
        <hr>
        
        <small><p> To Login as <span class="small">ADMIN</span>, click <a href="adminLogin.jsp">here</a>.</p></small>
        
        <hr>
        
        <p class="back-to-home"><a href="index.jsp">Back to Home</a></p>


        <%-- Display error message if login fails. This will be visible in login.jsp --%>
        <% String error = request.getParameter("error");
            if (error != null && error.equals("1")) { %>
                <p style="color: red; font-weight: bold; margin-top:15px;">Invalid Username or Password. Please try again.</p>
        <% } %>
        
        <%-- Display message if Register Successful. This will be visible in register.jsp --%>
        <% String rs = request.getParameter("registration");
            if (rs != null && rs.equals("success")) { %>
                <p style="color: green; font-weight: bold; margin-top:15px;">Your Registration is Successful. Please Login.</p>
        <% } %>
    </div>
</body>

</html>