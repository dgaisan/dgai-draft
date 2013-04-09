<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Restore password</title>
	<s:head />
</head>
<body>
	<s:if test="%{#session['loggedin'] != null}">
		<% response.sendRedirect("homepage.html"); %>
	</s:if>

	<s:actionerror/>

	<hr />

	Enter your email and we will send you your activation code.

	<s:bean name="com.onlymega.dgaisan.html5maker.common.ReCaptchaAdaptor" var="recaptcha" />

	<s:form action="restore/password" namespace="/">
		<s:textfield name="login" label="Email" />
		<s:property value="#recaptcha.html" escape="false" />

		<s:submit />
	</s:form>

</body>
</html>
	