<%--
  Created by IntelliJ IDEA.
  User: walter
  Date: 24/04/21
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Product" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Inspection</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico"/>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet"
          type="text/css"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet"/>
</head>
<body id="page-top">
<!-- Contact Section-->
<section class="page-section" id="contact">
    <div class="container">
        <!-- Contact Section Heading-->
        <h4 class="page-section-heading text-center text-uppercase text-secondary mb-0">Inspection</h4>
        <br/>
        <br/>

        <table class="table">
            <thead>
            <tr>
                <td>PRODUCT NAME</td>
                <td>DAY</td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <%
                List<Product> submittedQuestionnaires;
                if (request.getAttribute("questionnairesList") != null) {
                    submittedQuestionnaires = (List<Product>) request.getAttribute("questionnairesList");
                    int i = 0;
                    for (Product p : submittedQuestionnaires) {
                        i++;
                        String name = p.getProductName();
                        String day = p.getDay().toString(); //p.getDay();
            %>
            <tr>
                <td><%=name%>
                </td>
                <td><%=day%>
                </td>
                <td>


                    <form action="InspectionServlet?productId=<%=p.getProductId()%>" method="post">
                        <input type="submit" class="btn btn-primary" value="Inspect">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
            <%
            } else {
            %>

            No past questionnaires submitted found!

            <%
                }
            %>
            </tbody>
        </table>


    </div>

    <div class="col text-center">
        <a class="btn btn-primary" href="admin.jsp">Admin Page</a>
    </div>
</section>
<!-- Footer-->
<footer class="footer text-center">
    <div class="container">
        <div class="row">

        </div>
    </div>
</footer>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="assets/mail/jqBootstrapValidation.js"></script>
<script src="assets/mail/contact_me.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>
