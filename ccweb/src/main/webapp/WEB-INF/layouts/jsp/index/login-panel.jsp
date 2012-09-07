<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div style="text-align: center;">
    <form:form action="signin" modelAttribute="loginForm" method="post">

  <input type="text" name="email" placeholder="邮箱地址">
  <span class="help-block"><form:errors path="email"></form:errors></span>
  <input type="text" name="password" placeholder="密码">
  
  <span class="help-block"><form:errors path="password"></form:errors></span>
  <label class="checkbox inline">
	  <input type="checkbox" name="rememberMe" id="inlineCheckbox1" value="1"> 记住密码  &nbsp;&nbsp;<a href="#">忘记密码？</a>
	</label>
  <label style="clear: right;"></label>
  <button type="submit" class="btn">登录</button>
  <a href="#" class="btn">随便看看</a>

</form:form>
<hr/>
<a href="/ccweb/registerpage" class="btn">注册</a>

</div>