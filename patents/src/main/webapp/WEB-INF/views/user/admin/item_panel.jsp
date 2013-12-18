<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.centralize.left {
	text-align: center;
	margin-bottom: 20px;
 	background-image:url('<c:url value='/resources/img/default/item-lefttop-bg.png'></c:url>');
 /*	background-color:#ebf4df; */
 	background-position:top center;
 	background-repeat:repeat;
	vertical-align: middle;
	height: 148px;
}
.left-menu-container {
background-color: #ebf4df;

}

.left-menu-container >div{
	background-image: url("<c:url value='/resources/img/default/blackline.png'></c:url>");
	background-position: center center;
	background-repeat: repeat-x;
	height: 20px;
	width: 100%;
	margin: 10px 1px ;
	text-align: center;
}
.left-menu-container >div >div{
	color: #666;
	font-weight: bold;
	font-size: 16px;
	width: 50px;
	height:100%;
	background-color: #ebf4df;
	margin-left: 20px;
}

.nav-tabs.nav-stacked > li > a {
text-align: left;
font-size: 15px;
color: #adc877;
font-weight: bold;
border:0px;
padding:13px 25px;
text-decoration: none;
}
.nav-tabs > li > a:hover {
color: #8aa942;
text-decoration: none;
background-color: #cee0ca;
font-weight: bold;

}
.nav-tabs > .active > a , .nav-tabs > .active > a:hover {
color: #8aa942;

text-decoration: none;
background-color: #cee0ca;
font-weight: bold;
}



</style>
<c:url var="avatar_url" value="${sessionUserInfo.avatar}"></c:url>
<div class="row-fluid centralize left ">
	<div class="row-fluid " style="border-bottom: 1.5px solid #9db84d;">
		<c:choose>
			<c:when test='${sessionUserInfo.avatar == "/resources/img/avatar/avatar90.png" || sessionUserInfo.avatar == "/resources/img/avatar/avatar91.png" }'>
				 <div style="background-image:url(${avatar_url}); background-repeat:no-repeat;background-position:center;width:60px;margin:10px auto;">
				    <div style="height: 35px;"></div>
				    <div style="height: 20px;background-color:gray;  padding:5px 5px;">
				    	<a href='<c:url value="/pcenter/details"><c:param name="active" value="photo" /></c:url>' >上传头像</a> 
				    </div>
			   </div>
			<div style="width: 60px;" id="content"><a href='<c:url value='/id/${sessionUserInfo.id}'></c:url>'><h4>${sessionUserInfo.name }</h4></a>	</div>
			</c:when>
			<c:otherwise>
				<table cellpadding="2" style="width: 100%;" border="0">
					<tr>
						<td align="left" valign="top" width="50%"><img src="${avatar_url}" style="margin: 10px 5px; width: 60px; " ></td>
						<td  align="left" valign="top" >
							<div style="width: 60px;" id="content"><a href='<c:url value='/id/${sessionUserInfo.id}'></c:url>'><h4>${sessionUserInfo.name }</h4></a>	</div>	
						</td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div class="left-menu-container border-green-all" >
	<br>
	<ul class="nav nav-tabs nav-stacked">
		<li><a href='<c:url value="/admin/resume"><c:param name="active" value="personal" /></c:url>' ><img src="<c:url value='/resources/img/default/tip.png'></c:url>" style="margin-right: 10px;">个人信息</a></li>
		<li><a href='<c:url value="/admin/details"><c:param name="active" value="photo" /></c:url>'><img src="<c:url value='/resources/img/default/tip.png'></c:url>" style="margin-right: 10px;">账号信息</a></li>
		<li><a href='<c:url value="/admin/requirement/list"></c:url>' ><img src="<c:url value='/resources/img/default/tip.png'></c:url>" style="margin-right: 10px;">我的需求</a></li>
		<li><a href='<c:url value="/admin/patent/list"></c:url>' ><img src="<c:url value='/resources/img/default/tip.png'></c:url>" style="margin-right: 10px;">我的专利</a></li>
	</ul>
</div>

<html>
<script type="text/javascript">
$(document).ready(function() {
	$('ul > li > a').each(function(index){
		if ($(this).attr('href') == (window.location.pathname+window.location.search) ||$(this).attr('href') == (window.location.pathname) ){
			$(this).parent().addClass('active');
		}
	});
});
</script>
</html>