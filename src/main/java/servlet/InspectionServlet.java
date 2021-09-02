package servlet;

import service.ProductService;
import service.UserService;
import entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/InspectionServlet")
public class InspectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService db = new UserService();
        HttpSession session = request.getSession(false);

        String productId = request.getParameter("productId");


        try {
            //get list of users who submitted
            List<User> submitted = db.selectUserByAnswer(productId, 1);
            request.setAttribute("submitted", submitted);
            request.setAttribute("productId", productId);

            //get list of users who cancelled

            List<User> cancelled = db.selectUserByAnswer(productId, 0);
            request.setAttribute("cancelled", cancelled);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/inspectionUsers.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();
        try {
            List<Product> questionnairesList = productService.getProducts();
            request.setAttribute("questionnairesList", questionnairesList);
            request.getRequestDispatcher("/inspection.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}

