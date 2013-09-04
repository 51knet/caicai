<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="span12">
	<div class="banner_front" style="position: relative; z-index: 1; height: 120px; background-color: #fff; margin-top: 100px;">
		<div style="height: 210px; width: 900px; position: absolute; z-index: 999; margin-left: 30px; margin-top: -70px; border: 0px solid #ccc; color: #000;">
			<c:url var="avatar_url" value="${userInfo.avatar}"></c:url>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
			  <tr>
			    <td rowspan="3" width="180"><a href='<c:url value='/user/${userInfo.id}'></c:url>'><img src="${avatar_url}" style="width:180px; height: 180px;"></a></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td valign="bottom"> <h4>${userInfo.name }</h4></td>
			  </tr>
			  <tr>
			    <td style="color: #444;" > <span class="color_green">性别：</span>${userInfo.user.gender } |<span class="color_green"> 住址：</span>${userInfo.user.address } | <span class="color_green">邮箱：</span>${userInfo.user.email }</td>
			  </tr>
			 </table>
		</div>
	
	</div>
	<!--/.banner -->
</div>
<!--/span-->
