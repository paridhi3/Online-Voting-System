package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.company.dao.Party;
import com.company.dao.PartyDao;
import com.company.dao.PartyDaoImp;
import com.company.dao.UserDao;
import com.company.dao.UserDaoImp;

@WebServlet("/CastVoteServlet")
@MultipartConfig
public class CastVoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static UserDao uDao = new UserDaoImp();
    private static PartyDao pDao = new PartyDaoImp();

    public CastVoteServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("voter_id") != null) {
            int voter_id = (Integer) session.getAttribute("voter_id");
            int party_id = Integer.parseInt(request.getParameter("party_id"));

            try {
                uDao.castVote(voter_id, party_id); // Attempt to cast the vote
                List<Party> partyList = pDao.getAllParties();
                request.setAttribute("partyList", partyList);
                request.setAttribute("error", "0");               
                request.getRequestDispatcher("/WEB-INF/vote.jsp").forward(request, response);
            } catch (IllegalStateException e) {
                // Redirect to alreadyVoted.jsp if voter has already voted
            	request.getRequestDispatcher("/WEB-INF/alreadyVoted.jsp").forward(request, response);
            }
        } else {
            // Redirect to login if session is invalid
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("voter_id") != null) {
            int voter_id = (Integer) session.getAttribute("voter_id");

            try {
                // Check if the voter has already voted
                if (uDao.hasVoted(voter_id)) {
                	request.getRequestDispatcher("/WEB-INF/alreadyVoted.jsp").forward(request, response);
                } else {
                    // If not voted, proceed to vote.jsp
                    List<Party> partyList = pDao.getAllParties();
                    request.setAttribute("partyList", partyList);
                    request.getRequestDispatcher("/WEB-INF/vote.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
