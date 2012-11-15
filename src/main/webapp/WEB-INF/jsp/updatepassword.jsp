<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Update your password</title>
	<s:head />
</head>
<body>

	<s:bean name="com.onlymega.dgaisan.html5maker.common.ReCaptchaAdaptor" var="recaptcha" />
	
	<s:form action="restore/password/update" namespace="/">
		<s:hidden name="activationCode" />
		<s:textfield key="restore.password.password" name="password" />
		<s:textfield key="restore.password.password.repeat" name="passwordRep" />

		<s:property value="#recaptcha.html" escape="false" />
		<s:submit />
	</s:form>

</body>
</html>
