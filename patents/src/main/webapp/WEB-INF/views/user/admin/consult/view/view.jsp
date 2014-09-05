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

ul{list-style-type:none;}
#star{position:relative;width:600px;margin:10px auto;}
#star ul,#star span{float:left;display:inline;height:19px;line-height:19px;}
#star ul{margin:0 10px;}
#star li{float:left;width:24px;cursor:pointer;text-indent:-9999px;background:url("<c:url value='/resources/img/default/star.png'></c:url>") no-repeat;}
#star strong{color:#f60;padding-left:10px;}
#star li.on{background-position:0 -28px;}
#star p{position:absolute;top:20px;width:159px;height:60px;display:none;background:url(<c:url value='/resources/img/default/icon.gif'></c:url>) no-repeat;padding:7px 10px 0;}
#star p em{color:#f60;display:block;font-style:normal;}
</style>
<script type="text/javascript"> 
window.onload = function ()
{
	var oStar = document.getElementById("star");
	var aLi = oStar.getElementsByTagName("li");
	var oUl = oStar.getElementsByTagName("ul")[0];
	var oSpan = oStar.getElementsByTagName("span")[1];
	var oP = oStar.getElementsByTagName("p")[0];
	var i = iScore = iStar = 0;
	var aMsg = [
				"很不满意|差得太离谱",
				"不满意|不满意",
				"一般|基本解决问题",
				"满意|咨询效果好，还是挺满意的",
				"非常满意|咨询效果非常好，非常满意"
				]
	
	for (i = 1; i <= aLi.length; i++)
	{
		aLi[i - 1].index = i;
		//鼠标移过显示分数
		aLi[i - 1].onmouseover = function ()
		{
			fnPoint(this.index);
			//浮动层显示
			oP.style.display = "block";
			//计算浮动层位置
			oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 104 + "px";
			//匹配浮动层文字内容
			oP.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1]
		};
		//鼠标离开后恢复上次评分
		aLi[i - 1].onmouseout = function ()
		{
			fnPoint();
			//关闭浮动层
			oP.style.display = "none"
		};
		//点击后进行评分处理
		aLi[i - 1].onclick = function ()
		{
			iStar = this.index;
			oP.style.display = "none";
			oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")";
			$("#myScore").val(iStar);
		}
	}
	//评分处理
	function fnPoint(iArg)
	{
		//分数赋值
		iScore = iArg || iStar;
		for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";	
	}
};
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
					<div id="star">
    <span>点击星星打分</span>
    <ul>
        <li><a href="javascript:;">1</a></li>
        <li><a href="javascript:;">2</a></li>
        <li><a href="javascript:;">3</a></li>
        <li><a href="javascript:;">4</a></li>
        <li><a href="javascript:;">5</a></li>
    </ul>
    <span></span>
    <p></p>
</div>
				</div>
			</div><br><br>
				<form action="<c:url value='/admin/consult/order/comment'></c:url>" method="post" id="comment_form" style="float: left;">
					<input type="hidden" name="order_id" value="${order.id }" >
					<input type="hidden" name="score" value="5" id="myScore">
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
								<img src="<c:url value='/resources/img/default/star_1.png'></c:url>" >
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