<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">
	function requestCourseDetail(cid,eid){
		$("#course_id").val(cid);
		$("#enterprise_id").val(eid);
		$("#showCourseDetail").submit();
	}
</script>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.8.0.js" />"></script>
	<script type="text/javascript">
		var n = 0;
		$(document).ready(function(){
			count=$(".cont a").length;
			$(".item a").click(function(){
				$(this).addClass("seld").siblings().removeClass("seld");	
				var _index=$(this).index();
				$(".cont>a").eq(_index).fadeIn(300).siblings().fadeOut(300);
			});
			t = setInterval("showAuto()", 2000);
			$(".box").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 2000);});
		})
		function showAuto()
		{
			n = n >=(count - 1)?0: ++n;
			$(".item a").eq(n).trigger('click');
		}	
	</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	/*background: #FAFAFB;*/

}
.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #f77605 1.5px;
	padding-bottom: 4px;
	margin: 0px 0px 0px 0px;
	padding:0px 10px 5px 10px;
}
.row-fluid.custom .row > h4 >span {
	font-size: 14px;
	color:#666;
}

.row-fluid.custom .row {
	margin: 0px 10px 0px 10px;
}

.row-fluid.custom .row .bb{
	border-bottom: dashed  1px;
}
.row-fluid.custom .row .course{
	margin-top:10px;
	margin-left:10px;
	margin-bottom:10px;
	border:1px dotted #dadada;
	width: 230px;
	padding:5px;
	float: left;
}
.row-fluid.custom .row .teacher{
	margin-top:10px;
	margin-left:13px;
	margin-bottom:10px;
	border:1px dotted #dadada;
	width: 100px;
	padding:5px;
	float: left;
}

