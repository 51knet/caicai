<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	
</script>
<style>
	.ctype{
		background: #fff5e0;
		text-align: left;
	}
	.ctype .content{
		padding:10px 20px; 
		border-bottom: 1.5px solid #f77605;
		border-left: 1.5px solid #f77605;
		border-right: 1.5px solid #f77605;
	}
</style>

<div class="ctype">
	<div class="content">
		<c:forEach items="${cTypeList }" var="cType" begin="0" end="2">
			<a href='<c:url value='/enterprise/${teacher_id}/course/type/${cType.id}'></c:url>'><span style="color:#80b029; margin-right: 5px;">${cType.typeName}</span></a>
		</c:forEach>
	</div>	
		<div class="content">
		<c:forEach items="${cTypeList }" var="cType" begin="3" end="5">
			<a href='<c:url value='/enterprise/${teacher_id}/course/type/${cType.id}'></c:url>'><span style="color:#80b029; margin-right: 5px;">${cType.typeName}</span></a>
		</c:forEach>
	</div>	
		<div class="content">
		<c:forEach items="${cTypeList }" var="cType" begin="6" end="8">
			<a href='<c:url value='/enterprise/${teacher_id}/course/type/${cType.id}'></c:url>'><span style="color:#80b029; margin-right: 5px;">${cType.typeName}</span></a>
		</c:forEach>
	</div>	
	<div class="content">
		<c:forEach items="${cTypeList }" var="cType" begin="9" end="12">
			<a href='<c:url value='/enterprise/${teacher_id}/course/type/${cType.id}'></c:url>'><span style="color:#80b029; margin-right: 5px;">${cType.typeName}</span></a>
		</c:forEach>
	</div>	
	<div class="content">
		<c:forEach items="${cTypeList }" var="cType" begin="13" end="16">
			<a href='<c:url value='/enterprise/${teacher_id}/course/type/${cType.id}'></c:url>'><span style="color:#80b029; margin-right: 5px;">${cType.typeName}</span></a>
		</c:forEach>
	</div>	
	<div class="content">
		<c:forEach items="${cTypeList }" var="cType" begin="17" end="20">
			<a href='<c:url value='/enterprise/${teacher_id}/course/type/${cType.id}'></c:url>'><span style="color:#80b029; margin-right: 5px;">${cType.typeName}</span></a>
		</c:forEach>
	</div>	
</div>