<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Already Voted</title>
    <link rel="stylesheet" href="css/jspStyles.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</head>
<body>
    <%
        HttpSession session1 = request.getSession(false);
        if (session1 != null && session1.getAttribute("name") != null) {
            String name = (String) session1.getAttribute("name");
    %>

    <h1 class="text-center mb-4">Hello, <%= name %>!</h1>

    <div class="alert alert-danger text-center">
        <h4>You have already cast your vote!</h4>
        <p>Thank you for participating in the voting process. If you have any questions, please contact the admin.</p>
    </div>

    <div class="text-center mt-4">
        <a href="LogoutServlet">
            <button type="button" class="btn btn-primary" style="background-color: #142d4c;">Logout</button>
        </a>
    </div>

    <% 
        } else { 
            response.sendRedirect("login.jsp");
        } 
    %>
</body>
</html>
