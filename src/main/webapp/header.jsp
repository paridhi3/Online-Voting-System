<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Online Voting System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">

    <style>
    	    	body {
    		margin:0;
    		padding:0;
    	}
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
            width: 100%;
            height: 100vh;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover:not(.active) {
            background-color: #111;
        }

        .active {
            background-color: #04AA6D;
        }

        /* Clearfix for the floating elements */
        ul::after {
            content: "";
            display: table;
            clear: both;
          	margin:0;
          	padding:0;
        }
    </style>
</head>

<body>
    <!-- Navigation Bar -->
    <ul>
        <li><a href="#home">Home</a></li>
        <li style="float:right"><a class="active" href="admin.jsp">Admin</a></li>
        <li style="float:right"><a class="active" href="register.jsp">Register</a></li>
        
    </ul>
</body>
</html>
