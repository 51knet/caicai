<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:if test="${ flash!=null }">
	<div class="alert ${flash.messageType.styleName} fade in">
      <button type="button" class="close" data-dismiss="alert">×</button>
      <strong>消息：</strong> ${flash.message}.
    </div>
</c:if>