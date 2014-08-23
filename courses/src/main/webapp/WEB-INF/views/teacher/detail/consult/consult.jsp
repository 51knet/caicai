<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
var teacher_id = "${teacher_id}";
$(document).ready(function(){
	
	  $("#consult_comment").ajaxStart(function(){
	    $(this).html("<img src='<c:url value='/resources/img/default/loading.gif'></c:url>' >");
	  });
	   $("#consult_comment").load("<c:url value='/teacher/"+ teacher_id+"/consult/comment/list'></c:url>",{"pageNumber": "0"} , function(data){
		   $("#consult_comment").html(data);
	   });
	   
	   $("#consult_order").ajaxStart(function(){
		    $(this).html("<img src='<c:url value='/resources/img/default/loading.gif'></c:url>' >");
	  });
	   $("#consult_order").load("<c:url value='/teacher/"+ teacher_id+"/consult/order/list'></c:url>",{"pageNumber": "0"} , function(data){
		   $("#consult_order").html(data);
	   });
});
function ajaxPage( pageNumber){
	$.post("<c:url value='/teacher/"+ teacher_id+"/consult/comment/list'></c:url>",{"pageNumber": pageNumber} , function(data){
		   $("#consult_comment").html(data);
	  });

}

</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
}

.row-fluid.custom .row {
	margin: 20px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid.custom .content {
	margin: 20px 40px;
	width:675px;
	max-width:675px;
	word-wrap:break-word;	
}

.row-fluid.custom .content .bb{
	border-bottom: dashed #cccccc 1px;
}
.row-fluid.custom .content .limitTable{
	margin-top: 10px; 
	width:675px; 
	table-layout:fixed
}

.row-fluid.custom .content .limittable .limitTd{
	word-wrap:break-word; word-break:break-all;
}

</style>
<div class="row-fluid custom round ccc_border" >
<div class="row">
		<h4>电话咨询</h4>
	</div>
	<div class="content ">
		<table width="100%">
			<tr><td width="11%"><abbr title="所属高校"><b>所属高校：</b></abbr></td><td>${teacherInfo.teacher.college} </td></tr>
			<tr><td><abbr title="所属院系"><b>所属院系：</b></abbr></td><td>${teacherInfo.teacher.school}</td></tr>
			<tr><td><abbr title="研究方向"><b>研究方向：</b></abbr></td><td>${teacherInfo.teacher.major}</td></tr>
			<tr>
				<td  align="left" valign="top">
					<abbr title="研究方向"><b>教育背景：</b></abbr>
				</td>
				<td  align="left">
					<div id="resume_div" style=" max-height: 190px; overflow: hidden; ">
						<table  cellpadding="4" >
							<tbody>
								<c:forEach items="${eduInfo}" var="eduInfo">
									<tr>
									<td>
									${eduInfo.educationDesc}
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>

	<div class="content" style="border-top: 1px dashed #ccc;padding-top: 10px;">
		收费标准：150元/次(最长15分钟) 、200元/次(最长15分钟) <br><br>
		<a href="<c:url value="/teacher/${teacherInfo.id }/consult/cart"></c:url>" class="btn btn-info " >立即预约</a> 拨打 <b>68369338-806</b> ，助理帮您提交预约
	</div>
	<div class="content" style="border-top: 1px dashed #ccc;padding-top: 10px;">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#consult_comment_tab" data-toggle="tab"><h4>用户评价</h4></a></li>
				<li><a href="#order_tab" data-toggle="tab"><h4>最新订单</h4></a></li>
				<li><a href="#service_tab" data-toggle="tab"><h4>服务介绍</h4></a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active " id="consult_comment_tab">
					<div id="consult_comment">
	
					</div>
				</div>
				<div class="tab-pane " id="order_tab">
					<div id="consult_order">
	
					</div>
				</div>
				<div class="tab-pane" id="service_tab">
					<h5>异地企业业主的福音</h5>
					1.人地生疏，大大增加了咨询成功的难度，电话咨询让你足不出户就能很好的解决关键性问题，节省的不仅仅是车费、住宿费；
					2.可以同时获取到不同城市、不同权威专家的建议，高效的帮您获取到相对客观的建议。
				</div>
			</div>

		</div>
	</div>

</div>