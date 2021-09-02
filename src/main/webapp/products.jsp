<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>

    <% if (request.getAttribute("productList") == null) {
    %>
    NULL
    <% } else {
        Product product = new Product();
        List<Product> productList = null;
        productList = (List<Product>) request.getAttribute("productList");
        for (Product p : productList) {
            String name = p.getProductName();
    %>
    <%=name%>
    <%
            }
        }
    %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>