<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<style>
	.navbar_ul{
	padding-top:5px;
		list-style: none;
	}
	.navbar_ul >li{
		float: left;
		color: #254b93;
		font-weight: bold;
	}
	.navbar_ul >li >img{
		margin:0px 8px;
	}
</style>
<ul class="navbar_ul">
	<li><a href="<c:url value='/front'></c:url>">首页</a><img src="<c:url value='/resources/img/default/navline.png'></c:url>" ></img></li>
	<li><a href="<c:url value='/front/announcement/all'></c:url>">科技动态</a><img src="<c:url value='/resources/img/default/navline.png'></c:url>" ></img></li>
	<li><a href="<c:url value='/front/patent/all'></c:url>">专利信息</a><img src="<c:url value='/resources/img/default/navline.png'></c:url>" ></img></li>
	<li><a href="<c:url value='/front/technology/all'></c:url>">成果展示</a><img src="<c:url value='/resources/img/default/navline.png'></c:url>" ></img></li>
	<li><a href="<c:url value='/front/requirement/all'></c:url>">技术需求</a><img src="<c:url value='/resources/img/default/navline.png'></c:url>" ></img></li>
	<li><a href="<c:url value='/front/experts/all'></c:url>">专家信息</a><img src="<c:url value='/resources/img/default/navline.png'></c:url>" ></img></li>
	<li><a href="<c:url value='http://www.graphene-center.org/characterization/'></c:url>" target="_blank">石墨烯监测</a><img src="<c:url value='/resources/img/default/navline.png'></c:url>" ></img></li>
	<li><a href="<c:url value='/front/allies/all'></c:url>">联盟成员</a><img src="<c:url value='/resources/img/default/navline.png'></c:url>" ></img></li>
	<li><a href="<c:url value='/front/parks/all'></c:url>">各地政策</a><img src="<c:url value='/resources/img/default/navline.png'></c:url>" ></img></li>
	<li><a href="<c:url value='/front/fastupload'></c:url>"><img src="<c:url value='/resources/img/default/fastupload.png'></c:url>" ></img></a></li>
</ul>
			