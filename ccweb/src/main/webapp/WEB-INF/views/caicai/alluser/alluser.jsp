<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/loginCheck.js" />"></script>
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
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;

}
</style>
	<%
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		request.setAttribute("basePath", basePath);
	%>
<div  class="row-fluid custom round">
	<div class="row">
		<h4>用户管理</h4>
	</div>
	<div class="content">
		<div style="padding-bottom: 10px; text-align: right;">  
			<form class="navbar-form" action="<c:url value="/admin/caicai/user/search"></c:url>" method="post">
					 <input type="text" name="searchParam" class="span5" placeholder="输入邮箱搜索"  value="${searchParam }">
					 <button type="submit" class="btn" style=" margin-top:4px;font-family:Arial,'Microsoft YaHei'; color: #808080; ">邮箱搜索</button>
			</form>
		</div>
		<div class="tabbable">
			<ul class="nav nav-tabs">
		    	<li <c:if test='${active == "user"}'>class="active"</c:if>><a href='<c:url value="/admin/caicai/detail/user"></c:url>' >普通用户</a></li> 
		    	<li <c:if test='${active == "teacher"}'>class="active"</c:if>><a href='<c:url value="/admin/caicai/detail/teacher"></c:url>'>教师用户</a></li> 
		    	<li <c:if test='${active == "enterprise"}'>class="active"</c:if>><a href='<c:url value="/admin/caicai/detail/enterprise"></c:url>' >企业用户</a></li> 
			</ul>
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead><tr>
						<th  align="center" width="15%">名称</th>
						<th  align="center" width="15%">角色</th>
						<th  align="center" >电话</th>
						<th  align="center" >邮箱</th>
						<th  align="center" >注册时间</th>
						<th  align="center" width="20%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${userListCount >=0 }">
							<c:if test="${userListCount == 0 }">
								<tr><td colspan="5" >未搜索到</td></tr>
							</c:if>
							<c:forEach items="${userList }" var = "user">
								<tr>
									<td align="center">${user.name}</td> 
									<td align="center" >
										<c:if test="${user.role=='user' }">普通用户</c:if>
										<c:if test="${user.role=='teacher' }">教师用户</c:if>
										<c:if test="${user.role=='enterprise' }">企业用户</c:if>
									</td>
									<td align="center" >
										${user.cell_phone }
									</td>
									<td align="center" >
										<a href="mailto:${user.email }">${user.email }</a>
									</td>
									<td align="center" >
										${user.register_date}
									</td>
									<td align="center" >
										<c:if test="${user.forbidden=='yes' }"><a href='<c:url value="/admin/caicai/detail/${user.id}/free"></c:url>' >解禁</a></c:if>
										<c:if test="${user.forbidden== null }"><a href='<c:url value="/admin/caicai/detail/${user.id}/forbid"></c:url>' >禁用</a></c:if>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${page.content}" var="page">
								<tr>
									<td align="center">${page.name}</td> 
									<td align="center" >
										<c:if test="${page.role=='user' }">普通用户</c:if>
										<c:if test="${page.role=='teacher' }">教师用户</c:if>
										<c:if test="${page.role=='enterprise' }">企业用户</c:if>
									</td>
									<td align="center" >
										${page.cell_phone }
									</td>
									<td align="center" >
										<a href="mailto:${page.email }">${page.email }</a>
									</td>
									<td align="center" >
										${page.register_date}
									</td>
									<td align="center" >
										<c:if test="${page.forbidden=='yes' }"><a href='<c:url value="/admin/caicai/detail/${page.id}/free"></c:url>' >解禁</a></c:if>
										<c:if test="${page.forbidden== null }"><a href='<c:url value="/admin/caicai/detail/${page.id}/forbid"></c:url>' >禁用</a></c:if>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
		</div>
	</div>
</div>
