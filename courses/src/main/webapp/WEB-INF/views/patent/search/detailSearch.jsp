<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>

.patent{
	 width: 1024px;
	 margin: 10px 40px;
}
.patent .top{
	font-weight: bold;
	font-size: 17px;
	color: #3f5517;
	padding:10px 0px 8px 10px;
	width: 90%;
}

.date{
	margin-left: 40px; font-size: 12px; color: #666;
	font-weight: normal;
}

.patent .bottom{
	padding:10px 10px;
	width: 90%;
}
</style>
<div class="container title"  >
		<div class="innerLeftTitle">详细搜索</div>
 </div>
 <br>
 	<form action= '<c:url value="/search/patent/detail/list"></c:url>'  method="post" style="margin-left:50px;" id="patent_form" name="patent_post">
 <div class="container patent row-fluid">
			<div class="span5">
				<div class="control-group" id="patentNum">
					<div class="controls">
						专利号码：<input type="text" name="patentNum"   placeholder="专利号码"  > 
					</div>
				</div>
				<div class="control-group" id="patentType">
					专利类型：<select name="patentType" >
							<c:forEach items="${patentTypeList }" var="typeList">
								<option value="${typeList.id }" >${typeList.typeName }</option>
							</c:forEach>
						</select>
				</div>
				
				<div class="control-group" id="patentField">
					适用领域：<select name="patentField">
							<c:forEach items="${fieldList }" var="fieldList">
								<option value="${ fieldList.fieldName  }"   >${fieldList.fieldName }</option>
							</c:forEach>
						</select>
				</div>
			
				<div class="control-group" id="patentName">
					<div class="controls">
						 专利名称：<input type="text" name="patentName"   placeholder="专利名称"   >
					</div>
				</div>
				
				<div class="control-group" id="mainClassNum">
					<div class="controls">
						 主分类号：<input type="text" name="mainClassNum"   placeholder="主分类号"   >
					</div>
				</div>
			</div>
			
			<div class="span5">
				<div class="control-group" id="classNum">
					<div class="controls">
						 分类号码：<input type="text" name="classNum"   placeholder="分类号码"  > 
					</div>
				</div>
				
				<div class="control-group" id="applicant">
					<div class="controls">
						 申请人士：<input type="text" name="applicant"   placeholder="申请人士"  >
					</div>
				</div>
				
				<div class="control-group" id="inventer">
					<div class="controls">
						 发明人士：<input type="text" name="inventer"   placeholder="发明人士" >
					</div>
				</div>
				
				<div class="control-group" id="publishNum">
					<div class="controls">
						 公开号码：<input type="text" name="publishNum"   placeholder="公开号码" > 
					</div>
				</div>
			</div>
 </div>
 <div class="container patent row-fluid offset4">
 				<button type="submit" class="btn-large btn-success">搜索</button>&nbsp;&nbsp;
				<button type="reset" class="btn">重置</button>
 </div>
				
</form>