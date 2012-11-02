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
 
 <table  border="1" cellspacing="0" cellpadding="5">
			<tr>
			<td>ID</td>
  			<td>标题</td>
  			<td>是否已读</td>
  			<td>时间</td>
  			<td>发件人</td>
  			<td>基本操作</td>
  		</tr>
  	
  		
  		<c:forEach items="${isReadMsgList}" var = "irm" >
  			<tr>
  				<td>${irm.sendMsg.id}</td>
  				<td>${irm.sendMsg.title}</td>
  				<td>
  					<c:if test="${irm.isRead==2 }">
  						已读
  					</c:if>
  				</td>
  				<td>${irm.sendMsg.date}</td>
  				<td>${irm.sendMsg.user.name}</td>
  				<td><a href='<c:url value="/admin/teacher/message/detailOne?mid=${irm.sendMsg.id}&urmid=${irm.id}"></c:url>'>详细</a> |<a href="#">删除</a></td>
  			</tr>
  		</c:forEach>
	</table>

</div>