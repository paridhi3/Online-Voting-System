package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/AdminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession(false); // Retrieve the session object (false means we won't create a new session if none exists)

        // If the session exists, invalidate it
        if (session != null) {
            session.invalidate();  // Invalidate the session, effectively logging out the user
        }

        response.sendRedirect("index.jsp?adminLogoutSuccess=true"); // Redirect to the home page after logging out
    }

}
