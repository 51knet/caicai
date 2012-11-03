<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css" media="screen">
	@import url("<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>");
	@import url("<c:url value="/resources/css/standard.css"/>");
	@import url("<c:url value="/resources/colorbox/colorbox.css"/>");
	@import url("<c:url value="/resources/css/style.css"/>");
	@import url("<c:url value="/resources/css/nivo-slider.css"/>");
</style>
	
<div class="divtopbg">
	<div style="margin: 0px auto; height: 640px; background: url(resources/img/home_bg.png) center -25px no-repeat;">
		<div class="course-counter">
			<div>
				<font>4930</font>
			</div>
			<div>
				<font>计数</font>
			</div>
		</div>
		<div
			style="width: 340px; height: 356px; left: 50%; position: absolute; top: 50%; border-radius: 5px; -webkit-border-radius: 5px; -moz-border-radius: 5px; background: #FFFFFF; margin: -300px 0 0 105px; text-align: center;">
			<div style="margin-top: 15px;">
				<div style="margin: 0px auto; height: 37px; width: 250px; background: url(resources/img/Userloginbg.png) no-repeat; line-height: 38px; font-family: '微软雅黑'; font-size: 14px; color: #FFFFFF;">用户登录</div>
			</div>
			<div style="margin-top: 18px;">
				<input type="text" style="font-family: '微软雅黑'; font-size: 14px; height: 30px; width: 250px;" value="请您输入用户名..." />
			</div>
			<div style="margin-top: 10px;">
				<input type="password" style="font-family: '微软雅黑'; font-size: 14px; height: 30px; width: 250px;" value="请您输入密码..." />
			</div>
			<div style="margin-top: 10px; padding-left: 15px; font-size: 12px; color: #666666;">
				<input name="RememberPassword" type="checkbox" value="" checked /> 记住密码 <a href="#" target="_blank">忘记密码</a>
			</div>
			<div style="margin-top: 30px; padding-left: 15px;">
				<button type="button">登 录</button>
				<button type="button" style="margin-left: 15px;">注 册</button>
			</div>
		</div>
	</div>
</div>