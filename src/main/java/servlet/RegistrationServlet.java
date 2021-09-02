package servlet;

import service.UserService;
import entity.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        HttpSession session = request.getSession(false);

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(pass);
        user.setPoints(0);
        user.setBlocked(0);

        if (userService.selectUser(username) == null) {
            try {
                userService.userInsert(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            User addedUser = userService.selectUser(username);
            session.setAttribute("currentUser", addedUser);
        }

        ///////////

        ServletContext sc = request.getSession().getServletContext();

        ProductOfTheDayServlet ob = new ProductOfTheDayServlet();
        ob.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

