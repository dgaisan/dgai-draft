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

	Should also have a captcha!

	<s:form action="register/free">
		<s:textfield key="register.login" name="login" />
		<s:password key="register.password" name="pass" />
		<s:password key="register.password.repeat" name="passRep" />
		<s:submit />
	</s:form>

</body>
</html>