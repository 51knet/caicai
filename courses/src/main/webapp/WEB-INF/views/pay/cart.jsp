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
}
.container{
	padding: 70px 0px 0px 0px;
	background-color: #edf1e0;
}
.container .content{
	margin: 50px 10px 0px 10px;
}
.container .content >table{
	width: 950px;
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
	margin: 10px 0px 0px 0px;
}
.myblock{
	padding: 5px 8px; background-color: #6597c8; 
	color: #fff; font-size: 14px; font-weight: bold;
}
</style>
<div class="container" >
	<img src='<c:url value="/resources/img/pay/pay1.png"></c:url>' /><br>
	<div class="content">
		<table cellpadding="10" >
			<thead>
				<tr >
					<th align="left" valign="bottom" width="25%"><img src="<c:url value='${p_url }${seller.photo_url }'></c:url>"  /> <div class="seller">${seller.name }</div></th>
					<th class="content"  align="center" valign="bottom" width="25%">项目名称</th>
					<th class="content" align="center" valign="bottom"  width="25%">所属行业</th>
					<th class="content" align="center" valign="bottom">当前融资率</th>
				</tr>
			</thead>
			<tbody>
				<tr class="bline">
					<td valign="top">	<img src="<c:url value='${p_url  }${projects.logoPath }'></c:url>" style="width: 150px; height: 100px;" /></td>
					<td valign="top">${projects.projectName }</td>
					<td valign="top">${projects.industry }</td>
					<td  valign="top" ><fmt:formatNumber type="number" value="${projects.currentMoney/projects.totalMoney*100}" maxFractionDigits="0"/>%</td>
				</tr>
				<tr>
					<td colspan="4" align="right">
						<form  method="post" action='<c:url value="/projects/pay/view" ></c:url>'>
							<input type="hidden" value="${projects.id }" name="projects_id" />
							<input type="submit" class="btn btn-primary"  value="确认提交"/>
						</form>
					</td>
				</tr>
			</tbody>
		</table>

	</div>
</div>



