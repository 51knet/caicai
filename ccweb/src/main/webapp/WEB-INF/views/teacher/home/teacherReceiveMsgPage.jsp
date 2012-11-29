<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<a href='<c:url value="/admin/teacher/message/list"></c:url>' ><b>站内信</b></a><hr>
<div style="text-align: center;">
 <table class="table table-bordered">
 <thead><tr><th colspan="5">
 	收到的站内信数量：${msgCount}件 &nbsp;&nbsp;&nbsp;：
 	<a href='<c:url value="/admin/teacher/message/list"></c:url>'>未读信件数量${unReadCount}件</a>&nbsp;&nbsp;&nbsp;
 	<a href='<c:url value="/admin/teacher/message/isRead"></c:url>'>已读信件数量：${isReadCount}件</a>
 	<a href='<c:url value="/admin/teacher/message/isDele"></c:url>'>回收站：${isDeleCount}件</a>
 </th></tr></thead>
	<tbody>
	<tr><td>标题</td><td>是否已读</td><td>时间</td><td>发件人</td><td>操作</td></tr>
  		<c:forEach items="${page.content}" var = "page" >
  			<tr>
  				<td><a href='<c:url value="/admin/teacher/message/detailOne?mid=${page.sendMsg.id}&urmid=${page.id}"></c:url>'>${page.sendMsg.title}</a> </td>
  				<td>
  					<c:if test="${page.readed==1 }">
  						未读
  					</c:if>
  				</td>
  				<td>${page.sendMsg.date}</td>
  				<td>${page.sendMsg.user.name}</td>
  				<td><!-- 
  					<div class="btn-group"> 
						<button class="btn">更多</button>  
						<button class="btn dropdown-toggle" data-toggle="dropdown">   
						<span class="caret"></span> </button>
						<ul class="dropdown-menu">
							<li>详细</li>
							<li><a href='<c:url value="/admin/teacher/message/deleOne?mid=${page.sendMsg.id}"></c:url>'>删除</a> </li>
						</ul>
					 </div> -->
					 <a href='<c:url value="/admin/teacher/message/deleOne?mid=${page.sendMsg.id}"></c:url>'>删除</a>
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