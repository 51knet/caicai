<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<script type="text/javascript">
$(document).ready(function(){
	$('#email').autoMail({
		emails:['qq.com','163.com','126.com','sina.com','sohu.com','yahoo.cn','gmail.com','hotmail.com','live.cn']
	});
});
</script>
</head>
<h1>
	Welcome!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<c:if test="${sessionUserInfo!=null}">
<P>  User is ${sessionUserInfo} ${sessionUserInfo.user["name"]}. </P>
</c:if>

<a href="<c:url value="/mail/randomUrl/id"></c:url>"  >/mail/randomUrl/id</a>

<br/>
<a href="<c:url value="/student/1"></c:url>" >select student according to user_id(1)</a>
<br/>
<a href="<c:url value="/one2one/2/tongji"></c:url>" >create student (colleague tongji) based on user_id(2) </a>
<br/>
<a href="<c:url value="/user/dispatcher?userType=teacher"></c:url>">teacher admin page</a>
<br />
<a href="<c:url value="/admin/blog/list" /> ">博客列表</a>
<br />
<a href="<c:url value="/admin/blog/new" />">写博客</a>
<br />
<hr/>
<table class="table table-bordered">
	<thead>
		<tr><th>User Id</th><th>User Email</th><th colspan="3">Actions</th></tr>
	</thead>
	<tbody>
		<c:forEach items="${userList}" var="user" varStatus="status">
		<tr>
			<td>${user.id}</td><td> ${user.email}</td>
			<td><a href='<c:url value="/one2one/${user.id}/${user.email}"></c:url>' title=" (colleague ${user.email}) based with user_id( ${user.id} ) ">create student</a>
</td>
<td><a href="<c:url value="/mail/${user.randomUrl}/${user.id}"></c:url>">Activate</a>
</td>
</tr>
		</c:forEach>
	</tbody>
</table>

<form action="">
	<input id="email" type="text" name="email" />
	<input type="submit" value="提交" />
</form>