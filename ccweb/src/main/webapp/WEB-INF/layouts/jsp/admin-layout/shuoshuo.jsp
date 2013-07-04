<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!-- 
<c:if test="${ flash!=null }">
	<div class="alert ${flash.messageType.styleName} fade in">
      <button type="button" class="close" data-dismiss="alert">×</button>
      <strong>消息：</strong> ${flash.message}.
    </div>
</c:if> -->
<div style="margin: 0 auto; width: 100%; text-align: center;">
	<form style="margin-top: 10px;">
		<textarea rows="10" cols="" style="width: 90%; " >想说点什么呢？</textarea><br>
		<div class="offset8">
			<button class="btn btn-success offset">发布</button> <button class="btn btn-success ">取消</button>
		</div>
	</form>
</div>