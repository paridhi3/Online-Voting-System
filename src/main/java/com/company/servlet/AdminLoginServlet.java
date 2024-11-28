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

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static UserDao uDao = new UserDaoImp();

    public AdminLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String admin_username = request.getParameter("admin_username");
        String security_key = request.getParameter("security_key");


        // Check if the admin is valid
        if (uDao.isValidAdmin(admin_username, security_key)) {
            HttpSession session = request.getSession();
            session.setAttribute("admin_username", admin_username);
            response.sendRedirect("AddPartyServlet"); // Redirect to Admin welcome page
        } else {
            // Redirect to login page with error=1 for invalid credentials
            response.sendRedirect("adminLogin.jsp?error=1");
            System.out.println("Error: Invalid User!");
        }
    }
}
