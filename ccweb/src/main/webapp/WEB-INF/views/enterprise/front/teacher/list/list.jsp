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
<div class="row-fluid custom round">
	<div class="row">
			<c:choose>
				<c:when test="${annoCount>0}">
					<table cellpadding="4" width="100%" >
						<tbody>
							<tr>
								<td   align="left" valign="top" style="background-color:#59abda; height:220px; width: 440px;">
									<!--<c:forEach var="annophoto" items="${annoPhoto}" begin="0" end="0">
										<a href="<c:url value="/enterprise/${userInfo.id}/announcement/view/${annophoto.id}"></c:url>"><img src='<c:url value="${annophoto.photourl}" ></c:url>'  /></a>
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
													<div style="width: 140px" id="content"><a href="<c:url value="/enterprise/${userInfo.id}/announcement/view/${anno.id}"></c:url>">${anno.title}</a></div>
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
<div class="row-fluid custom round">
	<div class="row"><h4>知名教师 </h4></div>
	<div class="row" style="border: solid 1px #f77605;" >
		<c:choose>
			<c:when test="${eTeacherCount > 0}">
				<c:forEach items="${page.content }" var="et">
					<div class="teacher">
						<div><a href='<c:url value="/enterprise/${userInfo.id}/teacher/view/${et.id}"></c:url>' ><img src='<c:url value="${et.photourl}" ></c:url>'  ></a></div>
						<div>${et.content }</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<div style="padding:10px;">暂无内容</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="margin: 10px 0px 0px 10px;"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
</div>
<!-- 
nothing....
<c:forEach var="x" begin="1" end="9" varStatus="xstatus"> 
	<c:forEach var="y" begin="1" end="${x }" varStatus="ystatus">
		${x } * ${y } = ${x*y } ；
		<c:if test="${ystatus.count ==x }"><br /></c:if>
	</c:forEach>
</c:forEach> 
 -->