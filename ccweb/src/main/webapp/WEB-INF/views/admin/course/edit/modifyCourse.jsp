<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href=" <c:url value="/resources/js/uploadify/css/default.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/js/uploadify/css/uploadify.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/js/uploadify/js/jquery.uploadify.v2.0.1.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/js/uploadify/js/swfobject.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<style>
<!--
 .cont {
	width: 100%;
	margin-left: 30px;
	margin-bottom: 10px;
}
-->
</style>
<script type="text/javascript">
	function showAddResourceForm(courseOrder){
		var id = courseOrder+"_resourceForm";
		$("#"+id).css("display","block");
	}
	function closeResourceForm(courseOrder){
		var id = courseOrder+"_resourceForm";
		$("#"+id).css("display","none");
	}
	
	function deleLessonNum(lesson_id){
		$.ajax({
			  type: "post",
			  url: "<c:url value='/admin/teacher/course/edit/courselesson/checkFileName'/>",
			  data: "lessonId="+lesson_id,
			  dataType:"text",
			  success:function(num){
					if(num=='1'){
					alert("课时下存在课程资源,请先删除课程资源");
					$("#deleLessonNumForm").unbind( "submit" );
					return false;
					}else{
						$("#courseLessonId").val(lesson_id);
						$("#deleLessonNumForm").submit();
					}
				}
			});
		
	}
	$(document).ready(function(){
		$('.deleteHonorPostBtn').on('click', function() {
			var honor_id = $(this).next().val();
			$('#deleteHonorPostModal #lessonId').val(honor_id);	
		});
	});
	function upLoadClick(){
		return checkEmptyAjax("personal_info_form","personalInfoAJAX");
	}
</script>
<div style="margin-top: 10px;">
	<a href="#">课程资料</a>
	<hr />
	<div class="cont">
		<c:choose>
			<c:when test="${lessonListCount>0 }">
				<c:forEach items="${lessonList }" var="lesson">
					<table   style="width: 90%;  border: 1px solid #dcdcdc; margin-bottom: 5px;">
							<tbody>
								<tr>
									<td align="left">
										<div style="font-size: 15px;  background-color: #f2f2f2; padding: 3px;" >
											<b>第${lesson.lessonNum}课时</b>
											<span style="float: right; margin-right: 27px; font-size: 13px;">
												<c:if test="${lesson.status != null }"> <a  class="deleteHonorPostBtn" href="#deleteHonorPostModal" data-toggle="modal" data-target="#deleteHonorPostModal" role="button" onclick="deleLessonNum(${lesson.id })"><b>删除课时</b></a>  |</c:if> 
												<a href="#" onclick="showAddResourceForm(${lesson.lessonNum})"><b>添加资源</b></a>
											</span>
											<div style=" border: 1px solid #dcdcdc; background-color: #ffffff; text-align: left; padding: 5px; display: none;" id="${lesson.lessonNum}_resourceForm">
													<form style="margin-left:30px;" method="post" action='<c:url value="/admin/teacher/course/resource/create"></c:url>' enctype="multipart/form-data" >
														<input type="hidden" name="lessonNum" value="${lesson.lessonNum }">
														<input type="hidden" name="lessonId" value="${lesson.id}">
														<input type="hidden" name="courseId" value="${course.id}">
														
														<div class="control-group" id="fileName">
															<label class="control-label" for="fileName">资源名称</label>
															<div class="controls">
																<input type="text" style="width: 207px;" name="fileName" >&nbsp;“如：第一讲：物种的起源”<br>
															</div>
														</div>
														
														
														
														
														
														
														
														资源名称：<input type="text" style="width: 207px;" name="resourceName" >&nbsp;“如：第一讲：物种的起源”<br>
														资源类别：<select name="type"   style="width: 220px;">
																			<option >请选择</option>
																			<c:forEach items="${type}" var="l">
																				<option  value="${l.id}">${l.typeName}</option>
																			</c:forEach>
																		</select><br>
														上传资源：<input type="file" name="resourceFile" >&nbsp;不大于200M
														<button type="reset"   class="btn " style="margin-left: 5px;float: right;" onclick="closeResourceForm(${lesson.lessonNum})">取消</button>
														<button type="submit" onclick="upLoadClick();"  class="btn  btn-success" style=" float: right;">上传</button>&nbsp;&nbsp;
													</form>
											</div>
										</div>
										<div style="display:block;" id="courseResourceShow">
											<c:forEach items="${courseMap}" var="cm">
												<c:if test="${cm.key == lesson.lessonNum }">
													<c:forEach var="fileNames" items="${cm.value}"> 
														<div style="font-size: 13px;">
															<c:if test="${fileNames.fileName != null }">
																<div style="width: 100%; float: left;margin-top: 5px; margin-bottom:5px; border-bottom: 1px dotted #dcdcdc;">
																	<span style="margin-left: 30px; ">${fileNames.fileName}</span>
																	<span style="float: right;font-size: 13px;">
																		<a href='javascript:void(0)' onclick="editCourseResource(${fileNames.id})">修改</a>  | 
																		<a style=" margin-right: 31px;" href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'>删除</a> 
																	</span>
																</div>
															</c:if>
														</div>
													</c:forEach>
												</c:if>
											</c:forEach>
										</div>
										<div id="editCourseResourceForm" style=" border: 1px solid #dcdcdc; background-color: #ffffff; text-align: left; padding: 5px; display: none;" >
											<form name="resourceEditForm" style="margin-left:30px;" method="post" action='<c:url value="/admin/teacher/course/resource/edit"></c:url>' enctype="multipart/form-data" >
												<input type="hidden" name="resourceId" id="editResourceId" >
												<input type="hidden" name="courseId" id="editResourceId" >
												资源名称：<input type="text" style="width: 207px;" id="editResourceName" name="resourceName" >&nbsp;“如：第一讲：物种的起源”<br>
												资源类别：<select name="type" id="editResourceType"   style="width: 220px;">
																	<option >请选择</option>
																	<c:forEach items="${type}" var="l">
																		<option  value="${l.id}">${l.typeName}</option>
																	</c:forEach>
																</select><br>
												上传资源：<input type="file" name="resourceFile" >&nbsp;不大于200M
												<button type="reset"   class="btn " style="margin-left: 5px;float: right;" onclick="closeEditResourceForm()">取消</button>
												<button type="submit"   class="btn  btn-success" style=" float: right;">上传</button>&nbsp;&nbsp;
											</form>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
				</c:forEach>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
		<div style="font-size: 15px; text-align:right;   padding: 5px; width:675px;margin-top: 5px;"  >
				<form action='<c:url value="/admin/teacher/course/edit/addlessonnum"></c:url>' method="post">
						<input type="hidden" name="courseId" value="${course.id }" >
						<button type="submit"    class="btn btn-success">添加新课时</button>
				</form>
		</div>
		
		
		<div class="modal hide fade" id="deleLessonNumForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="myModalLabel">请注意</h3>
				</div>
				<div class="modal-body">
					<p>你确定删除吗？</p>
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
					<form action='<c:url value="/admin/teacher/course/edit/courselesson/destory"></c:url>' method="post" style="display: inline-block;">
						<input type="hidden" name="lessonId" id="courseLessonId" >
						<input type="hidden" name="courseId" value="${course.id }" >
						<button class="btn btn-primary">确定</button>
					</form>
				</div>
			</div>
	</div>
