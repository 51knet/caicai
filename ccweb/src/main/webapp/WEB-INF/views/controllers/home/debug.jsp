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
<br/>
<a href="<c:url value="/usertype?userType=teacher"></c:url>">teacher admin page</a>
<table class="table table-bordered">
	<thead>
		<tr><th>User Id</th><th>User Email</th><th colspan="3">Actions</th></tr>
	</thead>
	<tbody>
		<c:forEach items="${userList}" var="user" varStatus="status">
		<tr>
			<td>${user.id}</td><td> ${user.email}</td>
			<td><a href="<c:url value="/one2one/${user.id}/${user.email}"></c:url>" title=" (colleague ${user.email}) based with user_id( ${user.id} ) ">create student</a>
</td>
<td><a href="<c:url value="/mail/${user.randomUrl}/${user.id}"></c:url>">Activate</a>
</td>
</tr>
		</c:forEach>
	</tbody>
</table>