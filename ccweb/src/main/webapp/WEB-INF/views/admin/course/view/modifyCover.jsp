<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style type="text/css">
#preview{}
#showimg {width: 210px; height: 110px; margin-left:100px; margin-top: 20px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
</style>
<script type="text/javascript">
function previewImage(file)
{
 	var MAXWIDTH  = 210;
  	var MAXHEIGHT = 110;
  	var MARGIN_LEFT=100;
  	var MARGIN_TOP=20;
  var div = document.getElementById('preview');
  if (file.files && file.files[0])
  {
  	div.innerHTML = '<img id=showimg>';
  	var img = document.getElementById('showimg');
  	img.onload = function(){
  	  var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, MARGIN_LEFT,MARGIN_TOP);
  	  img.width = rect.width;
  	  img.height = rect.height;
  	  img.style.marginLeft = MARGIN_LEFT;
  	  img.style.marginTop = MARGIN_TOP;
  	}
  	var reader = new FileReader();
  	reader.onload = function(evt){img.src = evt.target.result;}
  	reader.readAsDataURL(file.files[0]);
  }
  else
  {
	    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
	    file.select();
	    document.getElementById("courseCover").style.display="none";
	    var src = document.selection.createRange().text;
	    div.innerHTML = '<img id=showimg>';
	    var img = document.getElementById('showimg');
	    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
	    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, MARGIN_LEFT, MARGIN_TOP);
	    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
	    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";
  }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
	var param = {top:MARGIN_TOP, left:MARGIN_LEFT, width:width, height:height};
	/* if( width>maxWidth || height>maxHeight )
	{
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;
		
		if( rateWidth > rateHeight )
		{
			param.width =  maxWidth;
			param.height = Math.round(height / rateWidth);
		}else
		{
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	} */
	
	param.width = MAXWIDTH;
	param.height = MAXHEIGHT;
	param.left = MARGIN_TOP;
	param.top = MARGIN_TOP;
	return param;
}
</script>
<div style="margin-top: 10px;">
	<a href="#">修改封面</a>
	<hr />
	<div style="width: 780px; margin-top: 30px;">
		<span > 封面预览 </span>
		<div id="preview">
		<img name="showimg" id="showimg" src=""
			style="display: none;" />
		</div>
		<div id="courseCover" style="margin-top: 10px;">
			<span> <c:choose>
					<c:when test="${course.courseCover != null && course.courseCover != ''}">
						<img src='<c:url value="${course.courseCover}"> </c:url>' style="width: 210px; height: 110px; margin-left: 100px;" />
					</c:when>
					<c:otherwise>
						<img src='<c:url value="/resources/img/teacher_front_bg.jpg"></c:url>' style="width: 210px; height: 110px;" />
					</c:otherwise>
				</c:choose>
			</span>
		</div>
	</div>
	<br />
	<div style="margin-top: 2px; width: 780px;">
		<form action="moidfycover" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>上传封面</td>
					<td><input type="file" style="width: 450px; margin-left: 40px;" size="50px" name="file" id="coverFile" onChange="previewImage(this);"></td>
				</tr>
				<tr>
					<td style="color: red; font-size: 14px; padding-left: 98px;" colspan="2">只支持jpg、gif、bmp格式，建议封面宽度210px，高度110px</td>
				</tr>
				<tr>
					<td colspan="2"><button class="btn btn-large btn-success" type="submit" style="margin-left: 100px;">保存</button></td>
				</tr>
			</table>
		</form>
	</div>
</div>