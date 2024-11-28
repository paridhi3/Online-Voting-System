<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.company.dao.Party" %>
<%@ page import="com.company.dao.PartyDao" %>
<%@ page import="com.company.dao.PartyDaoImp" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Political Parties</title>
    <link rel="stylesheet" href="css/jspStyles.css">
</head>
<body>
    <%
        // Retrieve the existing session (do not create a new session)
        HttpSession session1 = request.getSession(false); 

        if (session1 != null && session1.getAttribute("admin_username") != null) {
            String admin_username = (String) session1.getAttribute("admin_username");
    %>
    
    <h1 class="admin-heading">Hi <%= admin_username %>!</h1>
    <div class="addParty-container">
        <h2>Manage Political Parties</h2>

        <!-- Party Table -->
        <table class="party-table">
            <thead>
                <tr>
                    <th>Party ID</th>
                    <th>Party Name</th>
                    <th>Party Symbol</th>
                    <th>Vote Count</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Get the list of parties
                    List<Party> partyList = (List<Party>) request.getAttribute("partyList");
                    
                    // Sort the parties based on vote count in descending order
                    if (partyList != null && !partyList.isEmpty()) {
                        Collections.sort(partyList, new Comparator<Party>() {
                            @Override
                            public int compare(Party p1, Party p2) {
                                return Integer.compare(p2.getVote_count(), p1.getVote_count());
                            }
                        });
                        
                        // Highlight the winner (first party in the sorted list)
                        Party winner = partyList.get(0);
                        
                        for (Party party : partyList) {
                            boolean isWinner = party.equals(winner);  // Check if this party is the winner
                %>
                <tr <% if (isWinner) { %> style="background-color: #9EDF9C;" <% } %>>
                    <td><%= party.getParty_id() %></td>
                    <td><%= party.getParty_name() %></td>
                    <td>
                        <img src="data:image/png;base64,<%= java.util.Base64.getEncoder().encodeToString(party.getParty_symbol()) %>" 
                             alt="Party Symbol" width="50" height="50">
                    </td>
                    <td><%= party.getVote_count() %></td>
                    <td>
                        <form action="DeletePartyServlet" method="post">
                            <input type="hidden" name="party_id" value="<%= party.getParty_id() %>">
                            <button type="submit" class="delete-button">Delete Party</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" class="no-parties">You have not added any party yet.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Form to Add a New Party -->
        <form action="AddPartyServlet" method="post" enctype="multipart/form-data" class="add-party-form">
            <table class="form-table">
                <tr>
                    <td>
                        <input type="number" name="party_id" placeholder="Party ID" required 
                               pattern="\d+" title="Only numeric values are allowed">
                    </td>
                    <td>
                        <input type="text" name="party_name" placeholder="Party Name" required 
                               pattern="[A-Za-z\s]+" title="Only letters and spaces are allowed">
                    </td>
                    <td>
                        <input type="file" name="party_symbol" accept="image/*" required 
                               title="Please upload a file in PNG, JPEG, or JPG format.">
                    </td>
                    <td><button type="submit">Add Party</button></td>
                </tr>
            </table>
        </form>
    </div>
    
    <%
        // Display error or success messages
        String error = (String) request.getAttribute("error");
        if (error != null) {
            if ("1".equals(error)) {
    %>
            <p style="color: red; font-weight: bold; margin-bottom: 15px;">Failed to add party. Try again.</p>
    <%
            } else if ("2".equals(error)) {
    %>
            <p style="color: red; font-weight: bold; margin-bottom: 15px;">Failed to delete the party. Please try again.</p>
    <%
            } else if ("3".equals(error)) {
    %>
            <p style="color: green; font-weight: bold; margin-bottom: 15px;">Party deleted successfully!</p>
    <%
            } else if ("4".equals(error)) {
    %>
            <p style="color: green; font-weight: bold; margin-bottom: 15px;">Party added successfully!</p>
    <%
            }
        }
    %>
    
    <p class="addPartyLogout">
        If you are finished, don't forget to <b>LOGOUT</b> using the button below to ensure the security of your session.
    </p>
    <a href="AdminLogoutServlet">
        <button type="button" class="admin-logout-btn">Logout</button>
    </a>
    
    <%
        } else {
            response.sendRedirect("adminLogin.jsp"); // Redirect to login if session is invalid
        }
    %>
</body>
</html>
