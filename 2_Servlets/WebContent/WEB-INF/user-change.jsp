<%--
  Created by IntelliJ IDEA.
  User: Cepe6
  Date: 11/7/2017
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change user information</title>
</head>
<body>
<p>Change information for ${user.name}</p>

<form action="/user/?id=${user.id}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <input type="text" name="changed_name" placeholder="Name"/><br />
    <input type="text" name="changed_email" placeholder="Email">
    <input type="password" name="changed_password" placeholder="Password"/><br /><br />
    <input type="submit" value="Change"/>
</form>


</body>
</html>
