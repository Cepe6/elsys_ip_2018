<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>
<form action="/user/search" type="GET">
	<input type="text" name="search-value">
	<select name="search-attribute">
		<option value="name">Name</option>
		<option value="email">Email</option>
	</select>
	<input type="submit" value="Search">
</form>

	<table>
		<tr>
			<th>ID</th>
			<th>Username</th>
			<th>Email</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.email}</td>
				<td><a href="/user/?id=${user.id}">Go to user page</a></td>
				<td><a href="/user/change/?id=${user.id}"><button>Edit...</button></a></td>
				<td><a href="/user/delete/?id=${user.id}"><button>Delete</button></a></td>
			</tr>
		</c:forEach>
	</table>

	<a href="/user/add"><button>Add new user...</button></a>
	<a href="/logout"><button>Logout</button></a>
</body>
</html>