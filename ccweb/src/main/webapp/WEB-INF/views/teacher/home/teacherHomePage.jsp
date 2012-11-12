<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="text-align: center;">
	Welcome to teacher home page.<br>
	${sessionUserInfo.user.email }<br>
	${sessionUserInfo.user.id }<br>
	${sessionUserInfo.user.name}<br>
	<!--<a href='<c:url value="/admin/teacher/announcement/add"></c:url>'>添加公告</a><br>
	<table width="500" border="1" cellspacing="0" cellpadding="5">
		<tr><td>公告标题</td><td>发布时间<td>详细操作</td></tr>
		<c:forEach items="${list}" var="l">
			<tr><td align="left">${l.title}</td><td align="center">${l.date}</td><td><a href='<c:url value="/admin/teacher/announcement/detailOne?id=${l.id}"></c:url>'>修改</a> | <a href='<c:url value="/admin/teacher/announcement/deleAnno?id=${l.id}"></c:url>'> 删除</a></td></tr>
		</c:forEach>
	</table>
	<br/>
	-->

	<img src="<c:url value='/resources/img/teacher_home.jpg' ></c:url>" />
</div>