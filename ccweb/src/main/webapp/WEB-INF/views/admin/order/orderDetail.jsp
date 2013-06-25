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
}
.container .content >table{
	width: 100%;
}
.container .content >table .content{
	font-size: 15px;
	color: #929292;
}
.container .content >table >thead >tr >th .seller{
	font-size: 17px;
	color: #000;
	float: left;
	margin:80px 0px 0px 10px;
}
.container .content >table >thead >tr >th{
	background-image: url('<c:url value="/resources/img/pay/payline.png"></c:url>');
	background-position: bottom center;
	background-repeat: no-repeat;
}
.container .content >table >thead >tr >th >img{
	width: 100px; height: 100px; float: left;
}
.container .content >table >tbody .bline{
	border-bottom: 2px solid #f3dbb5;
	font-size: 16px;
}
.container .sub{
	float: right;
	margin: 10px 0px 0px 0px;
}
</style>
<div class="container">
	<div class="content">
		<table cellpadding="10" >
			<thead>
				<tr >
					<th align="left" valign="bottom" width="25%"><img src="<c:url value='${seller.photo_url }'></c:url>"  /> <div class="seller">${seller.name }</div></th>
					<th class="content"  align="center" valign="bottom" width="25%">课程名称</th>
					<th class="content" align="center" valign="bottom"  width="25%">课程类别</th>
					<th class="content" align="center" valign="bottom">价格</th>
				</tr>
			</thead>
			<tbody>
				<tr class="bline">
					<td valign="top">	<img src="<c:url value='${course.courseCover }'></c:url>" style="width: 150px; height: 100px;" /></td>
					<td valign="top">${course.courseName }</td>
					<td valign="top">${ course.cType.typeName}</td>
					<td valign="top">${course.price }</td>
				</tr>
			</tbody>
		</table>
		<br><br>
		<table cellpadding="10" >
			<thead>
				<tr >
					<th align="left" valign="bottom" width="25%">订单编号</th>
					<th class="content"  align="center" valign="bottom" width="25%">开始时间</th>
					<th class="content" align="center" valign="bottom"  width="25%">结束时间</th>
					<th class="content" align="center" valign="bottom">订单状态</th>
				</tr>
			</thead>
			<tbody>
				<tr class="bline">
					<td valign="top">${order.id }</td>
					<td valign="top">${order.startTime }</td>
					<td valign="top">${ order.endTime}</td>
					<td valign="top">${order.status}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="sub">
		<form  method="get" action='<c:url value="/" ></c:url>'>
			<input type="submit" class="btn btn-success"  value="返回后台"/>
		</form>
	</div>
</div>



