<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value='/resources/js/loginCheck.js'></c:url>"></script>
<style>
.patent{
	 width: 100%;
	padding:0px 30px;
}
.patent .top{
	font-size: 17px;
	color: #335183;
	padding:0px 0px 8px 10px;
	width: 85%;
}

.patent .middle{
	 font-size: 14px; 
	line-height: 25px;
}

.patent .bottom{
	padding:10px 10px;
	width: 85%;
}

.margin_left_40{
	margin-left: 40px;
}
.date{
	margin-left: 40px; font-size: 12px; color: #666;
	font-weight: normal;
}

.patent .bottom .limitTable{
	width:630px; 
	table-layout:fixed;
}

.patent .bottom .limitTable .limitTd{
	word-wrap:break-word; word-break:break-all;
}
</style>

 <div class="container patent">
	<div class="top btl">${patent.patentName }
	</div>
	<div class="bottom  ">	
	<br>
		<table width="95%" height="" border="0" cellpadding="5"  class="blue limitTable" >
			<tr>
			    <td width="122" align="center" bgcolor="#f3f3f3" >专利号码</td>
			    <td width="190" class="limitTd">${patent.patentNum}</td>
			    <td width="103" align="center" bgcolor="#f3f3f3" >申请日期</td>
			    <td width="169" class="limitTd">${patent.applicationDate}</td>
			  </tr>
			  <tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">适用领域</td>
			    <td width="190" class="limitTd">${patent.patentField}</td>
			    <td width="103" align="center" bgcolor="#f3f3f3">专利类型</td>
			    <td width="169" class="limitTd">${patent.patentType.typeName}</td>
			  </tr>
		
			  <tr>
			    <td align="center" bgcolor="#f3f3f3" >公开号码</td>
			    <td class="limitTd">${patent.publishNum}</td>
			    <td align="center" bgcolor="#f3f3f3" >公开日期</td>
			    <td class="limitTd">${patent.publishDate}</td>
			  </tr>
			   <tr>
			    <td align="center" bgcolor="#f3f3f3">联系人</td>
			    <td>
			    	<c:choose>
			    		<c:when test="${sessionUserInfo != null }">
			    			张小姐
			    		</c:when>
			    		<c:otherwise>
			    			<a class="loginPostBtn" href="#loginPostModal" role="button" data-toggle="modal" data-target="#loginPostModal">查看</a>
			    		</c:otherwise>
			    	</c:choose>
			    </td>
			    <td align="center" bgcolor="#f3f3f3" >联系电话</td>
			    <td>
			    	<c:choose>
			    		<c:when test="${sessionUserInfo != null }">
			    			021-68369338
			    		</c:when>
			    		<c:otherwise>
			    			<a class="loginPostBtn" href="#loginPostModal" role="button" data-toggle="modal" data-target="#loginPostModal">查看</a>
			    		</c:otherwise>
			    	</c:choose>
			    </td>
			  </tr>
			  
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">主分类号</td>
			    <td colspan="3"  class="limitTd">${patent.mainClassNum}</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">分类号码</td>
			    <td colspan="3"  class="limitTd">${patent.classNum }</td>
			  </tr>
			</table>
			<br>
	</div>
	<div class="top  btl">专利摘要</div>
	<div class="bottom  ">	
		${patent.summary }
	</div>
	
	<div class="top btl">评论</div>
	<div class="bottom  ">	
		尚未有人发表评论
	</div>
 </div>

<!-- login  -->
<div class="modal hide fade" id="loginPostModal" style="width: 500px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h4 id="myModalLabel">登录</h4>
	  </div>
	   <div style="padding: 10px 20px;">
			<h4>请登录后查看</h4>
		</div>
</div>
<script type="text/javascript">
function checkEmailAndPwd(){
	return checkEmailAndPass("login_form",'checkEmailAndPassword');
}
</script>
