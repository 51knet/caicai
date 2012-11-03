<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div style="text-align: center;">

	Welcome to teacher announcement page.<br>
	${userInfo.user.email }<br>
	${userInfo.user.id }<br>
	${userInfo.user.name}<br>
	<a href='<c:url value="/admin/teacher/announcement/add"></c:url>'>添加公告</a><br>
	<table class="table table-bordered">
	<thead><tr><th>公告标题</th><th>发布时间<th>详细操作</th></tr></thead>
	<tbody>
		
		<c:forEach items="${page.content}" var="page">
			<tr><td align="left">${page.title}</td><td align="center">${page.date}</td><td><a href='<c:url value="/admin/teacher/announcement/detailOne?id=${page.id}"></c:url>'>修改</a> | <a href='<c:url value="/admin/teacher/announcement/deleAnno?id=${page.id}"></c:url>'> 删除</a></td></tr>
		</c:forEach>
	</tbody>
		<!--  <div class="pagination">
				<c:if test="${page.totalPages > 0}">
					<c:set var="prev" value="${page.number-1}" scope="page"></c:set>
					<c:set var="next" value="${page.number+1}" scope="page"></c:set>
					<ul>
						<c:choose>
							<c:when test="${page.hasPreviousPage()}">
								<li><a href='<c:url value="/admin/teacher/announcement/detail?pageNumber=${prev}" />'>Prev</a></li></c:when>
							<c:otherwise>
								<li><a href="#" class="disabled">Prev</a></li></c:otherwise>
						</c:choose>
					    <li><a href="#" class="active">${page.number+1}/${page.totalPages}</a></li>
					    <c:choose>
							<c:when test="${page.hasNextPage()}">
								<li><a href='<c:url value="/admin/teacher/announcement/detail?pageNumber=${next}" />'>Next</a></li></c:when>
							<c:otherwise>
								<li><a href="#" class="disabled">Next</a></li></c:otherwise>
						</c:choose>
					    
					  </ul>
				</c:if> 
			</div>
			 -->
			 
			 <tfoot>
    <tr><td colspan="5">
        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
   		 </td></tr>
	</tfoot>
		</table>	 
	<br/>

</div>
<div class="btn-group"> 
		<button class="btn">Action</button>  
		<button class="btn dropdown-toggle" data-toggle="dropdown">   
		<span class="caret"></span> </button>
		<ul class="dropdown-menu">
			<li>eeee</li>
			<li>ddddd</li>
		</ul>
</div>