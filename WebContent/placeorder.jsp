<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>place order Form</title>
</head>
<body>

	<jsp:include page="userheader.jsp" />
	
	<c:if test="${msg != null}">
			<p><strong>${msg } </strong>
			</c:if>
	
	<h3>Place order form </h3>
	
	<form action="saveorder">
			<div>
			<label>Delivery Address : </label>
			<input type="text"  name="paddress" required  />
		</div>
		<div>
		<label> Is Order Finalized : </label>
		<input type="radio" id="yes" name="orderfinal" value="yes" required>
		<label for="yes">Yes </label>
		<input type="radio" id="no" name="orderfinal" value="no">
		<label for="no">No </label>
		
		</div>
						
				
		<button>SAVE</button>	
			
	</form>
</body>
</html>