<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	
</script>
<style>
body {
	background-color: #edf1e0;
	margin: 0 auto;
}
.container{
	margin: 40px 0px 20px 0px;
}
.container .content{
	margin: 50px 10px 0px 10px;
	background-color: #FFF;
	padding:20px 20px 20px 20px;
	border: 1px solid #e28f03;
	border-bottom: 2px solid #e28f03;
}
.container .content >table{
	width:100%;

}
.container .content >table .content{
	font-size: 15px;
	color: #929292;
}
.container .content >table >thead >tr >th >img{
	width: 100px; height: 100px; float: left;
}
.container .title{
	margin: 30px 30px 0px 30px;
	border-bottom: 1px solid #e28f03;
}

.container .sub{
	margin: 30px 30px 0px 30px;
}

</style>
<div class="container">
	<img src='<c:url value="/resources/img/pay/pay2.png"></c:url>' /><br>
	<div class="content">
		<table cellpadding="10" >
			<thead>
				<tr >
					<th  width="20%"><img src="<c:url value='${seller.photo_url }'></c:url>" style="float: right;"  /> </th>
					<th align="left" valign="bottom" width="10%" style="font-size: 17px; color: #e28f03"> ${seller.name }</th>
					<th  align="left" valign="bottom" width="60%"  style="font-size: 22px;" >课程名称：${course.courseName }</th>
					<th  align="center" valign="bottom"  >&nbsp; </th>
					<th align="center" valign="bottom" width="10%"><span style="font-size: 17px; color: #e28f03">${course.price }</span> 元</th>
				</tr>
			</thead>
		</table>
	</div>
	<div class="title">
			输入登录密码进行验证
	</div>
	<div class="sub">
		<c:if test="${ !paySuccessful }">
			<form class="form-inline"  method="post">
				<input type=text id="password" name="password" /> 
				<input type="submit" class="btn btn-success" value="提交" />
			</form>
		</c:if>
		<c:if test="${paySuccessful }">
			<a href='<c:url value="/admin/mycourse/view/${course.id }"></c:url>' > 购买成功，点击开始学习</a>
		</c:if>
	</div>

</div>