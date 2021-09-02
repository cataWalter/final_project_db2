package servlet;


import service.AnswerService;
import service.QuestionService;
import entity.Question;
import entity.User;

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

@WebServlet("/DefaultAnswerServlet")
public class DefaultAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("currentUser");
        String productId = (String) session.getAttribute("productId");

        System.out.println(productId);
        System.out.println(user.getUsername());
        System.out.println(user.getUserId());

        List<Question> questions = new ArrayList();
        try {
            questions = questionService.getDefaultQuestions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("default answers: ");
        for(Question q:questions) {

            String answer = request.getParameter(String.valueOf(q.getQuestionId()));
            System.out.println(answer);
            System.out.println(productId);

            try {
                answerService.addAnswer(user.getUserId(), Integer.parseInt(productId), q.getQuestionId(), answer);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        ServletContext sc = request.getSession().getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/submitAnswers.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}



