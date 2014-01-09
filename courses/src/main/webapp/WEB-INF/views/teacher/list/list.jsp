<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>

</style>
 <div class="path_link"><a href="<c:url value='/'></c:url>" >首页 </a> >> 专家列表 </div>
 <div class="container title"  >
		<div class="innerLeftTitle">热门专家（${fn:length(teacherList)}）</div>
 	</div>
<div class="container teacher">
			<!-- <div class="selete_filter">
			<select >
				<option>全部专家</option>
			</select>
		<span>大学</span><span>中学</span><span>小学</span><span>其他</span>
		</div> -->

  	<div class="bgimg">
	<c:choose>
	<c:when test="${fn:length(teacherList)==0}">
       <div class="teacherInfo">
       	<h3>暂无数据</h3>
       </div>
	</c:when>
	<c:otherwise>
	<div class="teacherInfo">
      	<c:forEach items="${page.content}" var="t">
			<div class="span3" >
				<c:choose>
					<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
						<a href="/courses/teacher/${t.id }"><img src='<c:url value="${url}${t.user.photo_url }"></c:url>' class="photos" /></a>
					</c:when>
					<c:otherwise>
						<a href="/courses/teacher/${t.id }"><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>'  class="photos"/></a>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${t.user.name==null||t.user.name==''}">
						<div id="contentlimite" style="width: 100px;"><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>尚未添加</a></div>
					</c:when>
					<c:otherwise>
						<div id="contentlimite" style="width: 100px;"><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a></div>
					</c:otherwise>
				</c:choose>
		
				<c:choose>
					<c:when test="${t.college==null||t.college==''}">
						<div id="contentlimite" style="width: 100px;">尚未添加</div>
					</c:when>
					<c:otherwise>
						<div id="contentlimite" style="width: 100px;">${t.college}</div>
					</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>
	</div>
	</c:otherwise>
	</c:choose>
	<div  class="teacherInfo" style="float: left; margin-left: 80px;  margin-top: 10px;"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>
