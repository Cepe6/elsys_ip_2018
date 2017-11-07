<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Cepe6
  Date: 11/7/2017
  Time: 02:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <c:if test="${error != null}">
        <p>${error}</p>
    </c:if>
    <form action="/login" method="POST">
    <input type="text" name="name" placeholder="Name"/><br />
    <input type="password" name="password" placeholder="Password"/><br /><br />
    <input type="submit" value="login"/>
</form>
</body>
</html>
