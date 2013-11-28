<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
.titlebg{
	background-color:#ccdfa8; 
	font-size: 14px;
	width: 100%;
}
.patent{
	 width: 1024px;
	 margin: 10px 40px;
}
.patent .top{
	font-weight: bold;
	font-size: 17px;
	color: #3f5517;
	padding:10px 0px 8px 10px;
	width: 90%;
}

.date{
	margin-left: 40px; font-size: 12px; color: #666;
	font-weight: normal;
}

.patent .bottom{
	padding:10px 10px;
	width: 90%;
}
</style>
<div class="container title"  >
		<div class="innerLeftTitle">活动详情</div>
 </div>
 <div class="container patent">
	<div class="top ">${activity.title }<span class="date">发布于：<fmt:formatDate value="${activity.date}" pattern="yyyy-MM-dd "/></span></div>
	<div class="bottom  bLine_dash tLine_dash">	${activity.content }</div>
 </div>



