<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
	Welcome!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<c:if test="${user!=null}">
<P>  User is ${user} ${user["name"]}. </P>
</c:if>

<a href="<c:url value="/mail/randomUrl/id"></c:url>"  >/mail/randomUrl/id</a>

<br/>
<a href="<c:url value="/student/1"></c:url>" >select student according to user_id(1)</a>
<br/>
<a href="<c:url value="/one2one/2/tongji"></c:url>" >create student (colleague tongji) based on user_id(2) </a>