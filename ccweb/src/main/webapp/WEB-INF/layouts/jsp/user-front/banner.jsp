<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.navbars{
	background-image: url("<c:url value='/resources/img/default/user-navbar-bg.png'></c:url>");
	background-position: left top;
	background-repeat: no-repeat;
	height: 50px;
	margin-bottom: 20px;
	margin-top:10px;
	text-align: left;
	background-color: #fff
}
.navbars >div{
	float: left;
	margin: 12px 25px 0px 35px;
	vertical-align: text-top;
}
<script type="text/javascript">
	$(document).ready(function() {
		var contentEditor = KindEditor.create('textarea[name="content"]',{
			cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
			uploadJson : '${uploadJson}',
			fileManagerJson : '${fileManagerJson}',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				KindEditor.ctrl(document, 13, function() {
					self.sync();
					document.forms['detail_form'].submit();
				});
				KindEditor.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['detail_form'].submit();
				});
			}
		});
		$("#sendMsg_info_form").submit(function(){
			contentEditor.sync();
			return checkEmptyAjax("sendMsg_info_form","sendMsgInfoAJAX");
		});
		prettyPrint();
		$("#t").focus(function() {
			$(".help-inline").html("");
		});
		$("#c").focus(function() {
			$(".help-inline").html("");
		});
	});
	</script>
</style>
<div style="background-color: #fff;">
	<div  style="position: relative; z-index: 1; height: 120px; background-color: #fff; margin-top: 100px;">
		<div style="height: 210px; width: 900px; position: absolute; z-index: 999; margin-left: 30px; margin-top: -70px; border: 0px solid #ccc; color: #000;">
			<c:url var="avatar_url" value="${userInfo.avatar}"></c:url>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
			  <tr>
			    <td rowspan="4" width="180"><a href='<c:url value='/user/${userInfo.id}'></c:url>'><img src="${avatar_url}" style="width:180px; height: 180px;"></a></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td valign="bottom"><br> <h4>${userInfo.name }</h4></td>
			  </tr>
			  <tr>
			    <td style="color: #444;"  valign="bottom"> <span class="color_green">性别：</span>${userInfo.user.gender } |<span class="color_green"> 住址：</span>${userInfo.user.address } | <span class="color_green">邮箱：</span>${userInfo.user.email }
			   
			    </td>
			  </tr>
			  <tr>
			  	<td  valign="top">
			  		 	<c:if test="${(sessionUserInfo!=null) && (sessionUserInfo.id != user_id) }">
							
								<c:if test="${! sessionScope.isFollower}">
									<a href='<c:url value='/addrelation?uid=${userInfo.id}'></c:url>' id="attention" class="btn btn-success btn-small">+关注</a>
								</c:if>
								<c:if test="${sessionScope.isFollower}">
									<a href='<c:url value='/delerelation?uid=${userInfo.id}'></c:url>' id="attention" class="btn  btn-small">取消关注</a>
								</c:if>
								<!-- <a href='<c:url value='/sendmessage?uid=${userInfo.id}'></c:url>' class="btn btn-small">发私信</a>  -->
								<a href="#myModal" role="button" class="btn btn-small" data-toggle="modal">发私信</a>
					
						</c:if>
			  	</td>
			  </tr>
			 </table>
		</div>
	
	</div>
	<div class="navbars">
	<div><a href='<c:url value='/user/${userInfo.id}'></c:url>'><img src="<c:url value='/resources/img/default/front-home.png'></c:url>" ></a></div>
	<div><a href='<c:url value='/user/${userInfo.id}'></c:url>'><img src="<c:url value='/resources/img/default/front-resume.png'></c:url>" ></a></div>
	<div><a href='<c:url value='/user/${userInfo.id}'></c:url>'><img src="<c:url value='/resources/img/default/front-friend.png'></c:url>" ></a></div>
</div>
</div>

<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">发送信件</h3>
	</div>
	<form action='<c:url value='/message/sendMsgInfo'></c:url>' method="post" id="sendMsg_info_form">
		<div class="modal-body" >
			<input type="hidden" value="${userInfo.id}" name="uid"> 
		</div>
		<div class="control-group" id="title" style="padding-left: 20px;">
			<div class="controls">
			信件标题：
				<input type="text" name="title" id="t" placeholder="信件标题"> 
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group" id="content" style="padding-left: 20px;">
			<div class="controls" >
			信件内容：
				<textarea  id="KEContent" name="content" placeholder="信件内容" id="c" cols="5" rows="8" style="width:450px;"></textarea>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group" style="float: right; margin-right: 20px;">
			<div class="controls">
				<button type="submit"  class="btn  btn-success">发送</button>
				<button type="reset" data-dismiss="modal" class="btn ">取消</button>
			</div>
		</div>
	</form>
</div>
	<!--/.banner -->

<!--/span-->
