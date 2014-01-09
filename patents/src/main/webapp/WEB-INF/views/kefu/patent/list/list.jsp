<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
.margin_right{
	margin-right: 15px;
	font-size: 14px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>专利列表</h4>
	</div>
	<div class="content" >
		<a class="margin_right"  href="<c:url value='/admin/kefu/patent/list/all'></c:url>" >全部 </a>
		<a  class="margin_right" href="<c:url value='/admin/kefu/patent/list/pass'></c:url>">通过 </a>
		<a class="margin_right"  href="<c:url value='/admin/kefu/patent/list/waite'></c:url>">审核中 </a>
		<a class="margin_right"  href="<c:url value='/admin/kefu/patent/list/china'></c:url>">国内 </a>
		<a  href="<c:url value='/admin/kefu/patent/list/foreign'></c:url>">国际</a>
		  <form  class="navbar-form"  action="<c:url value="/admin/kefu/search/patent"></c:url>"  method="get" style="float: right; margin-bottom:10px;">
				<input type="text" name="searchParam"   placeholder="搜索"  value="${searchParam }" > 
				<button type="submit" class="btn btn_font" style=" ">搜索</button>				
	     </form>
		<br><br>
		<table class="blue" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr>
					<th width="20%">申请号</th>
					<th>名称</th>
					<th  width="15%">申请日</th>
					<th  align="center" width="10%">首页显示</th>
					<th  align="center" width="10%">通过审核</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="page">
					<tr>
						<td ><a href='<c:url value="/admin/kefu/patent/view?id=${page.patentNum }"></c:url>' >${page.patentNum }</a></td><td >${page.patentName }</td>
						<td >${page.applicationDate }</td>
						<!-- <td align="center"><a href='<c:url value="/admin/patent/edit/${page.patentNum}"></c:url>'>修改</a> | 
						<a class="destoryPatentPostBtn" href="#destoryPatentPostModal" role="button" data-toggle="modal" data-target="#destoryPatentPostModal">删除</a><input type="hidden"  value="${page.patentNum}"> </td> -->
						<td  align="center"><input type="checkbox" <c:if test="${page.focus ==1 }">checked </c:if> onchange="changeFocus(${page.patentNum } , ${page.focus})" ></td>
						<td  align="center"><input type="checkbox" <c:if test="${page.status ==1 }">checked </c:if>  onchange="changeStatus( ${page.patentNum } , ${page.status } )" ></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:choose>
			<c:when test="${searchParam != null }">
				  <jsp:include page="/WEB-INF/views/_shared/pagination_query.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
			 	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
		
	</div>
</div>

<div class="modal hide fade" id="destoryPatentPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除该专利吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/patent/delete"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="p_delete_id" type="hidden" name="patentNum" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>
<script type="text/javascript">
function changeFocus(patentNum,focus){
	
	$.ajax({
		   type: "GET",
		   url: "<c:url value='/admin/kefu/patent/focus/change'></c:url>",
		   data: "patentNum="+patentNum+"&focus="+focus,
		   success: function(flag){
			   if(flag == true){
				   alert( "首页展示修改成功");
			   }else{
				   alert("服务器正忙，请稍后再试");
			   }
		    }
		});
}

function changeStatus(patentNum , status){
	
	$.ajax({
		   type: "GET",
		   url: "<c:url value='/admin/kefu/patent/status/change'></c:url>",
		   data: "patentNum="+patentNum+"&status="+status,
		   success: function(flag){
			   if(flag == true){
				   alert( "审核修改成功");
			   }else{
				   alert("服务器正忙，请稍后再试");
			   }
		   }
		});
}

</script>