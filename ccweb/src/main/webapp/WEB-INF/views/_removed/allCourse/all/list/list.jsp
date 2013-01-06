<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
function selectSchool(){
	var sel = document.getElementById("school");
	var opt = sel.options;
	for(var i=0;i<opt.length;i++){
		if(opt[i].selected){
			var schoolName = opt[i].innerHTML;
			window.location.href='<c:url value="/admin/teacher/course/all/list?school='+schoolName+'"></c:url>';
		//	$.ajax({
		//		  type: "post",
		//		  url: "<c:url value='/admin/teacher/course/all/list' />",
		//		  data: "school="+schoolName,
		//		  dataType:"text",
		//		  success:function(){
						//alert("success!!");
		//		  }
		//	});
		}
	}
}
</script>


<div class="row-fluid custom round" style="text-align: center;">
	<div  class="row" style="margin-top: 10px; text-align: left;">
		<select name="" id="school" onchange="selectSchool()" style="width:210px; margin-left: 10px;">
			<option>请选择</option>
			<c:forEach items="${school}"  var="s">
				<c:choose>
					<c:when test="${schoolName == s}">
						<option selected>${s}</option>
					</c:when>
					<c:otherwise>
						<option value="">${s}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<c:choose>
			<c:when test="${teacherList != null }">
				<select  id="teacher" onchange="selectTeaccher()" style="width:210px; margin-left: 10px;">
					<option>请选择</option>
					<c:forEach items="${teacherList}"  var="t">
						<option value="">${t.user.name}</option>
					</c:forEach>
				</select>
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
		
		
		<div style="text-align: center; width:230px;" id="big">
			<c:forEach items="${cb}"  var="cb">
				<div id="${cb.teacher.college}" style="float: left;border: 1px solid #cccccc; width:210px; height: 80px;  margin-left: 10px; margin-bottom: 10px; margin-top: 10px; padding-top: 10px;">
					${cb.teacher.user.name}-----${cb.teacher.college}-----${cb.course.courseName}
				</div>
			</c:forEach>
		</div>
	</div>	
</div>
