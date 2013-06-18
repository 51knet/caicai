<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
				<div class="span12">
					<div class="banner_front" style="position: relative; z-index: 1;">
						<div style=" height: 120px; width: 120px;  position: absolute; z-index: 999; margin-left: 100px; margin-top:40px; border: 0px solid #fff; color: #fff;"></div>
						<a href="<c:url value='/admin' ></c:url>">
							<c:if test="${teacherInfo.user.banner_id == null }">
								<img src="<c:url value='/resources/img/default/banner_new.png' ></c:url>" />
							</c:if>
							<c:if test="${teacherInfo.user.banner_id != null }">
								<img src="<c:url value='/resources/img/banner/b${teacherInfo.user.banner_id}.png' ></c:url>" />
							</c:if>
						</a>
					</div>
					<!--/.banner -->
				</div><!--/span-->
			