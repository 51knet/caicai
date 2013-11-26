<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
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
.row-fluid.custom .content{
	margin: 20px 40px;

}
</style>
<div class="row-fluid custom round">
	<div  class="row">
		<h4>我的需求>添加需求</h4>
	</div>
	<div class="content">
		<form action= '<c:url value="/admin/requirement/add"></c:url>'  method="post"  id="requirement_new_form"  >
			<div class="control-group" id="title">
				<div class="controls">
						需求标题： <input type="text" name="title" placeholder="标题"> <span class="help-inline"><form:errors path="title" /></span>
				</div>
			</div>
			<div class="control-group" id="requirType">
			需求类别：
					<select name="requirType" >
						<c:forEach items="${requirTypeList }" var="typeList" >
					  		<option value="${typeList.id }" >${typeList.typeName }</option>
					  </c:forEach>
					</select>
			</div>
			<div class="control-group" id="title">
				<div class="controls">
						截止日期： <input type="text" name="endTime" placeholder="截止日期" class="Wdate" onClick="WdatePicker()"> <span class="help-inline"><form:errors path="title" /></span>
				</div>
			</div>
			<div class="control-group" id="content">
				<div class="controls">
					<textarea  style="width:670px; height: 100px;"  name="content"  placeholder="需求内容"></textarea>
					<span class="help-inline"><form:errors path="content" /></span>
				</div>
			</div>
			<label style="clear: right;"></label>
			<button type="submit" class="btn btn-success">保存</button>&nbsp;&nbsp;
			<button type="reset" class="btn">取消</button>
		</form>
	</div>
</div>
<script type="text/javascript">
		$(document).ready(function() {
			$("#requirement_new_form").submit(function(){
				return checkEmptyAjax("requirement_new_form","new/createRequirementAjax");
			});
	    });
</script>