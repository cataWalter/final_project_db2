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

@WebServlet("/AnswersServlet")
public class AnswersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();
        ServletContext sc = request.getSession().getServletContext();
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("currentUser");
        String productId = (String) session.getAttribute("productId");

        List<Question> questions = new ArrayList();

        String type = (String) session.getAttribute("answers");

        if(type == "normal") {

            try {
                questions = questionService.getQuestionByProductId(productId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            for(Question q:questions) {
                String answer = request.getParameter(String.valueOf(q.getQuestionId()));
                try {
                    answerService.addAnswer(user.getUserId(), Integer.parseInt(productId), q.getQuestionId(), answer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            RequestDispatcher rd = sc.getRequestDispatcher("/DefaultQuestionServlet");
            rd.forward(request, response);
        }
        if(type == "submitAll") {
            answerService.setSuccessfullySent(user.getUserId(), productId);
            RequestDispatcher rd = sc.getRequestDispatcher("/greetingsPage.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        HttpSession session = request.getSession(false);


        if(session.getAttribute("currentUser") == null){
            session.setAttribute("questionnaireMsg", "You have to log in in order to answer the questionnaire!");

            ServletContext sc = request.getSession().getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else {

            List<Question> questions = new ArrayList();
            String productId = request.getParameter("productId");
            session.setAttribute("productId", productId);

            try {
                questions = questionService.getQuestionByProductId(productId);
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

            session.setAttribute("questions", questions);
            session.setAttribute("numberOfQuestions", questions.size());
            ServletContext sc = request.getSession().getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/answers.jsp");
            rd.forward(request, response);
        }
    }
}



