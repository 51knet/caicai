<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<h5></h5>
</div><br/>
<div>
<div align="center">
<table  style="width:50%;">
  <tr>
    <td colspan="4"  align="left" bordercolor="#F0F0F0" bgcolor="#CCCCCC"><h4><span class="STYLE1"> 讲师介绍</span></h4>
    </td>
  </tr>
  <tr>
    <td rowspan="3" valign="bottom">
    <c:choose>
		<c:when test="${teacher.user.photo_url != null && teacher.user.photo_url != ''}">
			<img src='<c:url value="${teacher.user.photo_url }"></c:url>'/>
		</c:when>
		<c:otherwise>
			 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>'/>
		</c:otherwise>
	</c:choose>
	</td>
    <td>姓名:${teacher.user.name }</td>
    <td>性别:${teacher.user.gender }</td>
    <td>所属高校:${teacher.college }</td>
  </tr>
  <tr>
    <td>所属高校:${teacher.college }</td>
    <td>所属院系:${teacher.school}</td>
    <td>教授课程:${teacher.major}</td>
  </tr>
  <tr>
    <td>职称:${teacher.title}</td>
    <td>导师类别:${teacher.role}</td>
  </tr>
</table>
</div>
</div>