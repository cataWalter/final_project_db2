package servlet;

import service.QuestionService;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/DefaultQuestionServlet")
public class DefaultQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuestionService questionService = new QuestionService();
        HttpSession session = request.getSession(false);

        List<Question> questions = new ArrayList();

        try {
            questions = questionService.getDefaultQuestions();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        session.setAttribute("questions", questions);
        ServletContext sc = request.getSession().getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/defaultQuestions.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
