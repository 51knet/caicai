<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
}

.row-fluid.custom .row > h3 {
	color: #000;
	border-bottom: solid #cccccc 0px;
	padding-bottom: 4px;
	margin: 0px 0px 0px 0px;
	background-image: url('<c:url value="/resources/img/default/faqline.png"></c:url>');
	background-position: left bottom;
	background-repeat: no-repeat;
}

.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 4px;
	margin: 30px 0px 0px 0px;
}

.row-fluid.custom .row {
	margin: 0px 40px 0px 40px;
}

.row-fluid.custom .row .content-bottom {
	line-height:23px;
	margin: 10px 0px 0px 0px;
}
.row-fluid.custom .row .content-top {
	line-height:28px;
	margin: 10px 0px 0px 0px;
}


</style>

<div class="row-fluid custom round">
	<div class="row">
		<h3>平台简介</h3>
	</div>
	<div class="row">
		<table style=" width: 100%"  cellpadding="5" class="content-top" >
			<tbody>
				<tr><td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					知识网是由多名国内外知名大学教授发起创办，专注分享优质教育资源的智慧云平台。教师可以轻松创建专属的个人教学平台，
					与学生互动，满足教学所需，同时可以与相关领域的学者交流；普通用户可以获取所需知识和技能，也可以搜索相关领域的名师名教。
					企业用户可以建立在线学习平台，满足所有用户所需。知识网所提供的在线实时互动教学系统，更可以让老师直接和学生们在线做一
					对一或一对多教学，让学习真真变的社会化、普及化。
				</td></tr>
			</tbody>		 
		</table>
	</div>
	<br><br>
	<div class="row">
		<h4>平台特色</h4>
	</div>	 
	<div class="row">
		<table style=" width: 100%"  cellpadding="6" class="content-bottom" >
			<tbody>
				<tr><td colspan="4">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					“知识网”以知识为核心，集社交网络、在线教育为一体，成为用户量最大、粘性最强的社会化服务门户。主要为用户提供服务包括：
				</td></tr>
				<tr>
					<td width="15%" align="left" valign="middle"><img src='<c:url value="/resources/img/default/faqbook.png"></c:url>' /></td>
					<td width="35%" align="left" valign="top">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					学习：平台凝聚着数以万计教师的智慧，我们为教师提供互动教学工具，在平台上积累海量学习资源，凝聚最具智慧人的知识，实现用户之间相互学习、知识共享。让优质资源走出名校围墙。</td>
					<td width="15%" align="left" valign="middle"><img src='<c:url value="/resources/img/default/faqbag.png"></c:url>' /></td>
					<td width="35%" align="left"  valign="top">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					社交：通过教师这根主线，编织师生、同学、校友、同门复杂社会网络。同学之间交流、教师之间组织会议、校友之间活动等，实现虚拟现实关系的真实映射。</td>
				</tr>
			</tbody>		 
		</table>
	</div>
</div>
