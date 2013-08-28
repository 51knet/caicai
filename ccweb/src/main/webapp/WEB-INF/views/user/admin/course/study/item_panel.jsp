<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$(document).ready(function() {
	$('ul > li > a').each(function(index){
		if ($(this).attr('href') == (window.location.pathname+window.location.search)) {
			$(this).parent().addClass('active');
		}
	});
});
</script>
<style>

.nav-tabs.nav-stacked > li > a {
text-align: center;
font-size: medium;
font-family: 'Microsoft YaHei',Arial;
color: #df9a1b;
}
.nav-tabs > li > a:hover {
color: #8aa942;
text-decoration: none;
background-color: #c7daa3;
}
.nav-tabs > .active > a , .nav-tabs > .active > a:hover {
text-decoration: none;
background-color: #c7daa3;
color: #8aa942;

}

.left-menu-container {
background-color: #fff;
width: 148px;
}
.left-menu-container a{
color: #666;
}
</style>
<div class="left-menu-container">
	<ul  class="nav nav-tabs nav-stacked" style="width:150px;">
		<li><a href='<c:url value="/admin/mycourse/view/${course.id}"></c:url>'><font style="size: 22px">课程学习</font></a></li>
		<li><a href='<c:url value="/admin/mycourse/courseinfo/${course.id}"></c:url>'><font style="size: 22px">课程信息</font></a></li>
		<li><a href='<c:url value="/admin/mycourse/comment/${course.id}"></c:url>'><font style="size: 22px">用户评价</font></a></li>
		<c:if test="${knowledgeFlag == 'none' }">
			<li><a href='<c:url value="/admin/mycourse/knowledge/${course.id}"></c:url>'><font style="size: 22px">加入知识库</font></a></li>
		</c:if>
		<li><a href='<c:url value="/"></c:url>'><font style="size: 22px">返回首页</font></a></li>
	</ul>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('ul > li > a').each(function(index){
		if ($(this).attr('href') == (window.location.pathname+window.location.search) ||$(this).attr('href') == (window.location.pathname) ){
			$(this).parent().addClass('active');
		}
	});
});
</script>
