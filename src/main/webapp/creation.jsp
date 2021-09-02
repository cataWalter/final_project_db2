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

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Creation</title>
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
        <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Creation</h2>
        <!-- Icon Divider-->
        <div class="divider-custom">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-line"></div>
            <div class="divider-custom-line"></div>
        </div>
        <!-- Contact Section Form-->

        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="CreationServlet" method="post">

                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls mb-0 pb-2">
                            <input class="form-control" id="inputUser" name="productName" type="text"
                                   placeholder="Product Name" required="required"
                                   data-validation-required-message="Insert product name"/>
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>


                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls mb-0 pb-2">
                            <label>Date</label>
                            <input class="form-control" id="inputUser" name="date" type="date" placeholder="date"
                                   required="required" data-validation-required-message="Insert date"/>
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="file" name="imageUpload" id="imageUpload" class="upload" />
                    </div>


                    <a4>How many marketing questions?</a4>
                    <input class="form-control" type="text" id="number" name="number" value=""/><br/>
                    <a href="#" id="filldetails" onclick="addFields()">
                        <button class="btn btn-warning">Fill Questions</button>
                    </a>
                    <br/>
                    <div class="form-group floating-label-form-group controls mb-0 pb-2" id="container"/>
                </form>
            </div>
        </div>
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

<script type='text/javascript'>
    function addFields() {
        // Number of inputs to create
        var number = document.getElementById("number").value;
        // Container <div> where dynamic content will be placed
        var container = document.getElementById("container");
        // Clear previous contents of the container
        while (container.hasChildNodes()) {
            container.removeChild(container.lastChild);
        }
        for (i = 0; i < number; i++) {

            // Append a node with a random text
            container.appendChild(document.createElement("br"));
            // Create an <input> element, set its type and name attributes
            var input = document.createElement("input");
            input.type = "text";
            input.className = "form-control";
            input.required = "required";
            input.placeholder = "Question " + (i + 1);
            input.name = "question_" + (i + 1);
            container.appendChild(input);

            container.appendChild(document.createElement("br"));
        }
        var submit = document.createElement("button");
        submit.className = "btn btn-primary btn-xl";
        submit.type = "submit";
        submit.textContent = "Submit";
        container.appendChild(submit);
    }
</script>
</body>
</html>



