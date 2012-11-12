<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Index</title>
	<s:head />
</head>
<body>
	<s:actionerror/>

	<s:if test="%{#session['loggedin'] != null}">
		<% response.sendRedirect(request.getContextPath() + "/jsp/home.jsp"); %>
	</s:if>
		
	<s:if test="%{#session['login_attempts'] > 1}">
		THIS TIME Captcha is supposed to be displayed.
		<!--TODO : reCaptcha-->
	</s:if>


	<s:form action="login">
		<s:textfield key="login" name="login" />
		<s:password key="password" name="password" />
		<s:submit />
	</s:form>

	<hr />
	<br />
	<s:a action="memberships">Need Account? Click here to register</s:a>
</body>
</html>
	