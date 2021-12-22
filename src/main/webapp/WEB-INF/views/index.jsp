<%@page language="java" pageEncoding="ISO-8859-1"
		contentType="text/html; ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<title>Newz Application</title>
</head>
<body>
	<div align="center">
		<h2>Newz Application Assignment</h2>
		<form:form action="add" method="post" modelAttribute="news">
			<table border="0" cellpadding="5">
				<tr>
					<td>News Name</td>
					<td><form:input path="name" /></td>
					<td><form:hidden path="newsId" /></td>
				</tr>
				<tr>
					<td>News Author </td>
					<td><form:input path="author" /></td>
				</tr>
				<tr>
					<td>Description </td>
					<td><form:input path="description" /></td>
				</tr>
				<tr>
					<td>Content </td>
					<td><form:input path="content" /></td>
				</tr>
				<tr>
					<c:if test="${!isUpdate}">
						<td colspan="2"><input type="submit" value="Add News"></td>
					</c:if>
					<c:if test="${isUpdate}">
						<td colspan="2"><input type="submit" value="Update News"></td>
					</c:if>
				</tr>
			</table>
		</form:form>
	</div>
	
	<h1></h1>
	<table border="2" width="70%" cellpadding="2">
		<tr><th>News ID</th><th>News Name</th><th>Author</th><th>Description</th>
			<th>PublishedAt</th>
			<th>Content</th>
			<th></th>
		</tr>
		<c:forEach var="u" items="${newsList}">
			<tr>
				<td>${u.newsId}</td>
				<td>${u.name}</td>
				<td>${u.author}</td>
				<td>${u.description}</td>
				<td>${u.publishedAt}</td>
				<td>${u.content}</td>
				<td>
				<td><a href="update?newsId=${u.newsId}">Update</a></td>
				<td><a href="delete?newsId=${u.newsId}">Delete</a></td>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
</body>
</html>