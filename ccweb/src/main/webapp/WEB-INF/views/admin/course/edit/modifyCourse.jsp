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
<div style="margin-top: 10px;">
	<a href="#">课程资料</a>
	<hr />
	<div class="cont">
			<c:choose>
				<c:when test="${resourceCount>0 }">
					<c:forEach var="course" items="${courseMap}" varStatus="i">
							<table   style="width: 90%;  border: 1px solid #dcdcdc; margin-bottom: 3px;">
								<tbody>
									<tr>
										<td align="left">
											<div id="courseOrder">
												<div style="font-size: 15px;  background-color: #f2f2f2; padding: 3px;" id="course_${i.count}" >
													<b>第${course.key}课时</b>
													<span style="float: right; margin-right: 26px;">
														<a href="#">添加内容</a> | <a href="#">编辑</a>
													</span>
													<c:forEach var="fileNames" items="${course.value}">
														<div  class="fileName_${i.count}" >
															<c:if test="${fileNames.fileName != null }">
																<div style="width: 87%; float: left; margin-left: 10px; margin-top: 3px;">${fileNames.fileName}</div>
																<div style="width:11%; float: left; text-align: center;  margin-top:3px;">
																	<a href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'>
																		编辑
																	</a>
																</div>
															</c:if>
														</div>
													</c:forEach>
												</div>
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
			<div style="font-size: 15px; text-align:right;   padding: 5px; width:675px;margin-top: 5px;"  >
					<form action='<c:url value="/admin/teacher/course/edit/addcourseorder"></c:url>' method="post">
							<input type="hidden" name="courseid" value="${course.id }" >
							<button type="submit"    class="btn btn-success">添加新课时</button>
					</form>
			</div>
		</div>
	</div>
	<!-- 
	<div>
		<div
			style="background-color: #f7f7f7; text-align: left; padding: 10px;margin-top:30px; margin-left: 10px;">
			<div>
				<span>第一部分:第一部分</span><span style="margin-left: 70%"><a>编辑</a></span>
			</div>
		</div>
		<div
			style="margin-left: 4%; height: 20px; line-height: 20px; margin-top: 20px">
			<span>第1课:第1课</span><span style="margin-left: 50%; margin-right: 10%">
				添加内容</span><span>编辑</span>
		</div>
		<hr />
		<div
			style="margin-left: 4%; height: 20px; line-height: 20px; margin-top: 20px">添加1课</div>
		<div></div>
		<div
			style="background-color: #f7f7f7; padding: 10px; margin-top: 20px; margin-left: 10px;">
			<div>添加一部分</div>
		</div>
	</div>

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