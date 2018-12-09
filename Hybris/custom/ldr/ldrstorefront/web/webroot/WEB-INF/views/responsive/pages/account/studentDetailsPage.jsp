<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<template:page pageTitle="${pageTitle}">


<c:if test="${not empty students }">
<h1>Student Details</h1>
<c:forEach  items="${students}" var="stu">

Student Id:: ${stu.id}<br>
Student name:: ${stu.name}<br>
</c:forEach>


</c:if>

<c:if test="${not empty student}">
<h1>Student ${student.name} Details</h1>
Student Id:: ${student.id}<br>
Student name:: ${student.name}<br>


</c:if>
</template:page>
