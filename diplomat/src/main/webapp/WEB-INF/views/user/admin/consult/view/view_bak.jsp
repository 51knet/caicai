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
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
.row-fluid .custom .user-row {
	color: #3d4f67;
}
h5{
	font-size: 15px;
	color: #3d4f67;
	padding-bottom:5px;
	border-bottom: dashed #cccccc 1px;
}
</style>
<script type="text/javascript">
		window.onload=function()//用window的onload事件，窗体加载完毕的时候
		{
			changebg();
		}

       function changebg() {       //为每个td设置获得焦点属性【也可以设置onclick属性】
           var maintable = document.getElementById("maintable");       //获取需要设置的表格
           var tds = maintable.getElementsByTagName("td");         //获取表格下的所有单元格
           for (var i = 0; i < tds.length; i++) {
               var td = tds[i];
               td.onfocus = getonfocus;
               td.style.cursor = "pointer";
           }
               
       }
       function getonfocus(){      //设置td的焦点事件，
           var maintable = document.getElementById("maintable");
           var tds = maintable.getElementsByTagName("td");
           var index = getindex(tds, this);           //返回响应事件的索引  用this代表触发此事件的元素
           alert("1");
          for (var i = 0; i < index+1; i++) {
               tds[i].style.color = "red";
           }
           for (var i = index+1; i < tds.length; i++) {
               tds[i].style.color = "black";
           }

       }
       function getindex(arr,element) {        //参数：数组，元素；返回兄弟元素的索引      
           for (var i = 0; i < arr.length; i++) {
               if (arr[i] == element) {
                   return i;
              }
           }
           return -1;
       }
  </script>
<c:url var="avatar_url" value="${order.teacher.photo_url}"></c:url>
<div class="row-fluid custom round">
	<div class="row <c:if test="${sessionUserInfo.role == 'user'}">user-row</c:if>">
		<h4>咨询订单>>订单详细</h4>
	</div>
	<div class="content">
		<h5>咨询专家信息</h5>
		<table width="100%"  cellpadding="8" >
			<tbody>
				<tr>
					<td width="100px;"><img width="100px" height="100px" src="${avatar_url}" ></td>
					<td valign="top">
					<h4> ${order.teacher.name }</h4>
					所属院系： ${teacherInfo.school}<br>
					研究方向： ${teacherInfo.major} <br>
					职称/职务：${teacherInfo.title}
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<h5>咨询详情</h5>
		<table class="table table-bordered" width="100%"  cellpadding="8" >
			<thead><tr style="background-color: #f1f1f1;"><th>咨询标题</th><th>联系电话</th><th>email</th><th>咨询日期</th></tr></thead>
			<tbody>
				<tr>
					<td >${order.title }</td>
					<td >${order.phone }</td>
					<td >${order.email }</td>
					<td >${order.date }</td>
				</tr>
				<tr  style="background-color: #f1f1f1;"><td colspan="5" ><b>咨询内容</b></td></tr>
				<tr><td colspan="5">${order.content }</td></tr>
			</tbody>
		</table>
		<br>
		<h5>我的评价</h5>
		<c:choose>
			<c:when test="${comment == null }">
		    <div class="control-group" >
				<div class="controls  " >
					<div style="float: left;"><i class="icon-star"></i> 请您打分：</div>	 
					<table border="0" cellpadding="0" cellspacing="0" id="maintable" style="float: left;">
				        <tr style=" font-size:20px;">
				            <td>☆</td><td>☆</td><td>☆</td><td>☆</td><td>☆</td>
				        </tr>
				       </table>
				</div>
			</div><br><br>
				<form action="<c:url value='/admin/consult/order/comment'></c:url>" method="post" id="comment_form" style="float: left;">
					<input type="hidden" name="order_id" value="${order.id }" >
					<input type="hidden" name="score" value="5" >
					<div class="control-group" id="content">
						<div class="controls  " >
							<i class="icon-star"></i> 评价内容：<textarea  style="width:500px;height:100px;"  name="content"  placeholder="评价内容"></textarea>
							<span class="help-inline"><form:errors path="summary" /></span>
						</div>
					</div>
					<button type="submit" class="btn btn-info ">提交</button>&nbsp;&nbsp;<a href="<c:url value="/admin/consult/order/list"></c:url>" class="btn  ">返回</a>
				</form>
			</c:when>
			<c:otherwise>
				<table class="table table-bordered" width="100%"  cellpadding="8" >
					<tbody>
						<tr>
							<td  style="background-color: #f1f1f1;" width="20%"><b>我的评分：</b></td>
							<td style="font-size: 17px; color: red;"><c:forEach begin="0" end="${comment.score-1 }">
								<b>☆</b>
								<!-- <i class="icon-star"></i> -->
							</c:forEach></td>
						</tr>
						<tr>
							<td style="background-color: #f1f1f1;"><b>我的评论：</b></td>
							<td>${comment.content }</td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>

</div>