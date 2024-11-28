package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import com.company.dao.User;
import com.company.dao.UserDao;
import com.company.dao.UserDaoImp;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static UserDao uDao = new UserDaoImp();

    public RegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET VOTER ID
        int voter_id = 0;
        try {
            voter_id = Integer.parseInt(request.getParameter("voter_id"));
        } catch (NumberFormatException e) {
            response.sendRedirect("register.jsp?error=2"); // Redirect with error=2 for invalid voter_id format
            System.out.println("Error: Invalid voter ID format");
            return;
        }

        String name = request.getParameter("name");
        String dob = request.getParameter("dob"); // Assuming the format is yyyy-MM-dd
        String password = request.getParameter("password");

        // Check if the voter ID is unique
        if (!uDao.isVoterIdUnique(voter_id)) {
            response.sendRedirect("register.jsp?error=4"); // Redirect with error=4 for duplicate voter ID
            System.out.println("Error: Voter ID must be unique");
            return;
        }

        // DOB Validation - Check if the user is 18 or older
        String dobYearString = dob.substring(0, 4); // Extract the year part from the DOB (first 4 characters)
        int dobYear = Integer.parseInt(dobYearString);
        LocalDate currentDate = LocalDate.now(); // Get the current year using LocalDate
        int currentYear = currentDate.getYear();
        if ((currentYear - dobYear) < 18) {
            response.sendRedirect("register.jsp?error=3"); // Redirect with error=3 if the user is under 18
            return; // Stop further processing
        }

        // Create a new user
        User user = new User();
        user.setVoterID(voter_id);
        user.setName(name);
        user.setDob(dob);
        user.setPassword(password);

        // Attempt to add the user to the database
        if (uDao.addUser(user)) {
            response.sendRedirect("login.jsp?registration=success"); // Redirect to login page if successful
        } else {
            response.sendRedirect("register.jsp?error=1"); // Redirect with error=1 if registration fails
        }
    }
}
