<%--
  Created by IntelliJ IDEA.
  User: Cepe6
  Date: 11/7/2017
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
<p>Add user</p>

<form action="/user/add" method="POST">
    <input type="text" name="name" placeholder="Name"/><br />
    <input type="text" name="email" placeholder="Email">
    <input type="password" name="password" placeholder="Password"/><br /><br />
    <input type="submit" value="Add"/>
</form>
</body>
</html>
