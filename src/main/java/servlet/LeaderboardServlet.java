package servlet;

import service.UserService;
import entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/LeaderboardServlet")
public class LeaderboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService conn = new UserService();
        ServletContext sc = request.getSession().getServletContext();
        RequestDispatcher rd;

        try {
            List<User> users;
            users = conn.selectLeaderboard();
            request.setAttribute("users", users);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        rd = sc.getRequestDispatcher("/leaderboard.jsp");
        rd.forward(request, response);

    }
}

