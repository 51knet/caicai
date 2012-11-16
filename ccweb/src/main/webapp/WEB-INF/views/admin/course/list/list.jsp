<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<a href='<c:url value="/admin/teacher/course/list"></c:url>' ><b>资源管理</b></a><hr>
<div style="text-align: center;">

	<table class="table table-bordered">
		<thead><tr><th colspan="4">全部课程</th></tr></thead>
		<tbody>
			<tr><td colspan="4">
					<a href="#myModal" role="button" class="btn" data-toggle="modal">添加新课程</a>
				</td></tr>
			<tr><td>课程标题</td><td>课程简述</td><td>发布时间</td><td>课程操作</td></tr>
			<c:forEach items="${page.content}" var="page">
				<tr><td align="left"><a href='<c:url value="/admin/teacher/course/view/${page.id}"></c:url>'>${page.courseName}</a></td>
				<td align="center">${page.courseDesc}</td>
				<td align="center">${page.courseDate}</td>
				<td align="center">
				 	<div class="btn-group"> 
						<button class="btn">更多</button>  
						<button class="btn dropdown-toggle" data-toggle="dropdown">   
						<span class="caret"></span> </button>
						<ul class="dropdown-menu">
							<li><a href='<c:url value="/admin/teacher/course/edit/${page.id}"></c:url>'>修改</a></li>
							<li><a href='<c:url value="/admin/teacher/course/destory/${page.id}"></c:url>'>删除</a></li>
						</ul>
					</div>
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
	
		
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">新课程添加</h3>
		</div>
		<form:form  action='new' method="post">
			<div class="modal-body">
				课程标题：<input type="text" name="courseName" placeholder="courseName">
				<span class="help-block"><form:errors path="courseName"></form:errors></span>
				课程描述：<textarea name="courseDesc" placeholder="courseDesc" cols="5" rows="8"></textarea>
				<span class="help-block"><form:errors path="courseDesc"></form:errors></span>
				<!-- 
					<button type="submit" class="btn btn-primary">OK</button>&nbsp;&nbsp;
					<button type="reset" class="btn" onclick="hidAnnoForm()">Cancel</button> -->
			</div>
			<div class="modal-footer">
				<button class="btn" type="reset" data-dismiss="modal" aria-hidden="true">取消</button>
				<button class="btn btn-primary" type="submit">保存</button>
			</div>
		</form:form>
	</div>
</div>