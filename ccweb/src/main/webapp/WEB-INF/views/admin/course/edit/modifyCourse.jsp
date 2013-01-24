<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href=" <c:url value="/resources/js/uploadify/css/default.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/js/uploadify/css/uploadify.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/js/uploadify/js/jquery.uploadify.v2.0.1.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/js/uploadify/js/swfobject.js" />"></script>
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
</script>
<div style="margin-top: 10px;">
	<a href="#">课程资料</a>
	<hr />
	<div class="cont">
		<!--  
		<c:choose>
			<c:when test="${resourceCount>0 }">
				<c:forEach var="courseResource" items="${courseMap}" varStatus="i">
						<table   style="width: 90%;  border: 1px solid #dcdcdc; margin-bottom: 5px;">
							<tbody>
								<tr>
									<td align="left">
										<div style="font-size: 15px;  background-color: #f2f2f2; padding: 3px;" id="course_${i.count}" >
											<b>第${courseResource.key}课时</b>
											<span style="float: right; margin-right: 27px; font-size: 14px;">
												<a href="#" onclick="showAddResourceForm(${courseResource.key})">添加内容</a> | <a href="#">编辑</a>
											</span>
											<div style=" border: 1px solid #dcdcdc; background-color: #ffffff; text-align: left; padding: 5px; display: none;" id="${courseResource.key }_resourceForm">
													<form style="margin-left:30px;" method="post" action='<c:url value="/admin/teacher/${course.id }/resource/create"></c:url>' enctype="multipart/form-data" >
														<input type="hidden" name="courseOrder" value="${courseResource.key }">
														资源名称：<input type="text" name="resourceName" >&nbsp;“如：第一讲：物种的起源”<br>
														上传资源：<input type="file" name="resourceFile">&nbsp;单次上传不大于200M
														<button type="submit"   class="btn  btn-success" style="margin-left: 40px;">上传</button>
														<button type="reset"   class="btn " style="margin-left: 5px;" onclick="closeResourceForm(${courseResource.key})">取消</button>
													</form>
											</div>
											<c:forEach var="fileNames" items="${courseResource.value}"> 
												<div  class="fileName_${i.count}">
													<c:if test="${fileNames.fileName != null }">
														<div style="width: 100%; float: left;margin-top: 5px; margin-bottom:5px; font-size: 14px; border-bottom: 1px dotted #dcdcdc;">
															<span style="margin-left: 30px;">${fileNames.fileName}</span>
															<a style="float: right; margin-right: 27px;" href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'>
																	编辑
															</a>
														</div>
													</c:if>
												</div>
											</c:forEach>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
			</c:when>
			<c:otherwise>
				尚未添加资源
			</c:otherwise>
		</c:choose>
		-->
		<c:choose>
			<c:when test="${lessonListCount>0 }">
				<c:forEach items="${lessonList }" var="lesson">
					<table   style="width: 90%;  border: 1px solid #dcdcdc; margin-bottom: 5px;">
							<tbody>
								<tr>
									<td align="left">
										<div style="font-size: 15px;  background-color: #f2f2f2; padding: 3px;" >
											<b>第${lesson.lessonNum}课时</b>
											<span style="float: right; margin-right: 27px; font-size: 14px;">
												<a href="#" onclick="showAddResourceForm(${lesson.lessonNum})">添加</a> | <a href="#">删除</a>
											</span>
											<div style=" border: 1px solid #dcdcdc; background-color: #ffffff; text-align: left; padding: 5px; display: none;" id="${lesson.lessonNum}_resourceForm">
													<form style="margin-left:30px;" method="post" action='<c:url value="/admin/teacher/${course.id }/resource/create"></c:url>' enctype="multipart/form-data" >
														<input type="hidden" name="lessonNum" value="${lesson.lessonNum }">
														<input type="hidden" name="lessonId" value="${lesson.id}">
														资源名称：<input type="text" style="width: 207px;" name="resourceName" >&nbsp;“如：第一讲：物种的起源”<br>
														资源类别：<select name="type"   style="width: 220px;">
																			<option >请选择</option>
																			<c:forEach items="${type}" var="l">
																				<option  value="${l.id}">${l.typeName}</option>
																			</c:forEach>
																		</select><br>
														上传资源：<input type="file" name="resourceFile" >
															<button type="reset"   class="btn " style="margin-left: 5px;float: right;" onclick="closeResourceForm(${lesson.lessonNum})">取消</button>
														<button type="submit"   class="btn  btn-success" style=" float: right;">上传</button>&nbsp;&nbsp;
													
													</form>
											</div>
											<div style="">
												<c:forEach items="${courseMap}" var="cm">
														<c:if test="${cm.key == lesson.lessonNum }">
															<c:forEach var="fileNames" items="${cm.value}"> 
																<div >
																	<c:if test="${fileNames.fileName != null }">
																		<div style="width: 100%; float: left;margin-top: 5px; margin-bottom:5px; font-size: 14px; border-bottom: 1px dotted #dcdcdc;">
																			<span style="margin-left: 30px;">${fileNames.fileName}</span>
																			<a style="float: right; margin-right: 27px;" href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'>
																					编辑
																			</a>
																		</div>
																	</c:if>
																</div>
															</c:forEach>
														</c:if>
												</c:forEach>
											</div>
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
				<form action='<c:url value="/admin/teacher/course/edit/addcourseorder"></c:url>' method="post">
						<input type="hidden" name="courseid" value="${course.id }" >
						<button type="submit"    class="btn btn-success">添加新课时</button>
				</form>
		</div>
	</div>
</div>

		

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