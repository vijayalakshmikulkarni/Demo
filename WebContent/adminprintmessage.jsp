<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Form</title>
</head>
<body>

	<jsp:include page="adminheader.jsp" />
	
	<c:if test="${msg != null}">
			<p><strong>${msg }</strong>
		</c:if>
		
</body>
</html>