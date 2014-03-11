<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/js/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
<!--
 function  NoPost(){
 	return false;
 }
//-->
</script>
<style>
.titlebg{
	background-color:#3d4f65; 
	font-size: 14px;
	width: 100%;
	color: #fff;
	font-weight: bold;
}
 .selete_filter{
 	margin-top:10px;
	text-align: left;
}
.patent{
	 width: 100%;

}
.content> form{
	margin-left: 20px;
}

</style>
<div class="container title"  >
		<div class="innerLeftTitle " >
			 <a href="<c:url value="#"></c:url>">上传成功</a>
		</div>
 </div>
 	
 <div class="container patent">
		<h4>上传成功，<a href="<c:url value='/fastupload'></c:url>">点击返回</a></h4>
 </div>



