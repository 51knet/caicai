<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#t").focus(function() {
		$(".help-inline").html("");
	});
	$("#c").focus(function() {
		$(".help-inline").html("");
	});
	checkAjax("anno_information","annoInfoAJAX");
});
</script>
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
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>


<div class="row-fluid custom round">
	<div  class="row" style="margin-top: 10px;">
		<a href='<c:url value="/admin/teacher/announcement/list"></c:url>'><b>教师公告</b></a>
		<hr>
		<div style="text-align: right;">
			<a style="margin-bottom: 10px; font-size: 14px;" href="#myModal" role="button"
							class="btn" data-toggle="modal">添加公告&nbsp;&nbsp;</a><br>
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead><tr>
						<th  align="center" width="15%">公告标签</th>
						<th  align="center" width="50%" >公告内容</th>
						<th  align="center" width="20%">发布时间</th>
						<th  align="center" width="15%">操作</th>
					</tr>
				</thead>
				<tbody>
					<!-- <tr>
						<td>公告标签</td>
						<td>公告内容</td>
						<td>发布时间</td>
						<td>公告操作</td>
					</tr> -->
					<c:forEach items="${page.content}" var="page">
						<tr>
							<td align="left" >${page.title}</td>
							<td align="left" >
									<div style="width:350px;" id="content">${page.content}</div>
							</td>
							<td align="left">${page.date}</td>
							<td align="center">
							<!-- 
								<div class="btn-group">
									<button class="btn">更多</button>
									<button class="btn dropdown-toggle" data-toggle="dropdown">
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a
											href='<c:url value="/admin/teacher/announcement/edit/${page.id}"></c:url>'>修改</a></li>
										<li></li>
									</ul>
								</div>
								 -->
								 <a href='<c:url value="/admin/teacher/announcement/destory/${page.id}"></c:url>'>删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<!-- <tfoot>
					<tr>
						<td colspan="4" align="right"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></td>
					</tr>
				</tfoot> -->
			</table>
			<div class="row"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
			<br />
			
			<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true" style="text-align: left;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">公告添加</h3>
				</div>
				<form:form action='new' method="post" id="anno_information">
					<div class="modal-body" id="title">
		
						公告标签：&nbsp;<input type="text" name="title" id="t" placeholder="公告标签"  >
						<span class="help-inline"><form:errors path="title"></form:errors></span>
					</div>
					<div class="modal-body" id="content">
						公告内容：
						<textarea name="content" placeholder="公告内容" id="c" cols="5"
							rows="8" style="width:380px;"></textarea>
						<span class="help-inline"><form:errors path="content"></form:errors></span>
					</div>
					<div class="modal-footer">
					
						<button class="btn btn-primary" type="submit">保存</button>
							<button class="btn" type="reset" data-dismiss="modal"
							aria-hidden="true">取消</button>
					</div>
				</form:form>
			</div>
		</div>
		<script type="text/javascript">
			function ApplyStyle(s){
				document.getElementById("mytab").className=s.innerText;
			}
		</script>
		<!-- <table><tr><td>
			点击链接切换样式：<a href="javascript:;" onclick="ApplyStyle(this)">t1</a>&nbsp;
			<a href="javascript:;" onclick="ApplyStyle(this)">t2</a>&nbsp;
			<a href="javascript:;" onclick="ApplyStyle(this)">t3</a>
		</td></tr></table> -->
	</div>	
</div>
