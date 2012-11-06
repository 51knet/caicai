<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="text-align: center;">
	Your E-mail: ${userInfo.user.email }<br>
	Your ID: ${userInfo.user.id }<br>
	Your Name: ${userInfo.user.name}<br>
	<br>
	<a href='<c:url value="/admin/teacher/teacherCourse/add"></c:url>' >添加新课程</a><br>
	<table class="table table-bordered">
		<thead><tr><th>课程标题</th><th>课程简述</th><th>发布时间</th><th>课程操作</th></tr></thead>
		<tbody>
			<c:forEach items="${page.content}" var="page">
				<tr><td align="left"><a href='<c:url value="/admin/teacher/teacherCourse/detailCourse?id=${page.id}"></c:url>'>${page.courseName}</a></td>
				<td align="center">${page.courseDesc}</td>
				<td align="center">${page.courseDate}</td>
				<td align="center"><a href='<c:url value="/admin/teacher/teacherCourse/detailOne?id=${page.id}"></c:url>'>修改</a> | <a href='<c:url value="/admin/teacher/teacherCourse/deleCourse?id=${page.id}"></c:url>'> 删除</a></td></tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr><td colspan="4" align="right">
	        	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
	</table>
		<!--  <div class="pagination">
				<c:if test="${page.totalPages > 0}">
					<c:set var="prev" value="${page.number-1}" scope="page"></c:set>
					<c:set var="next" value="${page.number+1}" scope="page"></c:set>
					<ul>
						<c:choose>
							<c:when test="${page.hasPreviousPage()}">
								<li><a href='<c:url value="/admin/teacher/teacherCourse/detail?pageNumber=${prev}" />'>Prev</a></li></c:when>
							<c:otherwise>
								<li><a href="#" class="disabled">Prev</a></li></c:otherwise>
						</c:choose>
					    <li><a href="#" class="active">${page.number+1}/${page.totalPages}</a></li>
					    <c:choose>
							<c:when test="${page.hasNextPage()}">
								<li><a href='<c:url value="/admin/teacher/teacherCourse/detail?pageNumber=${next}" />'>Next</a></li></c:when>
							<c:otherwise>
								<li><a href="#" class="disabled">Next</a></li></c:otherwise>
						</c:choose>
					    
					  </ul>
				</c:if> 
			</div> -->
	<br/>

</div>