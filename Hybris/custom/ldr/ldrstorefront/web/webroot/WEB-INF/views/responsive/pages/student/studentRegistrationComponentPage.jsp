<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<c:url value="${action}" var="url"/>


<form:form method="post" commandName="studentregistrationform" action="${url}">

	<formElement:formInputBox idKey="register.firstName"
		labelKey="register.firstName" path="firstName" inputCSS="form-control"
		mandatory="true" />
	<formElement:formInputBox idKey="register.lastName"
		labelKey="register.lastName" path="lastName" inputCSS="form-control"
		mandatory="true" />
	<formElement:formInputBox idKey="register.email"
		labelKey="register.email" path="email" inputCSS="form-control"
		mandatory="true" />
		
		
	 <div class="form-actions clearfix">
		<ycommerce:testId code="register_Register_button">
			<button type="submit" class="btn btn-default btn-block">
				<spring:theme code='${actionNameKey}' />
			</button>
		</ycommerce:testId>
	</div> 
</form:form>


