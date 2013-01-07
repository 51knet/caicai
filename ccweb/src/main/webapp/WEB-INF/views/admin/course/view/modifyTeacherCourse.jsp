<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="<c:url value="/resources/jquery/jquery-1.3.2.js" />"></script>
<script type="text/javascript">
	var progress_id = "bar";
	function SetProgress(progress) {
		if (progress) {
			$("." + progress_id + " > div")
					.css("100%", String(progress) + "%"); //控制#loading div宽度
			$("." + progress_id + " > div").html(String(progress) + "%"); //显示百分比
		}
	}

	var i = 0;
	function doProgress() {
		if (i > 100) {
			$("#message").html("加载完毕！").fadeIn("slow");//加载完毕提示
			return;
		}

		if (i <= 100) {
			setTimeout("doProgress()", 10);
			SetProgress(i);
			i++;
		}
	}
	$(document).ready(function() {
		doProgress();
	});
</script>
<div style="margin-top: 10px;">
	<a href="#">课程资料</a>
	<hr />
	<div>
		<div class="progress">
			<div class="bar" style="width:100%;"><div id="message"></div></div>
		</div>
		<div
			style="background-color: #f7f7f7; text-align: left; padding: 10px; margin-left: 10px;">
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
</div>