<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value='/resources/js/loginCheck.js'></c:url>"></script>
<%
	
	String requestUrl = new org.springframework.web.util.UrlPathHelper().getOriginatingRequestUri(request);
	
	String currentUrl = requestUrl;
%>
<style>

.patent{
	 width: 100%;
	padding:10px 30px;
}
.patent .top{
	font-size: 17px;
	color: #335183;
	padding:10px 0px 8px 10px;
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
<div class="container title"  >
		<div class="innerLeftTitle">技术详情</div>
 </div>
 <div class="container patent">
	<div class="top ">${technology.techName}
		 <span style="float: right; margin-right: 60px;">	
				<a href="#" style="text-decoration: none; " class="dropdown-toggle"  data-toggle="dropdown"><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=826619119&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:826619119:41" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></a>
		</span>
	</div>
	<div class="bottom   tLine_dash">	
	<br>
		<table width="95%" height="" border="0" cellpadding="5"  class="blue limitTable" >
			<tr>
			    <td width="122" align="center" bgcolor="#f3f3f3" >所属领域</td>
			    <td width="190" class="limitTd">${technology.techField}</td>
			    <td width="103" align="center" bgcolor="#f3f3f3" >项目类型</td>
			    <td width="169" class="limitTd">${technology.techType}</td>
			  </tr>
			  <tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">成熟度</td>
			    <td width="190" class="limitTd">${technology.maturity}</td>
			    <td width="103" align="center" bgcolor="#f3f3f3">项目进度</td>
			    <td width="169" class="limitTd">${technology.progress}</td>
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
			    <td align="center" bgcolor="#f3f3f3">应用领域</td>
			    <td colspan="3"  class="limitTd">${technology.applyArea}</td>
			  </tr>	  
			   <tr>
			    <td align="center" bgcolor="#f3f3f3" >合作方式</td>
			    <td class="limitTd">${technology.cooperation}</td>
			    <td align="center" bgcolor="#f3f3f3" >对企业要求</td>
			    <td class="limitTd">${technology.demand}</td>
			  </tr>
			</table>
			<br>
	</div>
	<div class="top ">专利成果</div>
	<div class="bottom   tLine_dash">	
		${technology.achievement }
	</div>
	
	<div class="top ">技术简介</div>
	<div class="bottom   tLine_dash">	
		${technology.contents }
	</div>
	
	<div class="top ">技术优势</div>
	<div class="bottom   tLine_dash">	
		${technology.advantage}
	</div>
	
 </div>

<!-- login  -->
<div class="modal hide fade" id="loginPostModal" style="width: 500px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h4 id="myModalLabel">登录</h4>
	  </div>
	   <div style="padding: 10px 20px;">
			<form action="<c:url value='/signin'></c:url>" id="login_form"  method="post">
				<input type="hidden" value="<%=currentUrl  %>" name="currentUrl">
				<div class="control-group" id="email">
				
					<div class="controls">
						邮箱：<input type="text" name="email" placeholder="邮箱"> <span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group" id="password">
					
					<div class="controls">
						密码：<input type="password" name="password" placeholder="密码"><span class="help-inline"  id="passwordErr"></span>
					</div>
				</div>
				<div>
					<div class="control-group">
						<div class="controls ">
							<button style="margin-left: 42px;" type="submit"  class="btn btn-success " onclick="return checkEmailAndPwd();">登录</button>
							 <a style="margin-left: 10px;"  class="btn"  href="<c:url value='/jumpToPatents'></c:url>">注册</a> <a href="<c:url value='/jumpToPatents'></c:url>"  style="margin-left: 20px;"> 忘记密码？</a>
						</div>
					</div>
				</div>
			</form>
			
		
		</div>
</div>
<script type="text/javascript">
function checkEmailAndPwd(){
	return checkEmailAndPass("login_form",'<c:url value="/patent/checkEmailAndPassword"></c:url>');
}
</script>
