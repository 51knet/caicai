<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
	.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>
<div class="row-fluid custom round">
	<div class="row" style="margin-top: 10px;">
		<a href='<c:url value="/admin/teacher/message/list"></c:url>' ><b>站内信</b></a><hr>
		<div style="text-align: center; margin-bottom: 10px;">收到的站内信数量：${msgCount}件 &nbsp;&nbsp;&nbsp;：
			 	<a href='<c:url value="/admin/teacher/message/list"></c:url>'>未读信件数量${unReadCount}件</a>&nbsp;&nbsp;&nbsp;
			 	<a href='<c:url value="/admin/teacher/message/isRead"></c:url>'>已读信件数量：${isReadCount}件</a>&nbsp;&nbsp;&nbsp;
			 	<a href='<c:url value="/admin/teacher/message/isDele"></c:url>'>回收站：${isDeleCount}件</a>
		</div>
		<div style="text-align: right;">
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
			 	<thead>
					<tr><th width="30%" align="center">标题</th><th width="30%" align="center">发送时间</th><th width="20%" align="center">发件人</th><th width="20%" align="center">操作</th></tr>
				</thead>
				<tbody>
			  		<c:forEach items="${page.content}" var = "page" >
			  			<tr>
			  				<td align="center">${page.sendMsg.title}</td>
			  				<td align="center">${page.sendMsg.date}</td>
			  				<td align="center">${page.sendMsg.user.name}</td>
			  				<td align="center">
			  					<!--  <div class="btn-group"> 
									<button class="btn">更多</button>  
									<button class="btn dropdown-toggle" data-toggle="dropdown">   
									<span class="caret"></span> </button>
									<ul class="dropdown-menu">
										<li> </li>
									</ul>
								 </div> -->
								 <a href='<c:url value="/admin/teacher/message/destory?mid=${page.sendMsg.id}"></c:url>'>彻底删除</a>
			  				</td>
			  			</tr>
			  		</c:forEach>
			</table>
			<div class="row"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
		</div>
	</div>
</div>