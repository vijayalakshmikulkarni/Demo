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
	
	<h3>${item.id==null?"New Item":"Edit Item" }</h3>
	
	<form action='${item.id==null?"addItem":"saveItem" }' method="POST">
		<div>
			<label>Id: </label>
			<input type="number" value="${item.id }" name="id" required 
			 ${item.id==null?"":"readonly" } />
		</div>
		<div>
			<label>Product Name:: </label>
			<input type="text" value="${item.productName }" name="pname" minlength="3" maxlength="20" required />
		</div>
		<div>
			<label>Cost</label>
			<input type="number" value="${item.cost }" name="cost" required />
		</div>
		<div>
			<label>Product description </label>
			<input type="text" value="${item.productDescription }" name="pdesc" required />
		</div>
		
		
		<button>SAVE</button>		
	</form>
</body>
</html>