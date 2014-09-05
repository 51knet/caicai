<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/js/emptyCheck-ajax.js" />"></script>
<style>
	.fwlc{
		padding: 10px 20px; border: 1.5px solid #d3e1ee; margin-top: 10px;line-height:23px;
		background-image:url('<c:url value="/resources/img/default/lcbg.jpg"></c:url>'); background-position: left top; background-repeat: repeat;
	}
</style>
<c:url var="avatar_url" value="${teacherInfo.avatar}"></c:url>
<div style="border: 1px solid #ccc; padding: 50px 20px 20px 20px;">
	<div class="fwlc">
		<table width="100%"  cellpadding="8" >
			<tbody>
				<tr>
					<td width="100px;"><img width="100px" height="100px" src="${avatar_url}" ></td>
					<td valign="top">
					<h4>您正在申请与 <span style="color: red;">${teacherInfo.name }</span> 通话</h4>
					所属院系： ${teacherInfo.teacher.school}&nbsp;&nbsp;
					研究方向： ${teacherInfo.teacher.major} &nbsp;&nbsp; 
					职称/职务：${teacherInfo.teacher.title}
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="fwlc">
		<b>服务流程： 1：提交订单 —> 2：为订单支付 —> 3：确认通话时间 —> 4：与专家通话</b><br>
		如您不熟悉网络操作，可拨打：<b> <span style="color: red;">68369338-806</span></b>，由客服人员替您完成申请
	</div>
	
	
	<div class="fwlc">
		<h4>订单提交</h4>
		<h5><i class="icon-star"></i><i>必须填写项</i></h5>
 		<h5><i class="icon-star"></i><i>填写的邮箱为登录账号，请牢记。密码会自动发至邮箱</i></h5>
		<hr>
		<form action="<c:url value='/teacher/${teacher_id}/consult/pay'></c:url>" method="post" id="cart_form">
		<div class="row-fluid">
			<div class="control-group" id="title">
				<div class="controls">
					<i class="icon-star"></i> 咨询标题：<input type="text" name="title"   placeholder="咨询标题"  > <span class="help-inline"><form:errors path="title" /></span>
				</div>
			</div>
			<div class="control-group" id="content">
					<div class="controls  " >
						<i class="icon-star"></i> 咨询内容：<textarea  style="width:400px;height:100px;"  name="content"  placeholder="咨询内容"></textarea>
						<span class="help-inline"><form:errors path="summary" /></span>
					</div>
				</div>
		</div>
		<div class="row-fluid">
			<div class="control-group" id="username">
				<div class="controls">
					<i class="icon-star"></i> 真实姓名：<input type="text" name="username"   placeholder="真实姓名"  > <span class="help-inline"><form:errors path="username" /></span>
				</div>
			</div>
			<div class="control-group" id="phone">
				<div class="controls">
					<i class="icon-star"></i> 联系电话：<input type="text" name="phone"   placeholder="联系电话"  > <span class="help-inline"><form:errors path="phone" /></span>
				</div>
			</div>
			<div class="control-group" id="email">
				<div class="controls">
					<i class="icon-star"></i> 联系邮箱：<input type="text" name="email"   placeholder="联系邮箱"  > <span class="help-inline"><form:errors path="email" /></span>
				</div>
			</div>
			<div >
			 	<button type="submit" class="btn btn-info ">下一步</button>&nbsp;&nbsp;<a href="<c:url value="/teacher/${teacher_id}/consult"></c:url>" class="btn  ">上一步</a>
			</div>
		</div>
		</form>
	</div>
</div>
<script type="text/javascript">
		$(document).ready(function() {			
			$("#cart_form").submit(function(){
				
				return checkEmptyAjax("cart_form","<c:url value='/teacher/consult/cartInfoAjax'></c:url>");
			});
	    });
</script>




