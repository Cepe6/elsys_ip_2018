<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My first jsp</title>
</head>
<body>
	<p>Hello, world!</p>
	<c:if test="${error != null}">
		<p>${error}</p>
	</c:if>
	<form action="admin" method="POST">
		Name: <input type="text" name="name"/><br />
		Password: <input type="password" name="password"/><br />
		<input type="submit" value="login"/>
	</form>

</body>
</html>