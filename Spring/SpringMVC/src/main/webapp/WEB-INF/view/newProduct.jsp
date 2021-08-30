<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new product</title>
</head>
<body>
    <h4>Add new product</h4>
    <form:form action="addProduct" modelAttribute="product">
        <p> Title <form:input path="title" type="text" /></p>
        <p> Price <form:input path="price" type="text" value="0"/></p>
        <p> <input type="submit" value="Add"/></p>
    </form:form>
</body>
</html>
