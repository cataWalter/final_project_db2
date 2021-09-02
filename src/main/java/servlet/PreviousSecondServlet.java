package servlet;


import entity.User;
import service.AnswerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/PreviousSecondServlet")
public class PreviousSecondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AnswerService answerService = new AnswerService();
        ServletContext sc = request.getSession().getServletContext();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("currentUser");
        String productId = (String) session.getAttribute("productId");
        answerService.deleteDefault(Integer.toString(user.getUserId()), productId);
        RequestDispatcher rd = sc.getRequestDispatcher("/defaultQuestions.jsp");
        rd.forward(request, response);
    }
}



