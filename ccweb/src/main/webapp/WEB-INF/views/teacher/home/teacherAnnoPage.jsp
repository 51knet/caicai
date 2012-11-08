<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher announcement page.</h1>
<div style="text-align: center;">
	<table class="table table-bordered">
	<thead><tr><th colspan="3">全部公告</th></tr></thead>
	<tbody>
		<tr><td colspan="3"><a href='<c:url value="/admin/teacher/announcement/add"></c:url>' class="btn">添加公告</a><br></td></tr>
		<tr><td>公告标题</td><td>发布时间</td><td>详细操作</td></tr>
		<c:forEach items="${page.content}" var="page">
			<tr><td align="left">${page.title}</td><td align="center">${page.date}</td>
				<td>
					 <div class="btn-group"> 
						<button class="btn">更多</button>  
						<button class="btn dropdown-toggle" data-toggle="dropdown">   
						<span class="caret"></span> </button>
						<ul class="dropdown-menu">
							<li><a href='<c:url value="/admin/teacher/announcement/detailOne?id=${page.id}"></c:url>'>修改</a></li>
							<li> <a href='<c:url value="/admin/teacher/announcement/deleAnno?id=${page.id}"></c:url>'> 删除</a></li>
						</ul>
					 </div>
				 </td>
			</tr>
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
	    	<tr><td colspan="3" align="right">
	        	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
	</table>	 
	<br/>

</div>
