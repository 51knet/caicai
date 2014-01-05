<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function sessionUserNull(){
		alert("请登录后查看详细信息");
	}
	
	 
	function selectType(){
		var sel = document.getElementById("type");
		var opt = sel.options;
		for(var i=0; i<opt.length;i++){
			if(opt[i].selected){
				var typeValue = opt[i].value;
				window.location.href = '<c:url value="/patent/'+typeValue+ '/list"></c:url>'; 
			}
		}
	}
</script>
<style>
.titlebg{
	background-color:#3d4f65; 
	font-size: 14px;
	width: 100%;
	color: #fff;
	font-weight: bold;
}

.td_right_line{
	background-image: url(' <c:url value="/resources/img/default/blueline.png" ></c:url> ' );
	background-position: right center;
	background-repeat: no-repeat;
}
 .selete_filter{
 	margin-top:10px;
	text-align: left;
}
.patent{
	 width: 1024px;
	 margin: 0px 55px;
}
</style>
  <jsp:include page="/WEB-INF/views/patent/path_link.jsp"></jsp:include>
<!-- <div class="container title"  >
		<div class="innerLeftTitle">专利数量（${searchpatentCount }）</div>
 </div> -->
  <div class="selete_filter">
	<select id="type" onchange="selectType()">
		<option value="all" >全部类别</option>
		<c:forEach items="${fieldList}" var="field">
			<c:choose>
				<c:when test="${patentField == field.fieldName}">
					<option value="${field.fieldName }" selected>${field.fieldName }</option>
				</c:when>
				<c:otherwise>
					<option value="${field.fieldName }">${field.fieldName }</option>
				</c:otherwise>
			</c:choose>
			
		</c:forEach>
	</select>
</div>
 <div class="container patent">
  <table  style="width: 90%; " cellpadding="8"  >
			 <thead>
					<tr class="titlebg">
					<th  width="20%" align="left" class="td_right_line">专利号</th>
					<th   align="left" class="td_right_line">专利名称</th>
					<th  width="10%" align="left" class="td_right_line">专利类别</th>
					<th  width="10%"  align="left" class="td_right_line">应用领域</th>
					<th  width='10%' align="left" class="td_right_line">公开日期</th>
					</tr>
				</thead>
	<c:choose>
		<c:when test="${searchpatentCount <=0}">
			<tr><td colspan="5"><h4 style="margin-left:60px;">未搜索到专利</h4></td></tr>
		</c:when>
		<c:otherwise>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr class="bLine_dash">
						<td>
							<!--<c:if test="${sessionUserInfo == null }">
								<a href="#" onclick="return sessionUserNull()" >${page.patentNum}</a>
							</c:if>
							<c:if test="${sessionUserInfo != null }">
								<a href="<c:url value="/patent/view/${ page.patentNum}"></c:url>">${page.patentNum}</a>
							</c:if> -->
							<a href="<c:url value="/patent/view?id=${ page.patentNum}"></c:url>"><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" >  ${page.patentNum}</a>
						</td>
						<td>
							${page.patentName}
						</td>
						<td>
							${page.patentType.typeName}
						</td>
						<td >
							${page.patentField}
						</td>
						<td>${page.publishDate}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
			    	<tr><td colspan="5">
			        <jsp:include page="/WEB-INF/views/_shared/pagination_post.jsp"></jsp:include>
			   		 </td></tr>
				</tfoot>
				
		</c:otherwise>
	</c:choose>
	</table>
 </div>



