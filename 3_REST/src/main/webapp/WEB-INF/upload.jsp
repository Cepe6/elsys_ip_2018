<%--
  Created by IntelliJ IDEA.
  User: Cepe6
  Date: 1/10/2018
  Time: 02:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
</head>
<body>
<h1>Upload CSV</h1>

<form action="upload" method="post" enctype="multipart/form-data">

    <p>Select a file : <input type="file" name="file" size="45" accept=".csv" /></p>
    <input type="submit" value="Upload CSV" />

</form>

</body>
</html>
