<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div style="text-align: center;">
    
<form action="" method="post">
  <input type="text" name="email" placeholder="邮箱地址">
  <input type="text" name="psw" placeholder="密码">
  <span class="help-block"></span>
  <label class="checkbox inline">
	  <input type="checkbox" id="inlineCheckbox1" value="1"> 记住密码  &nbsp;&nbsp;<a href="#">忘记密码？</a>
	</label>
  <label style="clear: right;"></label>
  <button type="submit" class="btn">登录</button>
  <a href="#" class="btn">随便看看</a>
</form>
<hr/>
<a href="CommonRegister" class="btn">注册</a>

</div>