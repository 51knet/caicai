<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="comment">
	<div  align="left" style="background-color:#F7F7F7;height:40px; line-height:40px;clear:both"><h4 style=" float:left; margin-left: 40px;">用户评价</h4></div> 
	<span style="line-height:40px">总评论人数:&nbsp;&nbsp;&nbsp;${sumPerson}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;评论分数:&nbsp;&nbsp;&nbsp;${mark}&nbsp;&nbsp;&nbsp;</span><br/>
		<c:forEach var="comment" items="${listcomment}">
			<span style="line-height:40px;">昵称:&nbsp;&nbsp;&nbsp;${comment.userName}&nbsp;&nbsp;&nbsp;本人评分数:&nbsp;&nbsp;&nbsp;${comment.comment.mark}</span><br/>
			<span style="line-height:40px;">标题:&nbsp;&nbsp;&nbsp;${comment.comment.commentTitle}&nbsp;&nbsp;&nbsp;评论时间:&nbsp;&nbsp;&nbsp;${comment.comment.commentDate }</span><br/>					
			<span style="line-height:40px;">评论信息:&nbsp;&nbsp;&nbsp;${comment.comment.commentDesc}</span><br/>
		</c:forEach>
</div>
