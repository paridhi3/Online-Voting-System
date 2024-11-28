<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.dao.Party" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vote</title>
    <link rel="stylesheet" href="css/jspStyles.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</head>
<body>
    <%
        HttpSession session1 = request.getSession(false);
        if (session1 != null && session1.getAttribute("name") != null) {
            String name = (String) session1.getAttribute("name");
    %>

    <h1 class="vote-heading">Welcome, <%= name %>!</h1>

	<%
			String error = (String) request.getAttribute("error");
			if("0".equals(error)) {
	%>
				<!-- Success Message -->
			    <div class="alert alert-success text-center">
			        Your vote has been successfully cast! You will be automatically logged out in <span id="countdown"><b>10</b></span> seconds.
			    </div>
			    <!-- Auto Logout with Countdown -->
				<script>
    				let countdownElement = document.getElementById("countdown").getElementsByTagName("b")[0];
    				let countdownTime = 10;  // Set initial countdown time in seconds

    				// Function to update the countdown every second
    				let countdownInterval = setInterval(function() {
        				countdownTime--;
        				countdownElement.textContent = countdownTime;  // Update the countdown display

        				if (countdownTime <= 0) {
            				clearInterval(countdownInterval);  // Stop the countdown
            				window.location.href = "LogoutServlet";  // Redirect to logout
        				}
    				}, 1000);  // Update every second
				</script>
	<%
			}
	%>
	
    <div class="container mt-4 vote-container">
    <h2>Choose your Party</h2>
        <div class="d-flex justify-content-center flex-wrap">
            <%
                @SuppressWarnings("unchecked")
                List<Party> partyList = (List<Party>) request.getAttribute("partyList");
                if (partyList != null && !partyList.isEmpty()) {
                    for (Party party : partyList) {
            %>
                <!-- Party Card -->
                <div class="card m-3" style="width: 18rem;">
                    <img class="card-img-top" src="data:image/png;base64,<%= java.util.Base64.getEncoder().encodeToString(party.getParty_symbol()) %>" alt="Party Symbol">
                    <div class="card-body text-center">
                        <h5 class="card-title"><%= party.getParty_name() %></h5>
                        <form action="CastVoteServlet" method="post">
                            <input type="hidden" name="party_id" value="<%= party.getParty_id() %>">
                            <button type="submit" class="btn btn-primary mt-3" style="background-color: green;">Vote</button>
                        </form>
                    </div>
                </div>
            <% 
                    }
                } else { 
            %>
                <p class="text-center mt-4">No parties available to display.</p>
            <% 
                } 
            %>
        </div>
    </div>

        <a href="LogoutServlet">
            <button type="button" class="vote-logout-btn" style="background-color: #142d4c;">Logout</button>
        </a>

    <% 
        } else { 
            response.sendRedirect("login.jsp");
        } 
    %>
</body>
</html>
