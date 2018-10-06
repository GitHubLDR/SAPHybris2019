<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
   <head>
      <title>Hello World</title>
   </head>
   
   <body>
   <div align="center">
   <font color="red">
      <h2>${message}</h2>
   </font>
   		<c:url value="/register-form" var="registerFormUrl"/>
   		<c:url value="/search-page" var="searchUrl"/>
   		<h3>
   			<a href="${registerFormUrl}">Registration form</a>
   			<a href="${searchUrl}">Search Student</a>
   		</h3>
   </div>
   </body>
</html>