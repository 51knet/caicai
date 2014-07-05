<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.login_left{
	width:439px;
	height:40px;
	background-image: url("<c:url value='/resources/img/default/loginbg.png'></c:url>");
	background-position: bottom left;
	background-repeat: no-repeat;
}

 .banner_front.custom{
  position: relative;
   z-index: 1;
 }
 .btn.custom{
 	height: 24px; font-size:13px; padding: 2px 4px; margin-top: -1px;
 }

</style>

		<div class="banner_front custom" >
			<div style=" height: 40px; width: 100%;  position: absolute; z-index: 999;  color: #fff;">
				<div class="navbar custom">
					<div class="container-fluid">
						<ul class="nav pull-left">
							<li>
								<div class="login_left"> 
									<ul class="nav pull-left">
										<c:if test="${sessionUserInfo != null}">
											<li ><a href='<c:url value="/"></c:url>' class="navbar-link" style="color: #fff;">${sessionUserInfo.user.email }</a></li>
											<li id="fat-menu" class="dropdown" style="margin-right: -10px"><a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown"> 
												<img src="<c:url value='/resources/img/default/gear.png'></c:url>" style="margin-top: -3px;height:15px;"></img>
											</a>
												<ul class="dropdown-menu" role="menu" aria-labelledby="drop3">
													<li><a href='<c:url value="/admin"></c:url>'>后台管理</a></li>
													<li class="divider"></li>
													<c:if test="${sessionUserInfo.user.isadmin == 'yes' }">
													<li><a href='<c:url value="/admin/kefu"></c:url>'>管理员后台</a></li>
													<li class="divider"></li>
													</c:if>
													<li><a href='<c:url value="/front"></c:url>'>网站前台</a></li>
													<li class="divider"></li>
													<li><a href='<c:url value="/signout"></c:url>'>退出登录</a></li>
												</ul></li>
										</c:if>
										<c:if test="${sessionUserInfo == null}">
											<li style="padding: 7px 0px 0px 5px;">
												<form  class="form-inline" action="<c:url value='/front/signin'></c:url>"  method="post">
													  邮箱：<input class="input-small"  type="text" placeholder="邮箱" name="email" style="height: 14px; ">
													  密码：<input class="input-small" type="password" placeholder="密码" name="password" style="height:14px; ">
													<button type="submit"  class="btn btn-info custom" style="">登录</button> 
													<a  class="btn btn-info custom"  style="height: 20px;" href="<c:url value='/login'></c:url>">注册</a>
												</form>	
											</li>
										</c:if>
									</ul>
								</div>
							</li>
						</ul>
						<br>
					</div>
				</div>
			</div>
			<img src="<c:url value='/resources/img/banner/default_banner.jpg' ></c:url>" />
		</div>
		<!--/.banner --><!--/span-->
			