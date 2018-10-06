<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
		<title>Student Registration Success</title>
	</head>
	<body>
		<div align="center">
		<c:choose>
			<c:when test="${msg}">
				<font color="green"><h1> Registration has been Success </h1></forn>
			</c:when>
			<c:otherwise>
				<font color="red"><h1> Registration has been failed!!!! </h1></forn>
			</c:otherwise>
		</c:choose>
			
		</div>
	</body>
</html>