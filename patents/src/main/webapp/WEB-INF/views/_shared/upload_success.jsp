<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<style type="text/css">
.jumbotron.custom {
	background: url(<c:url value="/resources/img/slider.png"></c:url>) no-repeat;
	margin: 0px auto;
	width: 960px;
	height: 600px;
}

#errordiv1{
	margin:120px 0px 0px 250px;
	width:460px; 
	height:300px;
}
#errordiv2{
	height:250px;
	width:460px;
}
	
#errordiv2 div{
	font-family:"微软雅黑";
	font-size:12px;
	height:30px;
}

.erroeraddress{
	height:250px;
	font-size:18px;
	color:#990000;
	font-family:"微软雅黑";
}

body {
	max-width: 1600px;
	width: 100%;
}

.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}

.container-fluid .row-fluid {
	background: url(<c:url value="/resources/img/background.png"></c:url>) repeat;
}
</style>

<div class="jumbotron custom">
	<div id="errordiv1">
		<div class="erroeraddress">
			<input style="margin-bottom: 300px;" type="image" />
		  <h4>上传成功！！<a href='<c:url value="/"></c:url>'> 
		  点击返回首页 </a></h4>
		</div>
		<div id="errordiv2">
			<div>
				
			</div>
		</div>
	</div>
</div>

