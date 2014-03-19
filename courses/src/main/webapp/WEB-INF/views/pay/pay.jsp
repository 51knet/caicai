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
	width:950px;
	margin: 50px 10px 0px 10px;
	background-color: #FFF;
	padding:20px 20px 20px 20px;
	border: 1px solid #e28f03;
	border-bottom: 2px solid #e28f03;
	
}
.container .content >table{
	width: 950px;

}

.container .content >table >thead >tr >th >img{
	width: 100px; height: 100px; float: left;
}
.container .title{
	width: 950px;
	margin: 30px 30px 0px 30px;
	border-bottom: 1px solid #e28f03;
}

.container .sub{
	margin: 30px 30px 0px 30px;
}

</style>
<div class="container">
	<c:if test="${ !paySuccessful }">
		<img src='<c:url value="/resources/img/pay/pay2.png"></c:url>' /><br>
	</c:if>
	<c:if test="${paySuccessful }">
		<img src='<c:url value="/resources/img/pay/pay3.png"></c:url>' /><br>
	</c:if>
	<div class="content">
		<table cellpadding="10" >
			<thead>
				<tr >
					<th  width="20%"><img src="<c:url value='${p_url }${seller.photo_url }'></c:url>" style="float: right;"  /> </th>
					<th align="left" valign="bottom" width="10%" style="font-size: 17px; color: #e28f03"> ${seller.name }</th>
					<th  align="left" valign="bottom" width="60%"  style="font-size: 22px;" >项目名称：${projects.projectName }</th>
					<th  align="center" valign="bottom"  >&nbsp; </th>
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
				<input type="password" id="password" name="password" /> 
				<input type= "hidden" name="projects_id" value="${projects.id }" />
				<input type="submit" class="btn btn-primary" value="提交" />
			</form>
		</c:if>
		<c:if test="${paySuccessful }">
			<a href='<c:url value="/projects/list/all"></c:url>' > 参股成功，点击返回</a>
		</c:if>
	</div>

</div>