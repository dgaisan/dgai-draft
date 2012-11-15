<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>Dashboard</title>
		<s:head />
	</head>
	<body>
		<s:if test="%{#session['loggedin'] == null}">
			<% response.sendRedirect(request.getContextPath() + "/jsp/login.jsp"); %>
		</s:if>
		
		Hello <s:property value="%{#session['theUser'].userName}"/> <br/>
		<div style = "float: right"><s:a action="logout" namespace="/">Logout</s:a></div>
		
	</body>
</html>