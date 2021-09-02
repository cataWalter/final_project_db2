package servlet;

import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/DeletionServlet")
public class DeletionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();
        String dateStr = request.getParameter("date");

        LocalDate localDate = LocalDate.now();

        LocalDate dateLocalDate = LocalDate.parse(dateStr);
        if (dateLocalDate.isBefore(localDate)) {
            try {
                productService.deleteDate(dateStr);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        ServletContext sc = request.getSession().getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/admin.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

