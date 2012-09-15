<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
	Welcome!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<c:if test="${user!=null}">
<P>  User is ${user} ${user["name"]}. </P>
</c:if>

<a href="<c:url value="/mail/randomUrl/id"></c:url>"  >/mail/randomUrl/id</a>