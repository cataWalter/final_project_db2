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
<%@ page import="entity.Answer" %>

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
        <h4 class="page-section-heading text-center text-uppercase text-secondary mb-0">Answers:</h4>
        <br/>
        <br/>

        <table class="table">
            <thead>
            <tr>
                <td>QUESTION</td>
                <td>ANSWER</td>
                <td>SUCCESSFULLY SUBMITTED?</td>
            </tr>
            </thead>
            <tbody>
            <%
                List<Answer> answers;
                if (request.getAttribute("answers") != null) {
                    answers = (List<Answer>) request.getAttribute("answers");
                    int i = 0;
                    for (Answer a : answers) {
                        i++;
                        String answer = a.getAnswerText();
                        String question = a.getQuestionByQuestionId().getQuestionText();
                        int successfullySent = a.getSuccessfullySent();
            %>
            <tr>
                <td>
                    <%=question%>
                </td>
                <td>
                    <%=answer%>
                </td>
                <td>
                    <%
                        if (successfullySent != 0) {
                    %>
                    Yes
                    <%
                    } else {
                    %>
                    No
                    <%
                        }
                    %>

                </td>
            </tr>
            <%
                }
            %>
            <%
            } else {
            %>

            No answer found!

            <%
                }
            %>
            </tbody>
        </table>
        </br>

        <div class="col text-center">
            <a class="btn btn-primary" href="admin.jsp">Admin Page</a>
        </div>

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
