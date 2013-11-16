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
		<h4>个人专利>添加专利</h4>
	</div>
	<div class="content">
		<form action= '<c:url value="/admin/patent/add"></c:url>'  method="post" style="margin-left:50px;" >
			<div class="control-group" id="patentNum">
				<div class="controls">
					<i class="icon-star"></i> 专利号码：<input type="text" name="patentNum"   placeholder="专利号码" required> <span class="help-inline"><form:errors path="patentNum" /></span>
				</div>
			</div>
			<div class="control-group" id="patentType">
				<i class="icon-star"></i>专利类型：
					<select name="patentType" >
						<option value="发明">发明</option>
						<option value="实用新型">实用新型</option>
						<option value="外观设计">外观设计</option>
					</select>
			</div>
			
			<div class="control-group" id="patentField">
				<i class="icon-star"></i>适用领域：
					<select name="patentField">
					   <c:forEach items="${pFieldList }" var="fieldList" >
					  	<option value="${fieldList.id }">${fieldList.fieldName}</option>
					  </c:forEach>
					</select>
			</div>
		
			<div class="control-group" id="patentName">
				<div class="controls">
					<i class="icon-star"></i> 专利名称：<input type="text" name="patentName"   placeholder="专利名称"  required> <span class="help-inline"><form:errors path="patentName" /></span>
				</div>
			</div>
			
				<div class="control-group" id="mainClassNum">
				<div class="controls">
					<i class="icon-star"></i> 主分类号：<input type="text" name="mainClassNum"   placeholder="主分类号"  required> <span class="help-inline"><form:errors path="mainClassNum" /></span>
				</div>
			</div>
			
				<div class="control-group" id="classNum">
				<div class="controls">
					<i class="icon-star"></i> 分类号码：<input type="text" name="classNum"   placeholder="分类号码"  required> <span class="help-inline"><form:errors path="classNum" /></span>
				</div>
			</div>
			
			<div class="control-group" id="applicant">
				<div class="controls">
					<i class="icon-star"></i> 申请人士：<input type="text" name="applicant"   placeholder="申请人士"  required> <span class="help-inline"><form:errors path="applicant" /></span>
				</div>
			</div>
			
			<div class="control-group" id="inventer">
				<div class="controls">
					<i class="icon-star"></i> 发明人士：<input type="text" name="inventer"   placeholder="发明人士" required> <span class="help-inline"><form:errors path="inventer" /></span>
				</div>
			</div>
			
			<div class="control-group" id="publishDate">
				<div class="controls">
					<i class="icon-star"></i> 公开日期：<input type="text" name="publishDate"   placeholder="公开日期" class="Wdate" onClick="WdatePicker()" required> <span class="help-inline"><form:errors path="publishDate" /></span>
				</div>
			</div>
			
			<div class="control-group" id="publishNum">
				<div class="controls">
					<i class="icon-star"></i>公开号码：<input type="text" name="publishNum"   placeholder="公开号码" required> <span class="help-inline"><form:errors path="publishNum" /></span>
				</div>
			</div>
			
			<div class="control-group" id="agency">
				<div class="controls">
					<i class="icon-star"></i> 代理机构：<input type="text" name="agency"   placeholder="代理机构" required> <span class="help-inline"><form:errors path="agency" /></span>
				</div>
			</div>
			
			<div class="control-group" id="agent">
				<div class="controls">
					<i class="icon-star"></i> 代理人士：<input type="text" name="agent"   placeholder="代理人士" required> <span class="help-inline"><form:errors path="agent" /></span>
				</div>
			</div>
			
			<div class="control-group" id="applicationDate">
				<div class="controls">
					<i class="icon-star"></i> 申请日期：<input type="text" name="applicationDate"   placeholder="申请日期" class="Wdate" onClick="WdatePicker()" required> <span class="help-inline"><form:errors path="applicationDate" /></span>
				</div>
			</div>
			
			<div class="control-group" id="address">
				<div class="controls">
					<i class="icon-star"></i> 联系地址：<input type="text" name="address"   placeholder="申请地址" required > <span class="help-inline"><form:errors path="address" /></span>
				</div>
			</div>
			
			<div class="control-group" id="summary">
				<div class="controls">
					<i class="icon-star"></i> 专利摘要：<br><input  name="summary"   placeholder="专利摘要" style="height: 80px; width: 320px;" required> <span class="help-inline"><form:errors path="summary" /></span>
				</div>
			</div>

			<label style="clear: right;"></label>
			<button type="submit" class="btn btn-success">发布</button>&nbsp;&nbsp;
			<button type="reset" class="btn">取消</button>
		</form>
	</div>
</div>
