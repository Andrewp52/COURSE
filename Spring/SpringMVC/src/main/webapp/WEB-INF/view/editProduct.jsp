<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit product</title>
</head>
<body>
<h3>Edit product</h3>
<form:form action="addProduct" modelAttribute="product">
    <p>Title <form:input path="title" type="text" value="${product.title}" /></p>
    <p>Price <form:input path="price" type="number" value="${product.price}" /></p>
    <form:hidden path="id" value = "${product.id}" />
    <input type="submit" value="Save">
</form:form>
</body>
</html>
