<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
</head>
<body>
	<jsp:include page="adminheader.jsp" />
	<h3>Error: ${errMsg }</h3>
	<p>We regret the inconvinience caused
	please report it to the admin, if problem persists.</p>
</body>
</html>