.box{position:relative;width:440px;margin:0px auto;}
.cont{height:220px;overflow:hidden; width:440px;}
.item{background:rgba(0,0,0,0.5);color:#fff;font-size:80px;position:absolute;left:0;bottom:0;width:100%;text-align:right;line-height:40px;height:40px;}
.item>a{margin-right:10px;cursor:pointer;text-shadow:0 0 3px rgba(0,0,0,0.8); text-decoration:  none;}
.item>a.seld{color:#06C;}
.item>a.hide{display:none;}
</style>

<!-- enterprise  announcement -->

<div class="row-fluid custom round">
	<!-- <div class="row"><h4>公告 <span class="pull-right" ><a href='<c:url value='/enterprise/${teacherInfo.id}/announcement/list'></c:url>'>更多</a></span> </h4></div> -->
	<div class="row">
		<!-- 
		<c:choose>
			<c:when test="${annoCount>0}">
				<table cellpadding="4" width="100%" style="margin-top: 10px;">
					<tbody>
						<tr>
							<td width="40%"  align="left" valign="top" style="background-color:#59abda; height:220px;">
								<c:forEach var="annophoto" items="${annoPhoto}" begin="0" end="1">
									<a href="<c:url value="/enterprise/${teacherInfo.id}/announcement/view/${annophoto.id}"></c:url>"><img src='<c:url value="${annophoto.photourl}" ></c:url>'  /></a>
								</c:forEach>
							</td>
							<td width="60%" align="left" valign="top">
								<table cellpadding="3" width="100%" >
									<c:forEach var="anno" items="${annolist}" begin="0" end="2">
										<tr  class="bb">
											<td    align="left" valign="top">
												<div style="width: 280px" id="content"><a href="<c:url value="/enterprise/${teacherInfo.id}/announcement/view/${anno.id}"></c:url>">${anno.title}</a></div>
											</td>
											<td  align="left" valign="top">
												${anno.date}
											</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
			<br>
				无内容
			</c:otherwise>
		</c:choose> -->
			<c:choose>
				<c:when test="${annoCount>0}">
					<table cellpadding="4" width="100%" >
						<tbody>
							<tr>
								<td   align="left" valign="top" style="background-color:#59abda; height:220px; width: 440px;">
									<!--<c:forEach var="annophoto" items="${annoPhoto}" begin="0" end="0">
										<a href="<c:url value="/enterprise/${teacherInfo.id}/announcement/view/${annophoto.id}"></c:url>"><img src='<c:url value="${annophoto.photourl}" ></c:url>'  /></a>
									</c:forEach>-->
										<div class="box">
									    	<div class="cont">
										    	<c:forEach var="annophoto" items="${annoPhoto}" begin="0" end="2">
										            <a ><img src="<c:url value="${annophoto.photourl}"></c:url>"></img></a>
										        </c:forEach>
									          <!--  <a class="hide"><img src="<c:url value='/resources/img/ayu.png'></c:url>"></img></a>
									          <a class="hide"><img src="<c:url value='/resources/img/ayu.png'></c:url>"></img></a> --> 
									        </div>
									        <div class="item">
									       		<c:forEach var="annophoto" items="${annoPhoto}" begin="0" end="2">
									            	<a class="seld">·</a>
									           </c:forEach>
									        	<!-- <a class="seld">·</a>
									            <a>·</a>
									            <a>·</a> -->
									        </div>
									    </div>
								</td>
								<td width="2%">&nbsp;</td>
								<td width="40%" align="left" valign="top" style="background-color:#a7c676; height:220px; color: #fff;">
									<table cellpadding="7" width="100%" >
										<c:forEach var="anno" items="${annolist}" begin="0" end="2">
											<tr  class="bb">
												<td align="left" valign="top">
													<div style="width: 140px" id="content"><a href="<c:url value="/enterprise/${teacherInfo.id}/announcement/view/${anno.id}"></c:url>">${anno.title}</a></div>
												</td>
												<td align="left" valign="top">
													${anno.date}
												</td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<table cellpadding="4" width="100%" >
						<tbody>
							<tr>
								<td   align="left" valign="top" style="background-color:#59abda; height:220px; width: 440px; color: #fff;">
										<table cellpadding="7" width="100%" >
											<tr  >
												<td align="left" valign="top">
													尚未添加内容
												</td>
											</tr>
									</table>
								</td>
								<td width="2%">&nbsp;</td>
								<td width="40%" align="left" valign="top" style="background-color:#a7c676; height:220px; color: #fff;">
									<table cellpadding="7" width="100%" >
											<tr  >
												<td align="left" valign="top">
													尚未添加内容
												</td>
											</tr>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
</div>

 
<!-- enterprise's teacher -->
<div class="row-fluid custom round">
	<div class="row"><h4>知名教师<span class="pull-right" ><a href='<c:url value='/enterprise/${teacherInfo.id}/teacher/list'></c:url>'>更多</a></span> </h4></div>
	<div class="row" style="border: solid 1px #f77605;" >
		<c:choose>
			<c:when test="${eTeacherCount !=0}">
				<c:forEach items="${eTeacher }" var="et" begin="0" end="5">
					<div class="teacher">
						<div><a href='<c:url value="/enterprise/${teacherInfo.id}/teacher/view/${et.id}"></c:url>' ><img src='<c:url value="${et.photourl}" ></c:url>'  ></a></div>
						<div>${et.content }</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<br>
				无内容<br><br>
			</c:otherwise>
		</c:choose>
	</div>
</div>


<!-- enterprisecourse -->
<div class="row-fluid custom round">
		<div class="row">
			<h4>学习资源<span class="pull-right" ><a href='<c:url value='/enterprise/${teacherInfo.id}/course/list'></c:url>'>更多</a></span> </h4>
		</div>
		<div class="row"  style="border: solid 1px #f77605;">
			<c:choose>
				<c:when test="${courseCount !=0}">
					<c:forEach items="${courseList}" var="course" begin="0" end="5">
				    	<div class="course">
							<div style="width: 230px; height: 155px; background-image: url('<c:url value="${course.courseCover }"></c:url>');  
									background-repeat:no-repeat;background-position:center;  ">
								<a href="javascript:void(0)"  onclick="requestCourseDetail( ${course.id} , ${teacherInfo.id})"> 	<div style="height: 125px;"></div></a>
			    				<div  style="height:24px;background-color:#000;  padding:3px; color: #fff;  Opacity:0.70; Filter:alpha(opacity=70);">
			    					<div  id="contentlimit" style="width: 240px;">
			    							${course.courseName }
			    					</div>
			   				 	</div>
							</div>
				    	</div>
				    </c:forEach>
				</c:when>
				<c:otherwise>
				<br>
					无内容<br>
				</c:otherwise>
			</c:choose>
		</div>
		<form action="<c:url value="/enterprise/course/view"></c:url>" id="showCourseDetail" method="post">
			<input type="hidden"  name="enterpriseId" id="enterprise_id" >
			<input type="hidden"  name="courseId" id="course_id">
			<input type="hidden"  name="coursepwd" id="course_pwd">
		</form>
</div>
