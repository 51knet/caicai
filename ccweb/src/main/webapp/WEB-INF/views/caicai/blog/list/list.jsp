<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#t").focus(function() {
		$(".help-inline").html("");
	});
	$("#c").focus(function() {
		$(".help-inline").html("");
	});
});

</script>
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


<div class="row-fluid custom round">
	<div  class="row" >
		<h4>博文管理</h4>
	</div>
	<div class="content">
			<div style="padding-bottom: 10px; text-align: right;">  
			<form class="navbar-form" action="<c:url value="/admin/caicai/blog/search"></c:url>" method="post">
					 <input type="text" name="searchParam" class="span5" placeholder="输入博文标题搜索"  value="${searchParam }">
					 <button type="submit" class="btn" style=" margin-top:4px;font-family:Arial,'Microsoft YaHei'; color: #808080; ">标题搜索</button>
			</form>
		</div>
		<div style="text-align: right;">
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead><tr>
						<th  align="center">博文标题</th>
						<th  align="center">发布人</th>
						<th  align="center" width="20%">更新时间</th>
						<th  align="center" >权限状态</th>
						<th  align="center" width="20%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${blogListCount >=0 }">
							<c:if test="${blogListCount == 0 }">
								<tr><td colspan="5" align="left" >未搜索到</td></tr>
							</c:if>
							<c:forEach items="${blogList }" var = "blog">
								<tr>
									<td align="left" >${blog.title}</td>
									<td align="center" >${blog.author.user.name}</td>
								<td><fmt:formatDate value="${page.dateUpdated}" pattern="yyyy-MM-dd HH:mm"/>  </td>
									<td align="center">
										<c:if test="${blog.forbidden=='yes' }">已禁用</c:if>
										<c:if test="${blog.forbidden== null }">未禁用</c:if>
									</td>
									<td align="center">
										<c:if test="${blog.forbidden=='yes' }"><a href='<c:url value="/admin/caicai/blog/${blog.id}/free"></c:url>' >解禁</a></c:if>
										<c:if test="${blog.forbidden== null }"><a href='<c:url value="/admin/caicai/blog/${blog.id}/forbid"></c:url>' >禁用</a></c:if>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${page.content}" var="page">
								<tr>
									<td align="left" >${page.title}</td>
									<td align="center" >${page.author.user.name}</td>
									<td><fmt:formatDate value="${page.dateUpdated}" pattern="yyyy-MM-dd HH:mm"/>  </td>
									<td align="center">
										<c:if test="${page.forbidden=='yes' }">已禁用</c:if>
										<c:if test="${page.forbidden== null }">未禁用</c:if>
									</td>
									<td align="center">
										<c:if test="${page.forbidden=='yes' }"><a href='<c:url value="/admin/caicai/blog/${page.id}/free"></c:url>' >解禁</a></c:if>
										<c:if test="${page.forbidden== null }"><a href='<c:url value="/admin/caicai/blog/${page.id}/forbid"></c:url>' >禁用</a></c:if>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
			<br />
		</div>
	</div>	
</div>
