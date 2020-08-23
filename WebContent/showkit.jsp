<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items List</title>
</head>
<body>
	<jsp:include page="userheader.jsp" />
	<h3>Items</h3>
	
	<c:if test="${msg != null}">
			<p><strong>${msg }</strong>
		</c:if>
	
	<c:choose>
		<c:when test="${items==null || items.isEmpty() }">
			<p>No Items Found</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>ProductId</th>
					<th>ProductName</th>
					<th>Cost</th>
					<th>Quantity</th>
					<th>Amount</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${items }" var="item">
					<tr>
					<td>${item.productId }</td>
					<td>${item.productName }</td>
					<td>${item.cost }</td>
					<td>${item.quantity }</td>
					
					<td>${item.amount }</td>
					<td>
						<a href="deleteitemkit?productId=${item.productId }">DELETE</a> <span>|</span>
						
					</td>
				</tr>				
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>
