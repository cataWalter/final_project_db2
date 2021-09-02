package servlet;

import service.ProductService;
import service.QuestionService;
import entity.Product;
import entity.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CreationServlet")

public class CreationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();
        QuestionService questionService = new QuestionService();
        String dateStr = request.getParameter("date");

        LocalDate localDate = LocalDate.now();
        LocalDate productDate = LocalDate.parse(dateStr);

        if (localDate.isBefore(productDate) || localDate.isEqual(productDate)) {
            String productName = request.getParameter("productName");
            String questionNumber = request.getParameter("number");


            String filename = request.getParameter("imageUpload");
            filename = "C:\\Images\\" + filename;

            File file = new File(filename);
            byte[] fileContent = Files.readAllBytes(file.toPath());


            List<String> questionText = new ArrayList();
            Question question = new Question();
            String temp = "question_";
            int i;

            Product product = new Product();
            product.setProductName(productName);
            product.setProductImage(fileContent);
            product.setDay(dateStr);

            try {
                productService.productInsert(product);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            for (i = 1; i <= Integer.parseInt(questionNumber); i++) {
                temp = temp + i;
                questionText.add(request.getParameter(temp));
                System.out.println(questionText.get(i - 1));
                question.setMarketingQuestion((byte) 1);
                question.setQuestionText(questionText.get(i - 1));
                question.setProductByProductId(productService.selectProduct(productName));


                try {
                    questionService.questionInsert(question);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                temp = "question_";
            }
        }


        ServletContext sc = request.getSession().getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/admin.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}

