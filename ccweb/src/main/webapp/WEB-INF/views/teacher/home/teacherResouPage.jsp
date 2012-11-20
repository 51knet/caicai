<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<a href='<c:url value="/admin/teacher/resource/list"></c:url>' ><b>资源管理</b></a><hr>
<div style="text-align: center;">
	<table class="table table-bordered">
	<thead><tr><th colspan="5">全部资源</th></tr></thead>
		<tbody>
		<tr><td colspan="5">
			<a href='<c:url value="/admin/teacher/resource/add"></c:url>' class="btn">添加资源</a>&nbsp;&nbsp;
			<a href='<c:url value="/admin/teacher/resource/type"></c:url>'  class="btn">类别管理</a><br></td></tr>
		<tr><td>文件名称</td><td>发布时间</td><td>文件描述</td><td>文件类型</td><td>详细操作</td></tr>
		<c:forEach items="${page.content}" var="page">
			<tr><td align="left">${page.name}</td>
			<td align="center">${page.date}</td>
			<td>
				<c:choose>
					<c:when test="${page.description==null || page.description=='' }">
						无
					</c:when>
					<c:otherwise>
						${page.description }
					</c:otherwise>
				</c:choose>
			</td>
			<td>${page.resourceType.typeName}</td>
			<td>
				 <div class="btn-group"> 
						<button class="btn">更多</button>  
						<button class="btn dropdown-toggle" data-toggle="dropdown">   
						<span class="caret"></span> </button>
						<ul class="dropdown-menu">
							<li><a href='<c:url value="/admin/teacher/resource/dele?id=${page.id }"></c:url>'> 删除</a></li>
							<li><a href="${page.savePath}">下载</a></li>
						</ul>
					 </div>
			</td></tr>
		</c:forEach>
		</tbody>
		<tfoot>
    <tr><td colspan="5">
        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
   		 </td></tr>
	</tfoot>
	</table>
	<br/>
</div>