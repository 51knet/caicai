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
						if(window.confirm('你确定要删除课时吗')){
							$("#courseLessonId").val(lesson_id);
							$("#deleLessonNumForm").submit();
			                 return true;
			              }
						
					}
				}
			});
		
	}
	$(document).ready(function(){
		$('.deleteResourcePostBtn').on('click', function() {
			var resource_id = $(this).next().val();
			//alert(resource_id);
			$(' #c_resource_Id').val(resource_id);	
		});
	});
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
											<span style="float: right; margin-right: 20px; font-size: 13px;">
												<c:if test="${lesson.status != null }"> <a href="javascript:void(0)" onclick="deleLessonNum(${lesson.id })"><b>删除课时</b></a>  |</c:if> 
												<a href="#" onclick="showAddResourceForm(${lesson.lessonNum})"><b>添加资源</b></a>
											</span>
											<div style=" border: 1px solid #dcdcdc; background-color: #ffffff; text-align: left; padding: 5px; display: none;" id="${lesson.lessonNum}_resourceForm">
													<form style="margin-left:30px;" method="post" action='<c:url value="/admin/teacher/course/resource/create"></c:url>' enctype="multipart/form-data" >
														<input type="hidden" name="lessonNum" value="${lesson.lessonNum }">
														<input type="hidden" name="lessonId" value="${lesson.id}">
														<input type="hidden" name="courseId" value="${course.id}">
														资源名称：<input type="text" style="width: 207px;" name="resourceName" >&nbsp;“如：第一讲：物种的起源”<br>
														资源类别：<select name="type"   style="width: 220px;">
																			<!-- <option >请选择</option>
																			<c:forEach items="${type}" var="l">
																				<option  value="${l.id}">${l.typeName}</option>
																			</c:forEach> -->
																			<option value="1"  selected>文档</option>
																			<option value="2">视频</option>
																		</select><br> 
														上传资源：<input type="file" name="resourceFile" >&nbsp;不大于200M
														<button type="reset"   class="btn " style="margin-left: 5px;float: right;" onclick="closeResourceForm(${lesson.lessonNum})">取消</button>
														<button type="submit" onclick="upLoadClick();"  class="btn  btn-success" style=" float: right;">上传</button>&nbsp;&nbsp;
													</form>
											</div>
										</div>
										<div style="display:block;" id="${lesson.lessonNum}_courseResourceShow">
											<c:forEach items="${courseMap}" var="cm">
												<c:if test="${cm.key == lesson.lessonNum }">
													<c:forEach var="fileNames" items="${cm.value}"> 
														<div style="font-size: 13px;">
															<c:if test="${fileNames.fileName != null }">
																<div id="${fileNames.id}_courseResourceShowDetail" style="width: 100%; margin-top: 5px; margin-bottom:1px; border-bottom: 1px dotted #dcdcdc;">
																	<table style="width: 94%;margin-left: 20px;" >
																		<tbody>
																			<tr>
																				<td width="8%"> 
																					<c:if test="${fileNames.resourceType.id ==1 }">
																					 	<img src='<c:url value="/resources/resourceType/text.jpg"></c:url>'style="width: 40px;height: 40px; "/>
																					 </c:if>
																					  <c:if test="${fileNames.resourceType.id ==2 }">
																					 	<img src='<c:url value="/resources/resourceType/video.jpg"></c:url>'style="width: 40px;height: 40px; "/>
																					 </c:if>
																				</td>
																				<td align="left" width="60%">
																					<a  href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'>
																						<span style="margin-left: 0px; ">${fileNames.fileName}</span>
																					</a> 
																				</td>
																				<td align="right" width="32%">
																					<span style="font-size: 13px;">
																						<a href='javascript:void(0)' onclick="editCourseResource(${fileNames.id})">修改</a>  | 
																						<a  style=" " class="deleteResourcePostBtn" href="#deleteResourcePostModal" role="button" data-toggle="modal" data-target="#deleteResourcePostModal">
																						删除</a><input type="hidden" value="${fileNames.id}">
																					</span>
																				</td>
																			</tr>
																		</tbody>
																	</table>
																</div>
															</c:if>
															<div id="${fileNames.id}_editCourseResourceForm" style=" border: 1px solid #dcdcdc; background-color: #ffffff; text-align: left; padding: 5px; display: none;" >
																<form name="resourceEditForm" style="margin-left:30px;" method="post" action='<c:url value="/admin/teacher/course/resource/edit"></c:url>' enctype="multipart/form-data" >
																	<input type="hidden" name="resourceId" value="${fileNames.id}" >
																	资源名称：<input type="text" style="width: 207px;" id="${fileNames.id}_editResourceName" name="resourceName" >&nbsp;“如：第一讲：物种的起源”<br>
																	资源类别：<select name="type" id="${fileNames.id}_editResourceType"   style="width: 220px;">
																						<c:forEach items="${type}" var="l">
																							<option  value="${l.id}">${l.typeName}</option>
																						</c:forEach>
																					</select><br>
																	上传资源：<input type="file" name="resourceFile" >&nbsp;不大于200M
																	<button type="reset"   class="btn " style="margin-left: 5px;float: right;" onclick="closeEditResourceForm(${fileNames.id})">取消</button>
																	<button type="submit"   class="btn  btn-success" style=" float: right;">上传</button>&nbsp;&nbsp;
																</form>
															</div>
														</div>
													</c:forEach>
												</c:if>
											</c:forEach>
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
		
		<form action='<c:url value="/admin/teacher/course/edit/courselesson/destory"></c:url>' method="post" style="display:none;" id="deleLessonNumForm">
				<input type="hidden" name="lessonId" id="courseLessonId" >
				<input type="hidden" name="courseId" value="${course.id }" >
		</form>
		<%-- <div class="modal hide fade" id="deleLessonNumForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
		</div> --%>
		
		<!-- delete resource) -->
		<div class="modal hide fade" id="deleteResourcePostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-header">
			    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			    <h3 id="myModalLabel">请注意</h3>
			  </div>
			  <div class="modal-body">
			    <p>你确定删除该资源吗？</p>
			  </div>
			  <div class="modal-footer">
			    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
			    <form action='<c:url value="/admin/teacher/course/resource/destory"></c:url>' method="post" style="display: inline-block;" >
			    	<input id="c_resource_Id" type="hidden" name="resourceId" />
			    	<button class="btn btn-primary">确定</button>
			    </form>
			  </div>
		</div>
		
	</div>
