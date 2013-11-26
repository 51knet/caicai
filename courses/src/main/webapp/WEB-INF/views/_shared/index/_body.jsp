<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
!function ($) {
  $(function(){
    // carousel demo
    $('#myCarousel').carousel();
    $('#myUniversity').carousel();
    $('#myTeacher').carousel();
  });
}(window.jQuery);
</script>
<style>
	.container.teacher .bgimg {
		background-position: left top; 
		background-repeat: repeat;
		background-image: url('<c:url value="/resources/img/default/index/white_bg.png"></c:url>');
	}
	
	.container .title .leftTitle{
		font-size:20px;
		font-weight:bold;
		margin-left: 70px;
		width: 110px;
		float: left;
		color: #fffdfa;
		line-height: 40px;
	}
	.container .title .rightTitle{
		margin-left: 0px;
		width: 90px;
		float: left;
		background-color: #5f7e20;
		text-align: center;
		color: #fffdfa;
		font-size: 15px;
		line-height: 40px;
		font-weight: bold;
	}
	
.container.marketing .row .contentLeft {
	 width: 600px; 
	 margin: 20px 0px 20px 70px;
	  text-align: left;
	  float: left;
	  vertical-align: top;
	  font-size: 15px;
	  font-weight: bold;
	}
.container.marketing .row .contentRight {
	 width: 280px; 
	 margin: 20px 0px 20px 30px;
	  text-align: left;
	  float: left;
	  vertical-align: top;
	  height: 290px;
	  background-color: #fff;
	  border: 1px solid #ccc;
	}
	.rightTop{
		width: 100%;
		height: 38px;
		background-image: url("<c:url value='/resources/img/default/activity_bg.png'></c:url>");
		background-repeat: repeat-x;
		line-height: 34px;
		color: #fff;
	}
	
	.rightTop .title{
		margin-left: 20px; font-size: 17px; font-weight: bold;
	}
	.rightTop .more{
		margin-left: 140px;  font-weight: bold;color: #fff;
	}
	.rigntBottom{
		height: 250px;
		line-height: 20px;
		padding: 5px 20px;
	}
	
	ul{
		list-style-type: circle;
	}
	
	a{
		text-decoration: none;
	}

</style>
<jsp:include page="/WEB-INF/views/_shared/index/_user_course.jsp"></jsp:include>
  	
<div class="container marketing">
  <div class="container title row-fluid">
		<div class="leftTitle" >热门资源</div>
		<div class="rightTitle dropdown" >
			<div class="dropdown-toggle"  data-toggle="dropdown"><a href="#" style="text-decoration: none; color: #fff;">专利展示</a></div>
			<div class="dropdown-menu" style="text-align: left; width: 450px; margin-left: -50px;" role="menu" aria-labelledby="dropdownMenu">
					<table style="width:100%" cellpadding="0" >
						<c:forEach items="${patentFieldList }" var="patentField"  varStatus="status">
							<c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0 }">
								<tr >
							</c:if>
							<td>
								<a href='#' style="text-decoration: none;"><span style="color:#80b029; margin: 0px 5px;">${patentField.fieldName}</span></a>
							</td>
							<c:if test="${status.count % 5 eq 0 || status.count eq 5}">
								</tr>
							</c:if>
						</c:forEach>
					</table>
			</div>
		</div>
  </div>
  <div class="row" >
		<div class="contentLeft row-fluid" >
			<div class="span6" >
				<ul>
					<c:forEach items="${patentList }" var="patentList" begin="0" step="2"  >
						<li >${patentList.patentName }</li>
					</c:forEach>
				</ul>
			</div>
			<div  class="span6" >
				<ul>
				<c:forEach items="${patentList }" var="patentList" begin="1" step="2">
					<li >${patentList.patentName }</li>
				</c:forEach>
				</ul>
				<br>
				<a style="text-decoration: none;" href="<c:url value="/patent/list"></c:url>" >查看全部>></a>
			</div>
		</div>
		<div class="contentRight">
			<div class="rightTop">
				<span class="title">活动动态</span>
				<a href="#" style="text-decoration: none;"><span class="more">更多</span></a>
			</div>
			<div class="rigntBottom">
				暂无活动
			</div>
		</div>
  </div>
</div>
<div class="container teacher" >
	  <div class="container title">
		 <table >
		 	<tr>
		 		<td width="16%" align="center"><h4>热门教师 </h4></td>
		 		<td align="right"><span class="count">共${fn:length(teacherLists)}名教师</span>
		 		 <a  href='<c:url value="/teacher/list"></c:url>'  >全部教师</a></td>
		 	</tr>
		 </table>
	 </div>
  	<div class="bgimg">
	    <c:if test="${fn:length(teacherLists)==0}">
		       <div class="teacherInfo">
		       	<h3>暂无教师数据</h3>
		       </div>
	 	 </c:if>
	      <div class="teacherInfo">
			<c:forEach items="${teacherLists}" var="t" begin="0" end="13">
				<div class="span2">
					<c:choose>
						<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
							<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="${url}${t.user.photo_url }"></c:url>' style="width: 90px; height:90px;" /></a>
						</c:when>
						<c:otherwise>
							<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 90px; height:90px;" /></a>
						</c:otherwise>
					</c:choose>
					<div style="margin-top: 3px; "> 
						<c:choose>
						<c:when test="${t.user.name==null||t.user.name==''}">
						<span id="contentlimit" style="width:90px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>尚未添加</a></span>
						</c:when>
						<c:otherwise>
						<span id="contentlimit" style="width:90px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a></span> 
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<div class="container teacher" >
   <div class="container title">
 	 <table width="100%">
 	 	<tr>
 	 		<td width="16%" align="center"><h4>热门企业 </h4></td>
 	 		<td align="right">
 	 			<span class="count">共${fn:length(enterPriseList)}个企业</span>
 	 		<a  href='<c:url value="/enterprise/list"></c:url>'   >全部企业</a></td>
 	 	</tr>
 	 </table>
  </div>
	  <div class="bgimg">
		  <c:if test="${fn:length(enterPriseList)==0}">
		      <div class="teacherInfo">
		     	 <h3>暂无企业数据</h3>	
		      </div>
		  </c:if>
	      <div class="teacherInfo">
			<c:forEach items="${enterPriseList}" var="enter" begin="0" end="9">
				<div class="span1" >
					<c:choose>
					<c:when test="${enter.user.photo_url!=null||enter.user.photo_url!=''}">
						<a href='<c:url value="${url }/enterprise/${enter.id}"></c:url>'><img src='<c:url value="${url }${enter.user.photo_url }"></c:url>' style="width: 127px; height:83px;" /></a> 
					</c:when>
					<c:otherwise>
						<a href='<c:url value="${url }/enterprise/${enter.id}"></c:url>'><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 127px; height:83px;" /></a> 
					</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</div>
	</div>
</div>