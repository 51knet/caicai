<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="container search-bar">
       <table width="100%" class="cont">
       		<tr>
       			<td width="30%"><a href="${url }"><img  src='<c:url value="/resources/img/default/logo.png"></c:url>'></a></td>
       			<td width="80%">
     				  <form class="navbar-form" action="<c:url value="/search"></c:url>" method="post">
				         <input type="text" name="searchParam" class="span6" placeholder="搜索教师、企业、资源"  value="${searchParam }">
				         &nbsp;&nbsp;<button type="submit" class="btn btn-success" style="margin-top: 3px;">搜索</button>
				       </form>
       			</td>
       		</tr>
       </table>
</div>
