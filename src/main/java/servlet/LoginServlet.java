package servlet;

import service.LoginService;
import service.UserService;
import entity.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "LoginServlet", value = "/LoginServlet")

public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        LoginService loginService = new LoginService();
        HttpSession session = request.getSession(false);
        ServletContext sc = request.getSession().getServletContext();
        RequestDispatcher rd;
        String msg = null;
        session.setAttribute("errore", msg);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        if (username.equals("admin") && password.equals("1")) {
            try {
                loginService.addLogin(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("admin", true);
            rd = sc.getRequestDispatcher("/admin.jsp");
            rd.forward(request, response);
        } else {
            try {
                User userLogin = userService.selectUser(user);
                if (userLogin != null) {
                    if (userLogin.getBlocked() != 0) {
                        msg = "Blocked account";
                        session.setAttribute("errore", msg);
                        rd = sc.getRequestDispatcher("/login.jsp");
                        rd.forward(request, response);
                    }
                    else {
                        loginService.addLogin(username);
                        session.setAttribute("questionnaireMsg", "");
                        session.setAttribute("currentUser", userLogin);
                        session.setAttribute("id", userLogin.getUserId());
                        ProductOfTheDayServlet ob = new ProductOfTheDayServlet();
                        ob.doGet(request, response);
                    }

                } else {
                    msg = "Wrong credentials";
                    session.setAttribute("errore", msg);
                    rd = sc.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
