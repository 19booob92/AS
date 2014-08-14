<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>HTTP Status 403</h1>

	<c:choose>
		<c:when test="${empty username}">
			<h2>Brak uprawnien !</h2>
		</c:when>
		<c:otherwise>
			<h2>Uzytkownik : ${username} <br/>Brak uprawnien !</h2>
		</c:otherwise>
	</c:choose>

</body>
</html>