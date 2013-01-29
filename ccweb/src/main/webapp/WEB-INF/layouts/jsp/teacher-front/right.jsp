<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">
	function checkCoursePwd(cid,tid){
		$("#errorMsg").html("");
		$.post('<c:url value="/checkCoursePwd" />', $("#checkpwd_form").serialize(), function(flag){
			//alert(typeof flag+flag);
				if('true'==flag){
					window.location.href ='<c:url value="/teacher/'+tid+'/course/view/'+cid+'" ></c:url>';
				}else{
					$("#errorMsg").html("密码错误！");
					return false;
				}			
		});
	}
</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
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
<!-- ${teacherInfo.id} -->
<div class="row-fluid custom round">
	<div class="row"><h5>公告 </h5></div>
	
	<div class="row">
		<!--<c:choose>
			<c:when test="${annoContent != null && annoContent != ''}">
				<a href="<c:url value="/teacher/${teacherInfo.id}/announcement/view/${annoId}"></c:url>">${annoContent}</a>
			</c:when>	
			<c:otherwise>无公告</c:otherwise>
		</c:choose>-->
		<c:choose>
			<c:when test="${annoCount>0}">
				<table class="table">
					<tbody>
						<c:forEach var="anno" items="${annolist}" begin="0" end="2">
							<tr>
								<td  width="80%"><a href="<c:url value="/teacher/${teacherInfo.id}/announcement/view/${anno.id}"></c:url>">${anno.title}</a>
								</td>
								<td>
									${anno.date}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<tr><td colspan="4">无内容</td></tr>
			</c:otherwise>
		</c:choose>
				<hr>
		<div style="text-align: right;">
		<c:if test="${annoCount>3}"><a href="<c:url value="/teacher/${teacherInfo.id}/announcement/list"></c:url>"> 查看所有>></a></c:if>&nbsp;&nbsp;</div>
	</div>
	<hr>
	<div style="text-align: right;">
	</div>
</div>

<!-- teacher resource -->
<div class="row-fluid custom round">
		<div class="row">
			<h5>教学资源</h5>
		
		</div>
		
		<div class="row ">
			<c:choose>
				<c:when test="${resourceCount !=0}">
					<table class="table">
						<tbody>
							<c:forEach var="resource" items="${resourceList}">
								<tr>
									<td  width="60%"><a href="<c:url value="/teacher/${teacherInfo.id}/resource/view/${resource.id}"></c:url>"> ${resource.fileName } </a>
									</td>
									<td width="20%">${resource.resourceType.typeName }</td>
									<td>
										${resource.date}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<tr><td colspan="4">无内容</td></tr>
				</c:otherwise>
			</c:choose>
		
		<hr>
		<div style="text-align: right;">
		<c:if test="${resourceCount>3}"><a href="<c:url value="/teacher/${teacherInfo.id}/resource/list"></c:url>"> 查看所有>></a></c:if>&nbsp;&nbsp;</div>
		</div>
</div>

<!-- teacher course -->
<div class="row-fluid custom round">
		<div class="row">
			<h5>课程资料</h5>
		</div>
	
		<div class="row ">
		<table class="table">
			<c:choose>
				<c:when test="${courseCount !=0}">
					<tbody>
						<c:forEach var="course" items="${courseList}">
							<tr>
								<td width="80%"><!--   -->
									<c:choose>
										<c:when test='${course.pwd == "" || course.pwd == null}'>
												<a href="<c:url value="/teacher/${teacherInfo.id}/course/view/${course.id}"></c:url>"> ${course.courseName }</a>
										</c:when>
										<c:otherwise>
											<a href="#checkcourse" data-toggle="modal"> ${course.courseName }</a> 
											<div id="checkcourse" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px; height:180px; ">
												<div class="modal-header">
													<h4 id="myModalLabel">验证密码</h4>
												</div>
												<div class="modal-body">
													<form  method="post" id="checkpwd_form">
														<input type="hidden" value="${course.id}" name="cid"> 
														输入密码：<input type="text" name="coursepwd" id="coursepwd" placeholder="密码">
														<span id="errorMsg" style="font-size: 14px; color: red"></span>
													</form>
													<div style="margin-left: 120px;">
														<button class="btn btn-primary"  onclick="checkCoursePwd( ${course.id} , ${teacherInfo.id})">确定</button>&nbsp;&nbsp;
														<button class="btn" type="reset" data-dismiss="modal" aria-hidden="true">取消</button>
													</div>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									${course.courseDate}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:otherwise>
					<tr><td colspan="3">无内容</td></tr>
				</c:otherwise>
			</c:choose>
		</table>
		<hr>
		<div style="text-align: right;">
		<c:if test="${courseCount>5}"><a href="<c:url value="/teacher/${teacherInfo.id}/course/list"></c:url>"> 查看所有>></a></c:if>&nbsp;&nbsp;</div>
		</div>
