package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.company.dao.UserDao;
import com.company.dao.UserDaoImp;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static UserDao uDao = new UserDaoImp();

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse voter_id from the request parameter
        int voter_id = 0; // Default value for voter_id
        try {
            voter_id = Integer.parseInt(request.getParameter("voter_id"));
        } catch (NumberFormatException e) {
            response.sendRedirect("login.jsp?error=2"); // Redirect with error=2 for invalid voter_id format
            System.out.println("Error: Invalid voter_id format");
            return;
        }
        String password = request.getParameter("password");

        System.out.println("voter_id: " + voter_id);
        System.out.println("password: " + password);
        System.out.println("Result of isValidUser(): " + uDao.isValidUser(voter_id, password));

        // Check if the user is valid
        if (uDao.isValidUser(voter_id, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("voter_id", voter_id);
            session.setAttribute("name", uDao.getNameByVoterId(voter_id)); // Store voter_id in the session
//            response.sendRedirect("/WEB-INF/vote.jsp"); // Redirect to the welcome page
            response.sendRedirect("CastVoteServlet");
        } else {
            // Redirect to login page with error=1 for invalid credentials
            response.sendRedirect("login.jsp?error=1");
            System.out.println("Error: Invalid User!");
        }
    }
}
