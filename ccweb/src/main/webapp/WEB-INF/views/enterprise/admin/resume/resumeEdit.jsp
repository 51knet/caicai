<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/resumeEdit.js" />"></script>
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
<script type="text/javascript">
/*   $(function(){
	  var mes=$("#message").attr("title");
	if(mes!=''){
		alert("信息已保存");
		return false;
	} 
	
}); */  
	setTimeout(function(){
 		document.getElementById("message").style.display="none";
	},2000);
	setTimeout(function(){
 		document.getElementById("mess").style.display="none";
	},2000);
	setTimeout(function(){
 		document.getElementById("messages").style.display="none";
	},2000);

 	  
</script>



<div class="row-fluid custom round">
	<div class="row">
		<h4>完善企业介绍</h4>
		<i class="icon-star"></i>
		<i>必须填写项</i>
	</div>
	<div class="content">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li <c:if test='${active == "personal"}'>class="active"</c:if>><a href="#personal_info_tab" data-toggle="tab">企业信息</a></li>
				<li <c:if test='${active == "contact"}'>class="active"</c:if>><a href="#contact_info_tab" data-toggle="tab">联系方式</a></li>
				<li <c:if test='${active == "honor"}'>class="active"</c:if>><a href="#honor_tab" data-toggle="tab">企业背景</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane <c:if test='${active == "personal"}'>active</c:if>" id="personal_info_tab">
					<form id="enterprise_info_form" action="enterprisepersonalInfo" class="form-horizontal" method="post">
						<div id="messages"  style="margin-left: 155px;"><h4 style="color: #adcc75">${message}</h4></div>
						<div class="control-group" id="name">
							<label class="control-label" for="name"><i class="icon-star"></i> 企业名称</label>
							<div class="controls">
								<input type="text" name="name" placeholder="企业名称" value="${sessionScope.sessionUserInfo.name}"> <span class="help-inline"><form:errors path="name"></form:errors></span>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick="return personalOnclick();" class="btn  btn-success">保 存</button>
							</div>
						</div>
					</form>
				</div>
				<div class="tab-pane <c:if test='${active == "contact"}'>active</c:if>" id="contact_info_tab">
					<form class="form-horizontal" action="contactInfo" method="post" id="teacher_contact_from">
					<div id="mess" style="margin-left: 155px;"><h4 style="color: #adcc75">${message}</h4></div>
						<div class="control-group" id="address">
							<label class="control-label" for="address">地址</label>
							<div class="controls">
								<input type="text"  name="address" placeholder="地址" value="${sessionScope.sessionUserInfo.user.address}">
								<span class="help-inline"><form:errors path="address"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="cellphone">
							<label class="control-label" for="cellphone">手机</label>
							<div class="controls">
								<input type="text"  name="cellphone" placeholder="手机号码" value="${sessionScope.sessionUserInfo.user.cell_phone}">
								<span class="help-inline"><form:errors path="cellphone"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="phone">
							<label class="control-label" for="phone">固话</label>
							<div class="controls">
								<input type="text"  name="phone" placeholder="固定电话" value="${sessionScope.sessionUserInfo.user.fix_phone}">
								<span class="help-inline"><form:errors path="phone"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="fax">
							<label class="control-label" for="fax">传真</label>
							<div class="controls">
								<input type="text"  name="fax" placeholder="传真号码" value="${sessionScope.sessionUserInfo.user.fax}">
								<span class="help-inline"><form:errors path="fax"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="qq">
							<label class="control-label" for="qq">QQ</label>
							<div class="controls">
								<input type="text" name="qq" placeholder="QQ" value="${sessionScope.sessionUserInfo.user.qq}">
								<span class="help-inline"><form:errors path="qq"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="msn">
							<label class="control-label" for="msn">MSN</label>
							<div class="controls">
								<input type="text"  name="msn" placeholder="MSN" value="${sessionScope.sessionUserInfo.user.msn}">
								<span class="help-inline"><form:errors path="msn"></form:errors></span>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick="reuturn:contactOnclick();"    class="btn  btn-success">保 存</button>
							</div>
						</div>
					</form>
				</div>
				
				<!-- honor -->
				<div class="tab-pane <c:if test='${active == "honor"}'>active</c:if>" id="honor_tab">
					<div id="honor" style="display: block">
						<div id="honorForm" style="display: none; padding-left: 20px;">
							<form action="honor/new" method="post" id="honor_info_Form" name="teacherHonor" >
							<input type="hidden" name="honorId">
								<div class="control-group" id="honorDesc">
									<div class="controls">
										<textarea  name="honorDesc" id="honorDescs" rows="6" cols="8" style="width: 600px; height:300px;"></textarea>
										<span class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<button type="submit"  class="btn  btn-success">保存</button>
										<button class="btn  " type="reset" onclick="hiddenHonorAddForm()">取消</button>
									</div>
								</div>
							</form>
						</div>
						<div id="honorList" style="display: block">
							<c:choose>
								<c:when test="${honorCount >0}">
									<table class="table ">
										<thead>
											<tr>
												<th  colspan="2">详细内容</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${honorList}" var="honor">
												<tr>
													<td colspan="2">${ honor.desc}</td>
												</tr>
												<tr>
													<!-- <td align="center">${honor.name}</td>
													<td align="center">${honor.reason}</td> -->
													<td width="85%"></td>
													<td><a class="deleteHonorPostBtn" href="#deleteHonorPostModal" role="button" data-toggle="modal" data-target="#deleteHonorPostModal">删除</a> 
														| <a href='javascript:void(0)' class="editHonorAjaxBtn">修改</a><input type="hidden" value="${honor.id}">
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:when>
								<c:otherwise>
									<span style="font-size: 13px;">尚未添加内容<br>
									<br></span>
								</c:otherwise>
							</c:choose>
							<button onclick="showHonorAddForm()" class="btn btn-success">添加</button>
						</div>
					</div>
		
					<!-- honorModal -->
					<div class="modal hide fade" id="deleteHonorPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">请注意</h3>
						</div>
						<div class="modal-body">
							<p>你确定删除吗？</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<form action='<c:url value="/admin/teacher/honor/destory"></c:url>' method="post" style="display: inline-block;">
								<input id="honorId" type="hidden" name="honorId" />
								<button class="btn btn-success">确定</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<c:url var="uploadJson" value="/file_upload/${sessionUserInfo.id}"></c:url>
<c:url var="fileManagerJson" value="/file_manager/${sessionUserInfo.id}"></c:url>
<link rel="stylesheet" href="<c:url value="/resources/kindeditor-4.1.3/themes/default/default.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.js"/>"></script>