<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<link href="<c:url value="/resources/js/uploadify3.2/uploadify.css" />" rel="stylesheet" type="text/css" />

<script type="text/javascript">

$(document).ready(function() {
	$("#authencation_form").submit(function(){
		//this.sync();
		return checkEmptyAjax('authencation_form','<c:url value="/admin/caicai/authentication/refuseAjax"></c:url>');
	});
});

function showReasonForm(){
	$("#reason_form").css("display","block");
}
function closeReasonForm(){
	$("#reason_form").css("display","none");
}
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
	<div class="row" >
		<h4>申请信息>详细信息</h4>
	</div>
	<div class="content">
		<table class="blue" id="mytab" cellpadding="4" width=100%  border=0>
			<tbody>
				<tr><td align="center" width="20%">标题</td><td align="left">${authentication.title}</td></tr> 
				<tr><td align="center">申请人</td><td align="left">${authentication.user.name}</td> </tr>
				<tr><td align="center">电话</td><td align="left">${authentication.user.fix_phone}</td></tr> 
				<tr><td align="center">邮箱</td><td align="left">${authentication.email}</td></tr> 
				<tr><td align="center">申请状态</td><td align="left" >
						<c:if test="${authentication.status=='pass' }">通过</c:if>
						<c:if test="${authentication.status=='submit' }">审核中</c:if>
						<c:if test="${authentication.status=='refuse' }">未通过</c:if>
					</td></tr>
				<tr><td align="center">申请时间</td><td  align="left"><fmt:formatDate value="${authentication.date}" pattern="yyyy-MM-dd HH:mm" /></td></tr>
				<tr><td align="center">具体内容</td><td  align="left">${authentication.content}</td></tr>
				<tr><td align="center">下载资料</td>
						<td align="center">
						<c:forEach items="${aResourceList }" var="resourceList">
							<div style="margin-right: 20px; float: left;"><a href='<c:url value="/admin/authentication/download/${resourceList.id }"></c:url>'>${resourceList.saveName}</a></div>
						</c:forEach>
						</td>
				</tr>
				<tr><td align="center" colspan="2"><a href='<c:url value="/admin/caicai/authentication/pass/${authentication.id }" ></c:url>' style="margin-right: 20px;">通过</a>
			<a href="javascript:void(0)" onclick="showReasonForm()">拒绝</a></td></tr>
			</tbody>
		</table>
	</div>
	<div class="content" id="reason_form" style="display: none;">
		<form:form action='refuse' method="post"  id="authencation_form" > 
			<input type="hidden" value="${authentication.id }" name="auth_id" /> 
			<div class="control-group" id="reason">
				<div class="controls">
					拒绝原因：<textarea style="width: 450px; height: 200px;" name="reason" ></textarea><span class="help-inline"></span>
				</div>
			</div>
			<div class="pull-left" style="margin-left: 70px;">
			<button type="submit" class="btn btn-success" >添加</button>&nbsp;&nbsp;
			<button type="reset" class="btn" onclick="closeReasonForm()">取消</button>
			</div>
		</form:form>
	</div>	
</div>

