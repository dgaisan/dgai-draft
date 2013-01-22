<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Index</title>
	<s:head />
</head>
<body>
	<s:actionerror/>

	<s:if test="%{#session['loggedin'] != null}">
		<% response.sendRedirect(request.getContextPath() + "/homepage.html"); %>
	</s:if>


	<s:form action="login" namespace="/">
		<s:textfield key="login" name="login" />
		<s:password key="password" name="password" />

		<s:if test="%{#session['login_attempts'] > 1}">
			<s:bean name="com.onlymega.dgaisan.html5maker.common.ReCaptchaAdaptor" var="recaptcha" />
			<s:property value="#recaptcha.html" escape="false" />
		</s:if>

		<s:submit />
	</s:form>

	<hr />
	<br />
	<s:a action="memberships">Need Account? Click here to register</s:a>
	<a href="jsp/restorepassword.jsp"> Forgot Password? </a>
	
	<br />
	<hr />
	<s:a action="design/create">Create New Banner</s:a>
	
</body>
</html>
	