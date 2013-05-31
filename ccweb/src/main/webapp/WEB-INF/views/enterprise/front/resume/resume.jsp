<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function requestCourseDetail(cid,eid){
		$("#course_id").val(cid);
		$("#enterprise_id").val(eid);
		$("#showCourseDetail").submit();
	}
</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	/*background: #FAFAFB;*/

}
.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #f77605 1.5px;
	padding-bottom: 4px;
	margin: 0px 0px 0px 0px;
	padding:0px 10px 5px 10px;
}
.row-fluid.custom .row > h4 >span {
	font-size: 14px;
	color:#666;
}

.row-fluid.custom .row {
	margin: 0px 10px 0px 10px;
}

.row-fluid.custom .row .title{
	margin: 20px 30px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .row .content{
	margin: 20px 30px;
	width:660px;
	word-wrap:break-word;
}
.row-fluid.custom .row .content .limitTable{
	margin-top: 10px; 
	width:655px; 
	table-layout:fixed
}

.row-fluid.custom .row .content .limittable .limitTd{
	word-wrap:break-word; word-break:break-all;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>学校介绍 </h4>
	</div>
	<div class="row"  style="border: solid 1px #f77605;">
		<div class="content ">
			<table width="100%">
				<tr>
					<td align="left" width="25%">
						<c:url var="avatar_url" value="${userInfo.avatar}"></c:url>
						<img width="150px" height="150px" src="${avatar_url}" style="margin-top: 10px;">
					</td>
					<td width="5%"></td>
					<td align="left">
						<h3>${userInfo.name}</h3>	
					</td>
				</tr>
			</table>
		</div>
		
		<div class="title ">
			<h4>联系方式</h4>
		</div>
		<div class="content ">
			<address>
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
				<abbr title="电话">联系电话：</abbr> ${userInfo.phone} 
				<br>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${userInfo.email == null||userInfo.email == '' }">
				</c:when>
				<c:otherwise>
				<abbr title="电邮">电子邮件：</abbr> <a href="mailto:${userInfo.email}">${userInfo.email}</a>
				</c:otherwise>
				</c:choose>
			</address>
		</div>
		<div class="title ">
			<h4>公司发展</h4>
		</div>
		<div class="content">
			<table   cellpadding="4" class="limitTable">
				<tbody>
				<c:choose>
					<c:when test="${honorCount>0}">
						<c:forEach var="honor" items="${honorList}">
							<tr>
								<td class="limitTd">
								${honor.desc}
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr><td colspan="3">无内容</td></tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</div>


