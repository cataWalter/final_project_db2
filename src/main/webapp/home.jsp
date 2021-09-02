<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Product" %>
<%@ page import="entity.Product" %>
<%@ page import="java.util.Base64" %>
<%@ page import="entity.Answer" %>
<%--
  Created by IntelliJ IDEA.
  User: walter
  Date: 24/04/21
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Home</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
</head>
<body id="page-top">


<nav class="navbar navbar-light bg-light static-top">
    <jsp:include page="menu.jsp" />
</nav>
<!-- Contact Section-->
<section class="page-section" id="contact">
    <div class="container" >
        <!-- Contact Section Heading-->
        <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Product of the day</h2>
        <br/>
        <br/>
    <div  class="d-flex justify-content-center">
    <%
        if(request.getAttribute("productOfTheDay")!=null){
                Product p = (Product) request.getAttribute("productOfTheDay");
                String productName = p.getProductName();
                int id = p.getProductId();
                byte[] productImage = p.getProductImage();
                String base64Encoded = new String(Base64.getEncoder().encode(productImage), "UTF-8");
    %>
    <div class="card" width: 600px>
        <div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
            <img
                src="data:image/png;base64,${requestScope['imageBt']}"

                    class="img-fluid"
            />
            <a href="#!">
                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
            </a>
        </div>
        <div class="card-body">
            <h5 class="card-title"><%=productName%></h5>


            <%
            if(session.getAttribute("alreadyAnsweredMsg") != null){
            %>
                <a href="#" style="pointer-events: none; cursor: default;" class="btn btn-primary">You have already answered this questionnaire!</a>

            <%
            }else if(session.getAttribute("currentUser") != null){
            %>
                <a href="AnswersServlet?productId=<%=p.getProductId()%>" class="btn btn-primary">Go to the questions</a>

            <%
            }else {
            %>
                <a href="login.jsp" class="btn btn-primary">Log in to answer the questionnaire</a>
            <%
            }
            %>


        </div>
    </div>

    <%
        }else{
    %>
        <h5 class="card-title">There is no product today</h5>
        <%
            }
        %>
    <br/>
    </div>
    </div>


    <section class="page-section" id="contact">
        <div class="container">
            <!-- Contact Section Heading-->
            <h4 class="page-section-heading text-center text-uppercase text-secondary mb-0">Answers:</h4>
            <br/>
            <br/>

            <table class="table">
                <thead>
                <tr>
                    <td>USER</td>
                    <td>QUESTION</td>
                    <td>ANSWER</td>
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
                            String user = a.getUserByUserId().getUsername();
                            String answer = a.getAnswerText();
                            String question = a.getQuestionByQuestionId().getQuestionText();
                %>
                <tr>
                    <td>
                        <%=user%>
                    </td>
                    <td>
                        <%=question%>
                    </td>
                    <td>
                        <%=answer%>
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



        </div>
    </section>


<br />
<br />






<!-- Footer-->
<footer class="footer text-center">
    <div class="container">
        <div class="row">
            <a2>
                <a href="LeaderboardServlet" title="">
                    LEADERBOARD
                </a>
            </a2>
        </div>
    </div>

</footer>
<!-- Bootstrap core JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<!-- Contact form JS-->
<script src="assets/mail/jqBootstrapValidation.js"></script>
<script src="assets/mail/contact_me.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
