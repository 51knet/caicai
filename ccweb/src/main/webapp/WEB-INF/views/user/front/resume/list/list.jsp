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
		<div class="top_left "><span>个人资料</span></div>
		<div class="top_right "></div>
	</div>
	<div class="content ">
		<address style="line-height: 30px;">
				<abbr title="我的昵称">我的昵称：</abbr> ${userInfo.name}
				<br>
				<abbr title="所属高校">所属高校：</abbr> ${userInfo.student.college}
				<br>
				<abbr title="所属专业">所属专业：</abbr> ${userInfo.student.major}
				<br>
				<abbr title="大学教师">大学教师：</abbr> ${userInfo.student.teacher}
				<br>
				<abbr title="毕业时间">毕业时间：</abbr> ${userInfo.student.graduateTime}
		</address>
	</div>
	
		<div class="row">
		<div class="top_left "><span>联系方式</span></div>
		<div class="top_right "></div>
	</div>
	<div class="content ">
		<address style="line-height: 30px;">
			<c:choose>
			<c:when test="${userInfo.address == null||userInfo.address == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="地址">联系地址：</abbr> ${userInfo.address} 
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${userInfo.fax == null||userInfo.fax == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="传真">联系传真：</abbr> ${userInfo.fax}
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${userInfo.phone == null||userInfo.phone == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="电话">联系电话：</abbr>  ${userInfo.phone} 
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${userInfo.email == null||userInfo.email == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="电邮">电子邮件：</abbr> <a href="mailto:#">${userInfo.email}</a>
			</c:otherwise>
			</c:choose>
		</address>

	</div>
	
	<div class="row">
		<div class="top_left "><span>教育背景</span></div>
		<div class="top_right "></div>
	</div>
	<div class="content ">
		<c:choose>
			<c:when test="${eduCount>0 }">
				<table class="blue" width="100%" cellpadding="5">
					<thead>
						<tr>
							<th>教育程度</th>
							<th>学校</th>
							<th>学院</th> 
							<th>专业</th> 
							<th>班级</th>
							<th>教师</th>
							<th>入学时间</th> 
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${eduInfo}" var="eduInfo">
							<tr>
								<td align="center"><c:forEach items="${levelmap}" var="map">
									<c:if test="${map.key == eduInfo.level }">${map.value }</c:if>
								</c:forEach></td>
								<td align="center">${eduInfo.school}</td>
								<td align="center">${eduInfo.college}<c:if test="${eduInfo.college == null }"> 无</c:if></td>
								<td align="center">${eduInfo.major}<c:if test="${eduInfo.major == null}"> 无</c:if></td>
								<td align="center">${eduInfo.classNum}</td>
								<td align="center">${eduInfo.teacherNam}</td>
								<td align="center" >${eduInfo.startTime}</td> 
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				尚未添加内容
				<br>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="row">
		<div class="top_left "><span>工作经历</span></div>
		<div class="top_right "></div>
	</div>
	<div class="content ">
		<c:choose>
			<c:when test="${(workCount >0)}">
				<table class="blue" width="100%" cellpadding="5">
					<thead>
						<tr>
							<th>公司名称</th>
							<th>公司职位</th>
							<th>起止时间</th> 
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${workInfo}" var="workInfo">
							<tr>
								<td align="center">${workInfo.company}</td>
								<td align="center">${workInfo.position}</td>
								<td align="center" width="25%">${workInfo.startTime} - ${workInfo.endTime}</td> 
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
			<span style="font-size: 13px;">尚未添加内容<br>
				<br></span>
			</c:otherwise>
		</c:choose>
	</div>

</div>

