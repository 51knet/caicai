<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.nar >h4{
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 10px;
	padding-left:20px;
	margin: 10px 30px 0px 30px;
}
.cont{
	margin: 10px 30px 0px 30px;
	padding:0px 20px 10px 20px;
	width: 100%;
}
</style>
<div >
	<div  class="nar">
		<h4>加入知识库</h4>
	</div>
	<div class="cont"><br>
		加入知识库您可以讲该课程分享给更多好友<br><br>
		<form method="post" action="<c:url value='/admin/mycourse/knowledge/new'></c:url>" >
			<input type="hidden" value="${course_id }" name="course_id">
			<button type="submit" class="btn btn-success ">点击加入知识库</button>
		</form>
	</div>
</div>


