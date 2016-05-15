<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<header>

</header>
<body>		
	<h2>Top 5 Websites Rankings</h2>
	
	<c:if test="${not empty visitlist}">	 
		<ul>
			<c:forEach var="listValue" items="${visitlist}">
				<li>${listValue}</li>
			</c:forEach>
		</ul>	 
	</c:if>
</body>
</html>
