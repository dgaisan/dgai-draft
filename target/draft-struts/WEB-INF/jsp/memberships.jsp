<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Membership</title>
	<s:head />
</head>
<body>
	<s:actionerror/>

	<br />


	<s:url id="membershipUrl1" namespace="/" action="register">
		<s:param name="membership" value="availableMemberships[ 0].name"/>
	</s:url>
	<s:a errorText="Sorry your request had an error." href="%{membershipUrl1}">
		<s:property value="availableMemberships[ 0].name" />		
	</s:a>

	<s:url id="membershipUrl2" namespace="/" action="register">
		<s:param name="membership" value="availableMemberships[ 1].name"/>
	</s:url>
	<s:a errorText="Sorry your request had an error." href="%{membershipUrl2}">
		<s:property value="availableMemberships[ 1].name" />		
	</s:a>

	<s:url id="membershipUrl3" namespace="/" action="register">
		<s:param name="membership" value="%{availableMemberships[ 2].name}"/>
	</s:url>
	
	<s:a errorText="Sorry your request had an error." href="%{membershipUrl3}">
		<s:property value="availableMemberships[ 2].name" />		
	</s:a>


	<hr />
	<br />
	
</body>
</html>
	