</div>
<script type="text/javascript">
	function editCourseResource(resource_id){
		//alert(resource_id);
		$("#courseResourceShow").css("display","none");
		$("#editCourseResourceForm").css("display","block");
		$.ajax({
			  type: "post",
			  url: '<c:url value="/admin/teacher/course/resource/edit/ajax"></c:url>',
			  data: "resourceId="+resource_id,
			  dataType:"json",
			  success:function(msg){
				  	$("#editResourceId").val(msg.id);
				  	$("#editResourceName").val(msg.fileName);
				  	$("#editResourceType option[value='"+msg.resourceType.id+"']").attr("selected","selected");
			  }
		}); 
	}
	
	function closeEditResourceForm(){
		$("#courseResourceShow").css("display","block");
		$("#editCourseResourceForm").css("display","none");
	}
</script>
		

	<!--
	<div id="testUploadify" style="margin-top: 20px;">
		<div id="fileQueue" style="height: 100px; padding-left: 10px; display: block;"></div>
		<form id="course_form">
		<input type="hidden" name="tid" value="${course.teacher.id }"  />
			课时：<input type="text" name="courseOrder" id="courseOrder"  /><br>
		</form>
			<input type="file" name="uploadify" id="uploadify_test" />
		<p>
		<a href="javascript: $('#uploadify_test').uploadifySettings('scriptData',{'courseOrder':$('#courseOrder').val()}); jQuery('#uploadify_test').uploadifyUpload()">开始上传</a>&nbsp;
		<a href="javascript:jQuery('#uploadify_test').uploadifyClearQueue()">取消所有上传</a>
		</p>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#uploadify_test").uploadify({
					'uploader'       : '<c:url value="/resources/js/uploadify/uploadify.swf" />',
					'script'        	    : '<c:url value="/admin/teacher/course/edit/'+${course.id}+'/resource/new" />',
					'cancelImg'     : '<c:url value="/resources/js/uploadify/images/cancel.png" />',
				//	'scriptData'     : $("#course_form").serialize(),
				//	'scriptData'		:{'courseOrder': $("#courseOrder").val()},
					'folder'         	: 'uploads',
					'queueID'        : 'fileQueue',
					'method'			:'get',
					'auto'           : false,
					'multi'          : true,
					'simUploadLimit' : 2,
					'buttonText'	 : 'search'//,
				});
			});
		</script>
	</div>
	 -->