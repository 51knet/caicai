<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	
</script>
<style>
body {
	background-color: #FFFFFF;
}

.container.course {
	text-align: left;
}

.container.course .row {
	margin-left: 0px;
}

.container.course.detail {
	width: 100%;
}

.container.course.detail.desc {
	margin-left: 70px;
	padding: 20px;
	width: 83.5%;
}

.container.course.detail.desc .content {
	margin-left: 40px;
}

.container.course.title {
	height: 240px;
	width: 1024px;
	margin-bottom: 15px;
	background-image: url('<c:url value='/ resources/ img/ default/ courseInfo.png '></c:url>');
	background-repeat: repeat-x;
	margin-top: 2px;
}

.container.course.content {
	width: 37%;
	text-align: left;
	float: left;
	margin-top: 32px;
	margin-left: 70px;
}

.nar {
	font-size: 16px;
	color: #adcc75;
	height: 40px;
}

.nar>h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 10px;
	padding-left: 88px;
}

.cont {
	margin-left: 90px;
	margin-bottom: 43px;
	padding-top: 20px;
}

.cont .top {
	font-size: 14px;
	background-color: #cccccc;
	padding: 3px;
}

.comments-container .content {
	border-bottom: 1px dashed;
	text-align: left;
	margin-left: -1px;
	width: 100%
}

.comments-container h5 {
	padding: 5px;
	text-align: left;
	padding-left: 88px;
}
</style>
	<div class="nar">
		<h4>支付页面,包括课程信息啥的，具体看毛妹子给的页面。这个页面是从/course/cart/view/{course_id}这个controller里面跳过来的，完了以后跳/course/pay/{order_id}这个controller就行。model里面放了courseId和orderId。</h4>
	</div>



