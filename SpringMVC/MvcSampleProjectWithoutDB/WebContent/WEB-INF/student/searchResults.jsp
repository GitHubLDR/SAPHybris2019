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
		<fort style="color: deeppink; font-size: xx-large;"><h1> Student Search Page</h1></fort>
		<c:url value="/get-student-details" var="url"/>
		<label style="color: maroon; font-size: x-large;">Search by FirstName or LastName or Email</label></br>
			<form action="${url}">
				<input name="search" value="" style="border: double; padding: 8px 74px;"></br></br>
				<button type="submit" style="padding: 9px 35px;background: aqua;">Search</button>
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
						<h3><strong>${searchVal}</strong> Stundet Results not found!!!</h3>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</body>
</html>