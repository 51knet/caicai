<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>

.patent{
	 width:100%;

}
.patent .top{
	font-weight: bold;
	font-size: 17px;
	color: #3f5517;
	padding:10px 0px 8px 10px;
	width: 96%;
	text-align: center;
}

.date{
	font-size: 12px; color: #666;
	font-weight: normal;
}

.patent .bottom{
	padding:10px 10px;
	width: 96%;
}
</style>
<c:set scope="page" value="http://localhost:8080/patents" var="url"></c:set>
 <!-- 
 <div class="path_link"><a href="<c:url value='/'></c:url>" >首页 </a> >> <a href="<c:url value='/activity/list'></c:url>" >活动列表</a> >> 活动详情 </div>-->
<div class="container title"  >
		<div class="innerLeftTitle">活动详情</div>
 </div>
 <div class="container patent">
	<div class="top ">${activity.title }<br><span class="date">发布于：<fmt:formatDate value="${activity.date}" pattern="yyyy-MM-dd "/></span></div>
	<div class="bottom  bLine_dash tLine_dash">
	<img src="<c:url value='${url}/${activity.filePath }'></c:url>" style="margin: 10px 100px;"><br>
	${activity.content }
	</div>
 </div>



