package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.company.dao.PartyDao;
import com.company.dao.PartyDaoImp;

@WebServlet("/DeletePartyServlet")
public class DeletePartyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static PartyDao pDao = new PartyDaoImp();

    public DeletePartyServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int party_id = Integer.parseInt(request.getParameter("party_id"));
            boolean isDeleted = pDao.deleteParty(party_id);

            if (isDeleted) {
                // Set a success message in the session or as a query parameter
                response.sendRedirect("AddPartyServlet?error=3");
            } else {
                // Set a failure message in the session or as a query parameter
                response.sendRedirect("AddPartyServlet?error=2");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AddPartyServlet?error=2"); // Redirect on exception
        }
    }
}