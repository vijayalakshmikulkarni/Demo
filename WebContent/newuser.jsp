<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
	
	<h3>Please provide your details here</h3>

	<form action="insertuser">
		<div>
			<label>User Name : </label> <input type="text" name="pname"
				required />
		</div>
		<div>
			<label>Email : </label> <input type="text" name="email" minlength="5"
				maxlength="70" required />
		</div>
		<div>
			<label>Contact Number :</label> <input type="number" name="pcontact"
				minlength="10" maxlength="10" required />
		</div>

		<button>SAVE</button>

	</form>
</body>
</html>