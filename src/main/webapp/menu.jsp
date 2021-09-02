<div class="container">
    <%
        if (session.getAttribute("currentUser") == null) {
    %>
    <a class="btn btn-primary" href="registration.jsp">Registration</a>
    <a class="btn btn-primary" href="index.jsp">Home</a>
    <a class="btn btn-primary" href="login.jsp">Log In</a>
    <%
    } else {
    %>
    <p>Logged as: ${currentUser.getUsername()}</p>
    <form action="LogOutServlet" method="post">
        <input type="submit" class="btn btn-primary" value="Log out">
    </form>
    <%
        }
    %>
</div>