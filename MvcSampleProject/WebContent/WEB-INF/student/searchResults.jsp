<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
		<title>Student Search Results</title>
	</head>
	<body>
		<div align="center">
		<fort style="color: deeppink;"><h1> Student Search Page</h1></fort>
		<c:url value="/" var="url"/>
			<form action="${url}get-student-details">
				<input name="search" value="">
				<button type="submit">Search</button>
			</form>
			<hr>
			<c:if test="${isSearchResultPage}">
				<c:choose>
					<c:when test="${isSuccess}">
						<fort style="color: blue;"><h1> Student Search Results</h1></fort>
						<table border="1">
						<tr><th>First Name</th><th>Last Name</th><th>Email</th></tr>
						<c:forEach items="${listOfStudents}" var="student">
					   		<tr>
					   			<td>${student.firstName}</td><td>${student.lastName}</td><td>${student.email}</td>
					   		</tr>
					   	</c:forEach>
				   		</table>
					</c:when>
					<c:otherwise>
						<strong>${searchVal}</strong> Stundet Results not found!!!
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</body>
</html>