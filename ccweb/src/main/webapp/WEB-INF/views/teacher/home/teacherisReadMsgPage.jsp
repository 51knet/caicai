<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher message page.</h1>
<div style="text-align: center;">
<br/>
 
 <table class="table table-bordered">
 <thead><tr><th colspan="5">
 	收到的站内信数量：${msgCount}件 &nbsp;&nbsp;&nbsp;：
 	<a href='<c:url value="/admin/teacher/message/list"></c:url>'>未读信件数量${unReadCount}件</a>&nbsp;&nbsp;&nbsp;
 	<a href='<c:url value="/admin/teacher/message/isRead"></c:url>'>已读信件数量：${isReadCount}件</a>
 	<a href='<c:url value="/admin/teacher/message/isDele"></c:url>'>回收站：${isDeleCount}件</a>
 </th></tr></thead>
	<tbody>
	<tr><td>标题</td><td>时间</td><td>发件人</td><td>操作</td></tr>
  		<c:forEach items="${page.content}" var = "page" >
  			<tr>
  				
  				<td>${page.sendMsg.title}</td>
  				<td>${page.sendMsg.date}</td>
  				<td>${page.sendMsg.user.name}</td>
  				<td>
  					 <div class="btn-group"> 
						<button class="btn">更多</button>  
						<button class="btn dropdown-toggle" data-toggle="dropdown">   
						<span class="caret"></span> </button>
						<ul class="dropdown-menu">
							<li><a href='<c:url value="/admin/teacher/message/detailOne?mid=${page.sendMsg.id}&urmid=${page.id}"></c:url>'>详细</a> </li>
							<li><a href='<c:url value="/admin/teacher/message/deleOneReaded?mid=${page.sendMsg.id}"></c:url>'>删除</a> </li>
							
						</ul>
					 </div>
  				</td>
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