<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom.price {
	margin-left: 6%;
	float: left;
}
.row-fluid.custom.price.power {
	margin-left: 10%;
	float: left;
	margin-top: 20px;
}
</style>
<div style="margin-top: 10px;">
	<a href="#">权限&价格</a>
	<hr />
	<form action="">
		<div class="row-fluid custom price">
			<ul>
				<li>价格设置</li>
			</ul>
			<span class="row-fluid custom price power"> 
				<input type="radio" value="免费" name="price">免费
			</span> 
			<br/>
			<span class="row-fluid custom price power"> 
				<input type="radio" value="付费" name="price">付费 ￥<input type="text" style="margin-left: 40px;">*<input type="text">%=￥4
			</span>
			<ul>
				<li>权限设置</li>
			</ul>
			<span class="row-fluid custom price power"> 
				<input type="radio" value="公开" name="power">公开（所有人可见）
			</span> 
			<span class="row-fluid custom price power"> 
				<input type="radio" value="密码访问 " name="power" >密码访问 <input type="text" value="设置密码" style="margin-left: 20px;">
			</span>
			<span class="row-fluid custom price power">
			 	<button class="btn btn-large btn-success">保存</button>
			</span>
		</div>
	</form>
</div>