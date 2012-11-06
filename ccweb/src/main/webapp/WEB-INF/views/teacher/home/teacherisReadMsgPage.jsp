<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher message page.</h1>
<div style="text-align: center;">

	Welcome to teacher announcement page.<br>
	${userInfo.user.email }<br>
	${userInfo.user.id }<br>
	${userInfo.user.name}<br>

<!-- 
	
 -->
 收到的站内信数量：${msgCount}件 &nbsp;&nbsp;&nbsp;：<a href='<c:url value="/admin/teacher/message/list"></c:url>'>未读信件数量${unReadCount}件</a>&nbsp;&nbsp;&nbsp;<a href='<c:url value="/admin/teacher/message/isRead"></c:url>'>已读信件数量：${isReadCount}件</a><br/><br/>
 
 <table class="table table-bordered">
 <thead><tr><th>标题</th><th>是否已读</th><th>时间</th><th>发件人</th><th>操作</th></tr></thead>
	<tbody>
  		<c:forEach items="${page.content}" var = "page" >
  			<tr>
  				
  				<td>${page.sendMsg.title}</td>
  				<td>
  					<c:if test="${page.readed==2 }">
  						已读
  					</c:if>
  				</td>
  				<td>${page.sendMsg.date}</td>
  				<td>${page.sendMsg.user.name}</td>
  				<td><a href='<c:url value="/admin/teacher/message/detailOne?mid=${page.sendMsg.id}&urmid=${page.id}"></c:url>'>详细</a> |<a href="#">删除</a></td>
  			</tr>
  		</c:forEach>
  	</tbody>
  		<tfoot>
    	<tr><td colspan="5">
        	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
   		 </td></tr>
	</tfoot>
	</table>

</div>