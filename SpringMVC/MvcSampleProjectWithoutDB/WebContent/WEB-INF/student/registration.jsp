<%@ page contentType="text/html; charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
   <head>
      <title>Student Registration</title>
   </head>
   <body>
   <div align="center">
   <h1> Student Registration Form</h1>
   <c:url var="url" value="/register"/>
   	<form:form method="post" modelAttribute="registrationForm" action="${url}">
	   	<table>
	   		<tr>
	   			<td>First Name :</td><td><form:input path="firstName" /></td>
	   		</tr>
	   		<tr>
	   			<td>Last Name :</td><td><form:input path="lastName" /></td>
	   		</tr>
	   		<tr>
	   			<td>Email :</td><td><form:input path="email" /></td>
	   		</tr>
	   		<tr align="center">
	   			<td><button type="submit"> Register</button></td>
	   		</tr>
	   	</table>
	</form:form>
   	</div>
   </body>
</html>
