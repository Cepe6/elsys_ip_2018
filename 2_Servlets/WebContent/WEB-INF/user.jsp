<%--
  Created by IntelliJ IDEA.
  User: Cepe6
  Date: 11/7/2017
  Time: 01:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    <table>
        <tr>
            Id: ${user.id}<br/>
        </tr>
        <tr>
            Username: ${user.name}<br/>
        </tr>
        <tr>
            Email: ${user.email}<br/>
        </tr>
        <tr>
            Password: ${user.password}
        </tr>
    </table>

    <a href="/user/change/?id=${user.id}"><button>Edit</button></a>
    <a href="/logout"><button>Logout</button></a>
</body>
</html>
