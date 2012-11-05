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
		Hello <s:property value="%{#session['theUser'].userName}"/> <br/>
	</s:if>
	<s:else>
		<s:form action="login">
			<s:textfield key="login" name="login" />
			<s:password key="password" name="password" />
			<s:submit />
		</s:form>
	</s:else>
	<hr />
	<br />
	<s:a action="registerpage">Need Account? Click here to register</s:a>
</body>
</html>
	