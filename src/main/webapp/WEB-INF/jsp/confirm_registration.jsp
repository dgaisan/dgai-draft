<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Registration Complete, but need to be confirmed</title>
	<s:head />
</head>
<body>

Confirmation Email was sent on <s:property value="login" />

<hr />

<s:url id="reUrl" namespace="/" action="re/confirmation" />


If you haven't received an email from us please click 
<s:a errorText="Sorry your request had an error." href="%{reUrl}">Here</s:a> to send a new one.

</body>
</html>