</div>


<!-- teacherAchievement  
<div class="row-fluid custom round">
	<div class="row">
		<h5>科研成果</h5>
		<hr>
	</div>
	
	<div class="row">
		&nbsp;&nbsp;<b>论文</b>
	<table class="table">
		<c:choose>
			<c:when test="${thesisCount !=0}">
				<thead>
					<tr><th >论文内容</th></tr>
				</thead>
				<tbody>
					<c:forEach var="thesis" items="${thesisList}">
						<tr>
							<td>
								${thesis.content }
							</td>
						</tr>
					</c:forEach>
				
				</tbody>
			</c:when>
			<c:otherwise>
				<tr><td >无内容</td></tr>
			</c:otherwise>
		</c:choose>
		<c:if test="${thesisCount > 2}"><tr><td align="right"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/thesis/list"></c:url>"> 查看所有>></a></div></td></tr></c:if>
	</table><br>
	
			&nbsp;&nbsp;<b>项目</b>
	<table class="table">
		<c:choose>
			<c:when test="${projectCount !=0}">
				<thead>
					<tr><th >项目名称</th><th >项目来源</th><th width=15%>开始时间</th><th width=15%>结束时间</th></tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${projectList}">
						<tr>
							
							<td  >
								${project.title}</td>
							<td  >${project.source}</td>
							<td  >${project.startTime}</td>
							<td  >${project.endTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:when>
			<c:otherwise>
				<tr><td colspan="3">无内容</td></tr>
			</c:otherwise>
		</c:choose>
		<c:if test="${projectCount > 2}"><tr><td align="right" colspan="3"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/project/list"></c:url>"> 查看所有>></a></div></td></tr></c:if>
	</table><br>
				&nbsp;&nbsp;<b>专利</b>
	<table class="table">
		<c:choose>
			<c:when test="${patentCount !=0}">
				<thead>
						<tr><th>发明人</th><th>专利名称</th><th>专利类型</th><th>专利申请号</th></tr>
				</thead>
				<tbody>
					<c:forEach var="patent" items="${patentList}">
						<tr>
							<td >${patent.inventer}</td>
							<td >${patent.name}</td>
							<td >${patent.type}</td>
							<td >${patent.number}</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:when>
			<c:otherwise>
				<tr><td colspan="4">无内容</td></tr>
			</c:otherwise>
		</c:choose>
			<c:if test="${patentCount > 2}"><tr><td align="right" colspan="4"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/patent/list"></c:url>"> 查看所有>></a></div></td></tr></c:if>
	</table><br>
		&nbsp;&nbsp;<b>荣誉</b>
	<table class="table">
		<c:choose>
			<c:when test="${honorCount !=0}">
				<thead>
					<tr><th >荣誉名称</th><th>获奖原因</th></tr>
				</thead>
				<tbody>
					<c:forEach var="honor" items="${honorList}">
						<tr>
							<td>
								${honor.name }
							</td>
							<td>
								${honor.reason}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:when>
			<c:otherwise>
				<tr><td colspan="2">无内容</td></tr>
			</c:otherwise>
		</c:choose>
				<c:if test="${honorCount > 2}"><tr><td align="right" colspan="2"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/honor/list"></c:url>"> 查看所有>></a></div></td></tr></c:if>
	</table>
	</div>
</div>
-->
	
<div class="row-fluid custom round">
	<div class="row">
		<h5>博文</h5>
	</div>
	
	<div class="row ">
	<table class="table">
		<tbody>
			<c:forEach var="blogPost" items="${blogPosts}">
			<tr>
				<td width="80%"><a href="<c:url value="/teacher/${teacherInfo.id}/blog/view/${blogPost.id}"></c:url>"> ${blogPost.title} </a></td>
				<td>
				<fmt:formatDate value="${blogPost.dateCreated}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>


