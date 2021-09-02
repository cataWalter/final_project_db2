<%@ page import="entity.User" %>
<%@ page import="java.util.List" %><%--
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
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Home</title>
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


<nav class="navbar navbar-light bg-light static-top">
    <jsp:include page="menu.jsp"/>

</nav>
<!-- Contact Section-->
<section class="page-section" id="contact">
    <div class="container">
        <!-- Contact Section Heading-->
        <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Leaderboard</h2>
        <br/>
        <br/>
        <table class="table">
            <thead>
            <tr>

                <td>Position</td>
                <td>Username</td>
                <td>Points</td>
            </tr>
            </thead>
            <tbody>
            <%
                List<User> userList = null;
                if (request.getAttribute("users") != null) {
                    userList = (List<User>) request.getAttribute("users");
                    int i = 0;
                    for (User p : userList) {
                        i++;
                        String username = p.getUsername();
                        int points = p.getPoints();
            %>
            <tr>
                <td><%=i%>
                </td>
                <td><%=username%>
                </td>
                <td><%=points%>
                </td>
            </tr>
            <%
                }
            } else {
            %>

            <tr>
                <td>error</td>
                <td>error</td>
                <td>error</td>
            </tr>

            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <div class="col text-center">
        <a class="btn btn-primary" href="index.jsp">Home</a>
    </div>


</section>
<!-- Footer-->
<footer class="footer text-center">
    <div class="container">
        <div class="row">
            <!-- Footer About Text-->
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
