<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"%>	
<%@ page import="java.util.*" %>
<%@taglib prefix="s" uri="/struts-tags" %>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Download</title>
	<s:head />
</head>
<body>
	<s:actionerror/>

	<%!
	String getContext(javax.servlet.http.HttpServletRequest request) {
		System.out.println( "In getContext" );
		return 
			request.getScheme() + "://" 
			+ request.getServerName()  
			+ (request.getServerPort() == 80 ? "" : ":"+request.getServerPort()) 
			+ request.getContextPath();
	}
	%>

	
	<a href="<%=getContext(request)%>/<s:property value='zip'/>"> <s:property value="text('com.onlymega.html5maker.download.zip')" default="Download Zip" /></a>



	<a href="saveBanner"><s:property value="text('com.onlymega.html5maker.download.savedesign')" default="Save design" /></a>

</body>
</html>