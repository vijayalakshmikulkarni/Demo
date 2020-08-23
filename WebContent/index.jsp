<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<html>
	<head>
		<title>Corona kit - Home</title>
	</head>
	<body>
	<h1>Corona Prevention kit</h1>
		<%-- <jsp:include page="header.jsp" /> --%>
		
		<c:if test="${msg != null}">
			<p><strong>${msg }</strong>
		</c:if>

	<h2>Admin Login</h2>
	<form action="login">
		<div>
			<div><label for="loginid">Enter login Id</label> </div>
			<div><input type="text" id="loginid" name="loginid" required> </div>
		</div>
		<div>
			<div><label for="password">Enter password</label> </div>
			<div><input type="text" id="password" name="password" required> </div>
		</div>
		<div>
			<div><input type="submit" value="Login"> </div>
		</div>
	</form>
</div>
<hr/>
<div>
	<!-- <a href="newuser"><button>New User Create Corona Kit</button></a> -->
	<a href="newuser" >NewUser Create Corona kit</a>
</div>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>
