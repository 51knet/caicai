<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
 
<div class="container search-bar">
       <form class="navbar-form" action="<c:url value="/search"></c:url>" method="post">
         <input type="text" name="searchParam" class="span6" placeholder="搜索教师、企业、课程、" value="${searchParam }">
         <button type="submit" class="btn btn-success">搜索</button>
       </form>
</div>
