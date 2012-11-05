<!DOCTYPE html>

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>Administration</title>
	</head>
	<body>
		<h1>Admin Panel</h1>
		Hi <s:property value="%{#session['theUser'].userName}" />
	</body>
</html>