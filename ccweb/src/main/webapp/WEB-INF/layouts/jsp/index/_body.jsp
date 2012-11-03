<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
body {
	max-width: 1600px;
	width: 100%;
}

.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}

.container-fluid .row-fluid {
	background: url(<c:url value="/resources/img/top_bg1.jpg" ></c:url>) repeat;
}
</style>

<div style="margin:0px auto; height:640px; background:url(<c:url value='/resources/img/home_bg.png'></c:url>) center -25px no-repeat;"></div>

<div
	style="width: 340px; height: 356px; left: 50%; position: absolute; top: 50%; border-radius: 5px; -webkit-border-radius: 5px; -moz-border-radius: 5px; background: #FFFFFF; margin: -300px 0 0 105px; text-align: center;">
    <form:form action="/ccweb/signin" modelAttribute="loginForm" method="post">

  <input type="text" name="email" placeholder="邮箱地址">
  <span class="help-block"><form:errors path="email"></form:errors></span>
  <input type="password" name="password" placeholder="密码">
  
  <span class="help-block"><form:errors path="password"></form:errors></span>
  <label class="checkbox inline">
	  <input type="checkbox" name="rememberMe" id="inlineCheckbox1" value="1"> 记住密码  &nbsp;&nbsp;<a href="#">忘记密码？</a>
	</label>
  <label style="clear: right;"></label>
  <button type="submit" class="btn">登录</button>
  <a href="#" class="btn">随便看看</a>

</form:form>

<hr/>
<a href="/ccweb/register" class="btn">注册</a>
</div>
