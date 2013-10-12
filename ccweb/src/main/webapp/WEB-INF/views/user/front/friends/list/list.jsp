<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">

</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;

}

.row-fluid .custom .row {
	margin: 0px 30px 10px 30px;
	color: #80b029;
	/*border-bottom: solid #cccccc 1.5px;*/
}
.row-fluid .custom .row .top_left{
	width:15%;
	float: left;
	height: 18px;
}
.row-fluid .custom .row .top_left >span{
	font-size: 15px;
	color:  #80b029;
}
.row-fluid .custom .row .top_right{
	width:85%;
	float: right;
	background-image: url("<c:url value='/resources/img/default/greenline.png'></c:url>");
	background-position: left center;
	background-repeat: repeat-x;
	height: 18px;
}
.row-fluid.custom .content {
	margin: 20px 30px;
}
.border_green{
	border: 1px solid #80b029;
}

.border-ccc-all{
	border: 1px solid #ccc;
}
.color_green{
	color: #859c34;
}


</style>
<div class="row-fluid custom" >
	<div class="row">
		<div class="top_left "><span>我的粉丝</span></div>
		<div class="top_right "></div>
	</div>
	<div class="row">
		<c:forEach items="${fansList}" var="fans">
			<div style="float: left; padding: 2px 0px;">
				 <table cellpadding="0" width="100">
				 	<tr><td align="center"><a href='<c:url value="/id/${fans.id}"></c:url>' ><img class="border_ccc" src='<c:url value="${fans.photo_url}"></c:url>' style="width: 80px;" /></a></td></tr>
				 	<tr><td align="center"><div  style="width: 90px;" id="content">${fans.name}</div></td></tr>
				 </table>
			</div>
		</c:forEach>
	</div>
	<br>
	<div class="row">
		<div class="top_left "><span>我关注的人</span></div>
		<div class="top_right "></div>
	</div>
	<div class="row">
		<c:forEach items="${hostList}" var="host">
			<div style="float: left; padding: 2px 0px;">
				 <table cellpadding="0" width="100">
				 	<tr><td align="center"><a href='<c:url value="/id/${host.id}"></c:url>' ><img class="border_ccc" src='<c:url value="${host.photo_url}"></c:url>' style="width: 80px;" /></a></td></tr>
				 	<tr><td align="center"><div  style="width: 90px;" id="content">${host.name}</div></td></tr>
				 </table>
			</div>
		</c:forEach>
	</div>
</div>

