<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<style type="text/css" media="screen">
.footerStyle{
	background:url(<c:url value="/resources/img/Footer_bg.jpg" />) repeat;
	height:180px;
	width:100%;
}
.footerTopStyle{
	background:url(<c:url value="/resources/img/Footer_Topbg.jpg" />) no-repeat;
	height:30px;
	line-height:30px;
	margin:0px auto;
	font-family:"Microsoft YaHei";
	font-size:14px;
	width:935px;
}
.footerinformation{
	color:#cccccc;
	font-family:"Microsoft YaHei";
	font-size:12px;
	margin-top:30px;
}
.footerTop{
	color:#cccccc;
	font-family:"Microsoft YaHei";
	font-size:12px;
	margin-top:5px;
}

</style>

<div style="margin-bottom: 0px; width: 100%;">
	<div class="footerStyle" align="center">
		<div class="footerTopStyle" align="center">
			<a href='<c:url value="/about"></c:url>'  class="ds-component">平台简介</a> | 
			<a href='<c:url value="/contact"></c:url>' >联系我们</a> | 
			<a href="#" class="ds-component" >友情链接</a> | 
			<a href='<c:url value="/legal"></c:url>'  >法律声明</a>
		</div>
		<div class="footerinformation">沪ICP备20110827号</div>
		<div class="footerTop">上海猜猜网络科技有限公司 版权所有 CopyRight © 2011-2012</div>
	</div>
</div>