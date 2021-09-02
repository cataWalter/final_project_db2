package servlet;

import service.AnswerService;
import entity.Answer;

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

@WebServlet("/AnswerInspection")
public class AnswerInspection extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AnswerService answerService = new AnswerService();
        HttpSession session = request.getSession(false);

        String userId = request.getParameter("userId");
        String productId = request.getParameter("productId");


        try {
            List<Answer> answers = answerService.getAnswers(userId, productId);
            request.setAttribute("answers", answers);
            request.getRequestDispatcher("/answerInspection.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

        request.getRequestDispatcher("/answerInspection.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

