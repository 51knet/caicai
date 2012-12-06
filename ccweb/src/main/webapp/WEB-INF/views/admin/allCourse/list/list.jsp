<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
	function showAll(){
		$.ajax({
			  type: "post",
			  url:  "<c:url value='/admin/teacher/course/all/list' />",
			  data: "school=all",
			  dataType:"text",
			  success:function(){
				 	window.location.href='<c:url value="/admin/teacher/course/all/list"></c:url>';
			  }
		});
	}
</script>


<div class="row-fluid custom round" style="text-align: center;">
	<div  class="row" style="margin-top: 10px; text-align: center;">
		<div style="text-align: center; width:700px; height: 230px;">
			<c:forEach items="${cb}" begin="1" end="6" var="cb">
				<div id="${cb.course.courseName}" style="float: left;border: 1px solid #cccccc; width:210px; height: 80px; margin-left: 16px; margin-bottom: 10px; margin-top: 10px; padding-top: 10px;">
					${cb.teacher.user.name}-----${cb.teacher.college}-----${cb.course.courseName}
				</div>
			</c:forEach>
		</div>
		<a href="#" onclick="showAll()" class="btn" >查看课程</a>
		<a href='<c:url value="/admin/teacher/course/all/list?school=all"></c:url>'  class="btn" >查看所有课程</a>
	</div>	
</div>
