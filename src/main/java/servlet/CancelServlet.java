package servlet;


import entity.Question;
import entity.User;
import service.AnswerService;
import service.QuestionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CancelServlet")
public class CancelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getSession().getServletContext();
        /*AnswerService answerService = new AnswerService();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("currentUser");
        String productId = (String) session.getAttribute("productId");
        answerService.delete(Integer.toString(user.getUserId()), productId);*/

        RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
}



