package servlet;

import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogOutServlet", value = "/LogOutServlet")

public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService db = new UserService();
        HttpSession session = request.getSession(false);
        ServletContext sc = request.getSession().getServletContext();
        RequestDispatcher rd;

        session.setAttribute("currentUser", null);
        ProductOfTheDayServlet ob = new ProductOfTheDayServlet();
        ob.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
