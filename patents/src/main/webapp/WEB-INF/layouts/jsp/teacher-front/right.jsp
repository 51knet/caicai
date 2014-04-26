<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">
	function checkCoursePwd(cid,tid){
		$("#errorMsg").html("");
		var pwd = $("#coursepwd").val();
		$.post('<c:url value="/checkCoursePwd" />', $("#checkpwd_form").serialize(), function(flag){
				if('true'==flag){
					$("#course_id").val(cid);
					$("#teacher_id").val(tid);
					$("#course_pwd").val(pwd);
					$("#showCourseDetail").submit();
				}else{
					$("#errorMsg").html("密码错误！");
					return false;
				}			
		}, "text");
	}
	
	function requestCourseDetail(cid,tid){
		$("#course_id").val(cid);
		$("#teacher_id").val(tid);
		$("#showCourseDetail").submit();
	}
</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;

}
.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 4px;
	margin: 20px 0px 0px 0px;
}
.row-fluid.custom .row {
	margin: 0px 40px 0px 40px;
}

.row-fluid.custom .row .bb{
	border-bottom: dashed  1px;
}
</style>
<!-- ${teacherInfo.id} -->
<div class="row-fluid custom round">
	<div class="row"><h4>公告 </h4></div>
	
	<div class="row">
		<c:choose>
			<c:when test="${annoCount>0}">
				<table cellpadding="4" width="100%" style="margin-top: 10px;">
					<tbody>
						<c:forEach var="anno" items="${annolist}" begin="0" end="2">
							<tr>
								<td  width="80%" class="bb"><a href="<c:url value="/teacher/${teacherInfo.id}/announcement/view/${anno.id}"></c:url>">${anno.title}</a>
								</td>
								<td class="bb">
									${anno.date}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
			<br>
				无内容
			</c:otherwise>
		</c:choose>
		<div style="text-align: right;">
			<c:if test="${annoCount>3}"><br><a href="<c:url value="/teacher/${teacherInfo.id}/announcement/list"></c:url>"> 查看所有>></a></c:if>&nbsp;&nbsp;</div>
		</div>
</div>

<!-- teacher resource -->
<div class="row-fluid custom round">
		<div class="row">
			<h4>文献资料</h4>
		</div>
		<div class="row">
			<c:choose>
				<c:when test="${resourceCount !=0}">
					<table  cellpadding="4" width="100%" style="margin-top: 10px;">
						<tbody>
							<c:forEach var="resource" items="${resourceList}">
								<tr>
									<td  width="80%"  class="bb"><a href="<c:url value="/teacher/${teacherInfo.id}/resource/view/${resource.id}"></c:url>"> ${resource.fileName } </a>
									</td>
									<td class="bb">
										${resource.date}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
				<br>
				无内容
				</c:otherwise>
			</c:choose>
		<div style="text-align: right;">
			<c:if test="${resourceCount>3}"><br><a href="<c:url value="/teacher/${teacherInfo.id}/resource/list"></c:url>"> 查看所有>></a></c:if>&nbsp;&nbsp;</div>
		</div>
</div>

<!-- teacher resource -->
<div class="row-fluid custom round">
		<div class="row">
			<h4>文献资料</h4>
		</div>
		<div class="row">
			<c:choose>
				<c:when test="${resourceCount !=0}">
					<table  cellpadding="4" width="100%" style="margin-top: 10px;">
						<tbody>
							<c:forEach var="resource" items="${resourceList}">
								<tr>
									<td  width="80%"  class="bb"><a href="<c:url value="/teacher/${teacherInfo.id}/resource/view/${resource.id}"></c:url>"> ${resource.fileName } </a>
									</td>
									<td class="bb">
										${resource.date}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
				<br>
				无内容
				</c:otherwise>
			</c:choose>
		<div style="text-align: right;">
			<c:if test="${resourceCount>3}"><br><a href="<c:url value="/teacher/${teacherInfo.id}/resource/list"></c:url>"> 查看所有>></a></c:if>&nbsp;&nbsp;</div>
		</div>
</div>

<div class="row-fluid custom round">
	<div class="row">
		<h4>博文</h4>
	</div>
	<div class="row">
	<table cellpadding="4" width="100%" style="margin-top: 10px;">
		<tbody>
			<c:forEach var="blogPost" items="${blogPosts}">
			<tr>
				<td width="80%" class="bb"><a href="<c:url value="/teacher/${teacherInfo.id}/blog/view/${blogPost.id}"></c:url>"> ${blogPost.title} </a></td>
				<td class="bb">
				<fmt:formatDate value="${blogPost.dateCreated}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>


