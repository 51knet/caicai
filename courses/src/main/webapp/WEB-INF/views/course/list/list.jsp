<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function selectType() {
		var sel = document.getElementById("type");
		var opt = sel.options;
		for ( var i = 0; i < opt.length; i++) {
			if (opt[i].selected) {
				var typeName = opt[i].innerHTML;
				//alert(typeName);
				window.location.href = '<c:url value="/course/list/type?detail='+ typeName + '"></c:url>';
			}
		}
	}
</script>

<div class="selete_filter">
	<select id="type" onchange="selectType()">
		<option>全部课程</option>
		<c:forEach items="${courseTypeList}" var="ct">
			<c:choose>
				<c:when test="${courseType == ct.typeName}">
					<option  selected>${ct.typeName}</option>
				</c:when>
				<c:otherwise>
					<option value="">${ct.typeName}</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<!-- <span>大学</span><span>中学</span><span>小学</span><span>其他</span> -->
</div>
<div class="container title"  >
	 	 <table >
	 	 	<tr>
	 	 		<td width="20%" align="center"><h4>所有课程（${courseCount}）</h4></td>
	 	 		<td></td>
	 	 	</tr>
	 	 </table>
 </div>
 <div class="container user-course">
 <c:if test="${courseCount <=0}">
  <br>
 	<h4 style="margin-left: 70px;">未搜索到课程</h4>
 </c:if>
 <br>
 	<c:forEach items="${courseList}" var="course">
			<table cellpadding="20" style="width: 100%; height: 100%;  margin-bottom: 10px; "  >
				<tr class="bb">
				<td  width="24%" align="right"  valign="top">
						<c:choose>
							<c:when test="${course.courseCover != null && course.courseCover != ''}">
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="${url }${course.courseCover }"></c:url>' style="width: 162px; height: 120px;float: right;" />
								</a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 162px; height: 120px; float: right;" />
								</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td valign="top">
						<div style="width:370px; margin-top: 10px;" id="contentlimit">
						<b style="font-size: 17px;">课程名称：${course.courseName}</b><br><br>课程类别：${course.cType.typeName }<br>课程描述：${course.courseDesc }<br>发布时间：${course.courseDate }</div>
					</td>
					<td width="38%" align="left"  valign="top">
						<div style="width: 100%;height: 100%;">
							<div style="float: left; height: 100px; width: 120px;">
								<c:choose>
									<c:when test="${course.teacher.user.photo_url != null && course.teacher.user.photo_url != ''}">
										<a href='<c:url value="/teacher/${course.teacher.id}"></c:url>'><img src='<c:url value="${url }${course.teacher.user.photo_url }"></c:url>' style="width: 100px; height: 100px;" />
										</a>
									</c:when>
									<c:otherwise>
										<a href='<c:url value="/teacher/${course.teacher.id}"></c:url>'> <img src='<c:url value="/resources/img/avatar/avatar90.png"></c:url>' style="width: 100px; height: 100px;" />
										</a>
									</c:otherwise>
								</c:choose>
							</div>
							<c:choose>
							<c:when test="${course.teacher.isEnterprise == null}">
							<div style="float: left; height: 100px;width: 200px;"id="contentlimit" >教师名称：${course.teacher.user.name } <br>所在学校：${course.teacher.college }<br>专业技术：${course.teacher.major }<br>
								<a style="margin-top: 5px;" href='<c:url value="/teacher/${course.teacher.id}"></c:url>' class="btn  btn-success">查看发布的课程</a>
							</div>
							</c:when>
							<c:otherwise>
							<div style="float: left; height: 100px; width: 200px;" id="contentlimit">企业名称：${course.teacher.user.name } <br><br>
								<a style="margin-top: 5px;" href='<c:url value="/teacher/${course.teacher.id}"></c:url>' class="btn  btn-success">查看发布的课程</a>
							</div>
							</c:otherwise>
							</c:choose>
						</div>
					</td>
				</tr>
			</table>
	</c:forEach>
 </div>



