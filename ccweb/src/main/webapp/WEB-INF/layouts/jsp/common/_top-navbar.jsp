<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="navbar custom navbar-fixed-top">
	<div class="container-fluid" style="text-align: center; height: 39px; width: 1024px; margin: 0 auto;">
		<ul class="nav pull-left">
			<li><a id="logo" href='<c:url value="/courses"></c:url>'></a></li>
		</ul>
		<ul class="nav pull-right">
			<li>
				<form class="navbar-form" style="margin-right:50px;" action="<c:url value="/searchUser"></c:url>"  method="post">
				<select class="form-control" style="width: 100px;" name="types">
					  <option value="all">全部</option>
					  <option value="users">找人</option>
					  <option value="courses">找课程</option>
				</select> <input type="text" name="searchParam" class="span5" placeholder="输入姓名搜索"  value="${searchParam }">
		         &nbsp;&nbsp;<button type="submit" class="btn" style="margin-top: 4px; font-family:Arial,'Microsoft YaHei'; color: #808080; ">搜 索</button>
		       </form>
			</li>
			<li><a href='<c:url value="/courses"></c:url>'><img class="courselogo" src="<c:url value='/resources/img/default/courselogo.png'></c:url>"> <img class="goto"
					src="<c:url value='/resources/img/default/index/goto.png'></c:url>"></a></li>
			<c:if test="${sessionUserInfo != null}">
				<li style="margin-right: -12px;"><a href='<c:url value="/patentAdmin"></c:url>' class="navbar-link">${sessionUserInfo.user.email }</a></li>
				<li id="fat-menu" class="dropdown" style="margin-right: -10px"><a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown"> <img src="<c:url value='/resources/img/default/gear.png'></c:url>"
						style="margin-top: -3px;height:15px;"></img>
				</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop3">
						<li><a href='<c:url value="/patentAdmin"></c:url>'>后台管理</a></li>
						<li class="divider"></li>
						<li>
							<c:if test="${sessionUserInfo.role != 'user'}">
								<a href='<c:url value="/patentAdmin/details?active=photo"></c:url>'>个人中心</a>
							</c:if>
							<c:if test="${sessionUserInfo.role == 'user'}">
								<a href='<c:url value="/patentAdmin/details?active=photo"></c:url>'>个人中心</a>
							</c:if>
						</li>
						<li class="divider"></li>
						<li><a href='<c:url value="/courses"></c:url>'>发现知识</a></li>
						<li class="divider"></li>
						<li><a href='<c:url value="/signout"></c:url>'>退出登录</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessionUserInfo == null}">
				<li ><div style="width: 30px;"></div></li>
			</c:if>
		</ul>
		<br>
	</div>
</div>
