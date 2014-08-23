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
		<h4>订单提交成功</h4>
		<hr>
				订单提交成功！我们会有专人在24个小时内主动联系您，尽请等待！<br>
			 	<a href="<c:url value="/teacher/${teacher_id}"></c:url>" class="btn btn-info ">点击返回</a>

	</div>
</div>
<script type="text/javascript">

</script>




