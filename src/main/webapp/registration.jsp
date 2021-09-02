<%--
  Created by IntelliJ IDEA.
  User: walter
  Date: 24/04/21
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="service.*" %>
<%@ page import="entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Registration</title>
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
        <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Registration</h2>
        <!-- Icon Divider-->
        <div class="divider-custom">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-line"></div>
            <div class="divider-custom-line"></div>
        </div>
        <!-- Contact Section Form-->

        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="RegistrationServlet" method="post">
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls mb-0 pb-2">
                            <label>Username</label>
                            <input class="form-control" id="inputUser" name="username" type="text"
                                   placeholder="Username" required="required"
                                   data-validation-required-message="Inserisci il tuo username."/>
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls mb-0 pb-2">
                            <label>Email Address</label>
                            <input class="form-control" id="inputEmail" name="email" type="email" placeholder="Mail"
                                   required="required" data-validation-required-message="Inserisci la tua mail."/>
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls mb-0 pb-2">
                            <label>Password</label>
                            <input class="form-control" id="inputPassword" name="password" type="password"
                                   placeholder="Password" required="required"
                                   data-validation-required-message="inserisci la password."/>
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group">
                        <button class="btn btn-primary btn-xl" type="submit">Submit</button>
                    </div>
                </form>
            </div>
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
