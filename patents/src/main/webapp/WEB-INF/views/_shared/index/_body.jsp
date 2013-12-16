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
		color: #fffdfa;
			line-height: 40px;
		font-weight:bold;
		margin-left: 70px;
		width: 110px;
		float: left;
		
	
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
	 margin: 30px 0px 30px 70px;
	  text-align: left;
	  float: left;
	  vertical-align: top;
	  font-size: 15px;
	 /* font-weight: bold;*/
	}
.container.marketing .row .contentRight {
	 width: 280px; 
	 margin: 30px 0px 30px 30px;
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
		list-style-type: none;
	}
	li{
		line-height:28px;
	}
	 .centerline{
		background-image: url("<c:url value='/resources/img/default/centerline.png'></c:url>");
		background-position: center top;
		background-repeat: repeat-y;
	}
</style>
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
								<a href='<c:url value='/patent/${patentField.fieldName}/list' ></c:url> ' style="text-decoration: none;"><span style="color:#80b029; margin: 0px 5px;">${patentField.fieldName}</span></a>
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
					<c:forEach items="${patentList }" var="patentList" begin="0" step="2" end="16"  >
						<li ><div id="contentlimit" style="width:290px; float: left;">
						<a class="a_color_green"  href="<c:url value="/patent/view?id=${patentList.patentNum }"></c:url>">${patentList.patentName }</a>
						</div></li>
					</c:forEach>
				</ul>
			</div>
			<div  class="span6" >
				<ul>
				<c:forEach items="${patentList }" var="patentList" begin="1" step="2" end="17">
					<li   ><div id="contentlimit" style="width:290px; float: left;">
						<a class="a_color_green"  href="<c:url value="/patent/view?id=${patentList.patentNum }"></c:url>">${patentList.patentName }</a>
					</div></li>
				</c:forEach>
				</ul>
				<br>
				<a style="text-decoration: none;" href="<c:url value="/patent/list"></c:url>" >查看全部>></a>
			</div>
		</div>
		<div class="contentRight">
			<div class="rightTop">
				<span class="title">活动动态</span>
				<a href="<c:url value="/activity/list"></c:url> " style="text-decoration: none;"><span class="more">更多</span></a>
			</div>
			<div class="rigntBottom">
				<c:if test="${activityList == null }">
					无内容
				</c:if>
				<ul>
				<c:forEach items="${activityList }" var="activityList" begin="0"  end="6">
					<li  ><div id="contentlimit" style="width:250px; float: left;">
							<a class="a_color_green"  href="<c:url value="/activity/view/${ activityList.id}"></c:url>">${activityList.title }</a>
					</div></li>
				</c:forEach>
				</ul>
			</div>
		</div>
  </div>
</div>


<div class="container teacher" >
   <div class="container title">
	 <ul id="tab_req">

		        <li class="  current " ><b>专利需求</b></li>
		        <li  ><b>技术需求</b></li>
		</ul>
  </div>
	<div id="content_req">
        <ul style="display:block;" >
      	     	<div class="row-fluid centerline" >
      	     		<div class="span6" style="border: 0px solid;">
					<table  width="100%" cellpadding="3" border="0">
						<thead>
							<tr>
								<th align="left">标题</th><th width="22%" align="left">发布人</th><th width="19%" align="left">发布日期</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${patentRequire }" var="patentRequire"  begin="0" step="2" end="16">
								<tr>
									<td><div id="contentlimit" style="width:250px; float: left;"><a class="a_color_green"  href="<c:url value="/requirement/view/${ patentRequire.id}"></c:url>">${patentRequire.title }</a></div></td>
									<td><div id="contentlimit" style="width:90px; float: left;">${patentRequire.user.name }</div></td>
									<td><fmt:formatDate value="${patentRequire.date}" pattern="yyyy-MM-dd "/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
      	     		</div>
      	     		<div  class="span6"  style="border: 0px solid;">
	     	     		<table  width="100%" cellpadding="3" border="0">
							<thead>
								<tr>
									<th align="left">标题</th><th width="22%" align="left">发布人</th><th width="19%" align="left">发布日期</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${patentRequire }" var="patentRequire"  begin="1" step="2" end="17">
									<tr>
										<td><div id="contentlimit" style="width:250px; float: left;"><a class="a_color_green"  href="<c:url value="/requirement/view/${ patentRequire.id}"></c:url>">${patentRequire.title }</a></div></td>
										<td><div id="contentlimit" style="width:90px; float: left;">${patentRequire.user.name }</div></td>
										<td><fmt:formatDate value="${patentRequire.date}" pattern="yyyy-MM-dd "/></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="3" align="right">
											<a style="text-decoration: none;" href="<c:url value="/requirement/patent/list"></c:url>" >查看全部>></a>
									</td>
								</tr>
							</tbody>
						</table>
					
      	     		</div>
      	     	</div>
        </ul>
        
        <ul  >
           <div class="row-fluid centerline" >
     	     		<div class="span6" style="border: 0px solid;">
						<table  width="100%" cellpadding="3" border="0">
							<thead>
								<tr>
									<th align="left">标题</th><th width="22%" align="left">发布人</th><th width="19%" align="left">发布日期</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${technologyRequire}" var="technologyRequire"  begin="0" step="2" end="16">
									<tr>
										<td><div id="contentlimit" style="width:250px; float: left;"><a class="a_color_green"  href="<c:url value="/requirement/view/${ technologyRequire.id}"></c:url>">${technologyRequire.title }</a></div></td>
										<td><div id="contentlimit" style="width:90px; float: left;">${technologyRequire.user.name }</div></td>
										<td><fmt:formatDate value="${technologyRequire.date}" pattern="yyyy-MM-dd "/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
     	     		</div>
     	     		<div  class="span6"  style="border: 0px solid;">
	    	     		<table  width="100%" cellpadding="3" border="0">
						<thead>
							<tr>
								<th align="left">标题</th><th width="22%" align="left">发布人</th><th width="19%" align="left">发布日期</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${technologyRequire }" var="technologyRequire"  begin="1" step="2" end="17">
								<tr>
									<td><div id="contentlimit" style="width:250px; float: left;"><a class="a_color_green"  href="<c:url value="/requirement/view/${ technologyRequire.id}"></c:url>">${technologyRequire.title }</a></div></td>
									<td><div id="contentlimit" style="width:90px; float: left;">${technologyRequire.user.name }</div></td>
									<td><fmt:formatDate value="${technologyRequire.date}" pattern="yyyy-MM-dd "/></td>
								</tr>
							</c:forEach>
								<tr>
									<td colspan="3" align="right">
											<a style="text-decoration: none;" href="<c:url value="/requirement/technology/list"></c:url>" >查看全部>></a>
									</td>
								</tr>
						</tbody>
						</table>
     	     		</div>
     	     	</div>
        </ul>
	</div>
</div>

<div class="container teacher" >
	  <div class="container title">
		 <table >
		 	<tr>
		 		<td width="16%" align="center"><h4 style="color: #fff;">热门教师 </h4></td>
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
			<c:forEach items="${teacherLists}" var="t" begin="0" end="6">
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