</div>
<script type="text/javascript">
	function editCourseResource(resource_id){
		var show_id=resource_id+"_courseResourceShowDetail";
		var edit_id = resource_id+"_editCourseResourceForm";
		$("#"+show_id).css("display","none");
		$("#"+edit_id).css("display","block");
		$.ajax({
			  type: "post",
			  url: '<c:url value="/admin/teacher/course/resource/edit/ajax"></c:url>',
			  data: "resourceId="+resource_id,
			  dataType:"json",
			  success:function(msg){
				  	$("#"+resource_id+"_editResourceName").val(msg.fileName);
				  	$("#"+resource_id+"_editResourceType option[value='"+msg.resourceType.id+"']").attr("selected","selected");
			  }
		}); 
	}
	
	function closeEditResourceForm(resource_id){
		var show_id=resource_id+"_courseResourceShowDetail";
		var edit_id = resource_id+"_editCourseResourceForm";
		$("#"+show_id).css("display","block");
		$("#"+edit_id).css("display","none");
	}
	
	function showAddResourceForm(lessonNum){
		var id = lessonNum+"_resourceForm";
		$("#"+id).css("display","block");
	}
	function closeResourceForm(lessonNum){
		var id = lessonNum+"_resourceForm";
		$("#"+id).css("display","none");
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