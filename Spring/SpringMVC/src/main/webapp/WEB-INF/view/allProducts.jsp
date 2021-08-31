<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<h4>Products</h4>

<%-- Search by id form --%>
<br>
<form action="findId" method="get">
    Find by id <input name="id" type="number" value="0"/>
    <input type="submit" value="Find" />
</form>
<br>

<%-- Link to the new product form --%>
<a href ="newProduct">Add new product</a>

<%-- Current products list --%>
<ul>
    <c:forEach var="product" items="${products}">
        <li>
            <p> ID: ${product.id}
            <p> Title: ${product.title}
            <p> Price: ${product.price}
            <a href="edit?id=${product.id}">Edit</a> <a href="delete?id=${product.id}">Delete</a>
        </li>
    </c:forEach>
</ul>

</body>
</html>
