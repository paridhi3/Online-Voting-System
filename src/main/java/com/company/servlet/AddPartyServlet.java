package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

import com.company.dao.Party;
import com.company.dao.PartyDao;
import com.company.dao.PartyDaoImp;

@WebServlet("/AddPartyServlet")
@MultipartConfig
public class AddPartyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static PartyDao pDao = new PartyDaoImp();

    public AddPartyServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        // ADD PARTY
        int party_id = Integer.parseInt(request.getParameter("party_id"));
        String party_name = request.getParameter("party_name");
        Part partySymbolPart = request.getPart("party_symbol");

        int rowsInserted = pDao.addPartyToDatabase(party_id, party_name, partySymbolPart);
        if (rowsInserted > 0) {
            List<Party> partyList = pDao.getAllParties(); // On success, fetch the updated party list and forward
            request.setAttribute("partyList", partyList);
            request.setAttribute("error", "4");
            request.getRequestDispatcher("/WEB-INF/addParty.jsp").forward(request, response); 
        } else {
            response.sendRedirect("AddPartyServlet?error=1"); // On failure, redirect with error message
        }
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Party> partyList = pDao.getAllParties();
        request.setAttribute("partyList", partyList);

        // Retrieve the error code from the URL
        String error = request.getParameter("error");
        if (error != null) {
            request.setAttribute("error", error);
        }

        request.getRequestDispatcher("/WEB-INF/addParty.jsp").forward(request, response);
    }

}
