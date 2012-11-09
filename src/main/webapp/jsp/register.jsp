<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Register</title>
	<s:head />
</head>
<body>
	<s:if test="%{#session['loggedin'] != null}">
		<% response.sendRedirect(request.getContextPath() + "/jsp/home.jsp"); %>
	</s:if>

	<s:form action="preregister">
		<s:textfield key="login" name="login" />
		<s:textfield key="password" name="pass" />
		<s:textfield key="username" name="userName" />		
		<s:submit />
	</s:form>

</body>
</html>
