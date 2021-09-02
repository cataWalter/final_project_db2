package servlet;

import service.AnswerService;
import service.ProductService;
import entity.*;
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
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;


@WebServlet("/ProductOfTheDayServlet")
public class ProductOfTheDayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("doGet ProductOfTheDayServlet");
        ProductService productService = new ProductService();
        AnswerService answerService = new AnswerService();
        UserService userService = new UserService();

        ServletContext sc = request.getSession().getServletContext();
        RequestDispatcher rd;

        try {
            Product productOfTheDay;
            productOfTheDay = productService.selectProductOfTheDay();
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("currentUser");
            if (user != null){
                User updatedUser = userService.selectUser(user.getUsername());
                if (updatedUser.getBlocked() == 1){
                    session.setAttribute("currentUser", null);
                }
            }
            if (productOfTheDay != null) {
                request.setAttribute("productOfTheDay", productOfTheDay);
                int productId = productOfTheDay.getProductId();

                byte[] content = productOfTheDay.getProductImage();
                String base64Encoded = new String(Base64.getEncoder().encode(content), "UTF-8");
                request.setAttribute("imageBt", base64Encoded);

                List<Answer> answers = answerService.getSentAnswers(Integer.toString(productId));
                request.setAttribute("answers", answers);

                if (user != null && answerService.alreadyAnswered(productId, user.getUserId())) {
                    session.setAttribute("alreadyAnsweredMsg", "You have already answered the questionnaire");
                } else {
                    session.setAttribute("alreadyAnsweredMsg", null);
                }
            }

            rd = sc.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

