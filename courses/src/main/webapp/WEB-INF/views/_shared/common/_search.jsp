<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
 <style>

 </style>
<div class="container search-bar">
       <table width="100%" class="cont">
       		<tr>
       			<td width="30%"><img  src='<c:url value="/resources/img/default/logo.png"></c:url>'></td>
       			<td width="80%">
     				  <form class="navbar-form" action="<c:url value="/search"></c:url>" method="post">
				         <input type="text" name="searchParam" class="span6" placeholder="搜索教师、企业、课程、" value="${searchParam }">
				         &nbsp;&nbsp;<button type="submit" class="btn btn-success" style="margin-top: 3px;">搜索</button>
				       </form>
       			</td>
       		</tr>
       </table>
</div>
