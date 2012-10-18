<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function test(){
		//var id = ${sessionScope.userInfo.user.id};
		//alert(id+"---"+typeof id);
		var ids = document.getElementById("ids");
		alert(ids.value+"---"+typeof ids.value);
	
	}
</script>

<h1>Welcome to teacher home page.</h1>
<div style="text-align: center;">
	
	Welcome to teacher home page.<br>
	${userInfo.user.email }<br>
	${sessionScope.userInfo.user.id }<br>

	 <form action="addFriendsRelated" method="post" style="text-align:left">
		选择要发送请求的人：<br>
		<c:forEach items="${requestScope.allUser}" var="allUser" >
			<c:if test="${sessionScope.userInfo.user.id != allUser.id }">
				用户邮箱：<input id="ids" type="checkbox"  name="ids"   value="${allUser.id }" />${allUser.email}<br>
			</c:if>
		</c:forEach> 
		<br>
		<input type="submit" />
	</form>
	<br/>
<button onclick="test()">测试</button>
</div>