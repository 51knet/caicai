<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
<!--
 function  NoPost(){
 	return false;
 }
//-->
</script>
<style>
.titlebg{
	background-color:#3d4f65; 
	font-size: 14px;
	width: 100%;
	color: #fff;
	font-weight: bold;
}
 .selete_filter{
 	margin-top:10px;
	text-align: left;
}
.patent{
	 width: 100%;

}
.content> form{
	margin-left: 20px;
}

</style>
<div class="container title"  >
		<div class="innerLeftTitle " >
			 <a href="<c:url value="#"></c:url>">快速上传</a>
		</div>
 </div>
 	<h5><i class="icon-star"></i><i>必须填写项</i></h5>
 <div class="container patent">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#patent_tab" data-toggle="tab">上传专利</a></li> 
		    	<li ><a href="#tech_tab" data-toggle="tab">上传技术</a></li>
		    	<li ><a href="#patent_require_tab" data-toggle="tab">专利需求</a></li> 
		    	<li ><a href="#tech_require_tab" data-toggle="tab">技术需求</a></li> 
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="patent_tab">
					<div class="content row-fluid">
						<form onsubmit="return NoPost();" onsubmit="return NoPost();" action= '<c:url value="/patent/add"></c:url>'  method="post" style="" id="patent_form" name="patent_post">
						<div class="span6" >
							<div class="control-group" id="patentNum">
								<div class="controls">
									<i class="icon-star"></i> 专利号码：<input type="text" name="patentNum"   placeholder="专利号码" required > <span class="help-inline"><form:errors path="patentNum" /></span>
								</div>
							</div>
							<div class="control-group" id="patentType">
								<i class="icon-star"></i>专利类型：
									<select name="patentType" >
								  			<c:forEach items="${patentTypeList }" var="typeList">
								  				<option value="${typeList.id }" >${typeList.typeName }</option>
								  			</c:forEach>
									</select>
							</div>
							
							<div class="control-group" id="patentField">
								<i class="icon-star"></i>适用领域：
									<select name="patentField">
						  					<c:forEach items="${patentFieldList }" var="fieldList">
						  						<option value="${fieldList.fieldName }" >${fieldList.fieldName }</option>
						  					</c:forEach>
									</select>
							</div>
						
							<div class="control-group" id="patentName">
								<div class="controls">
									<i class="icon-star"></i> 专利名称：<input type="text" name="patentName"   placeholder="专利名称"  required > <span class="help-inline"><form:errors path="patentName" /></span>
								</div>
							</div>
							<div class="control-group"  > 
								<i class="icon-star"></i>专利范围：
									<select name="country">					  					
						  					<option value="0"  selected="selected">国内</option>
						  					<option value="1" >国外</option>
									</select>
							</div>
							<div class="control-group" id="mainClassNum">
								<div class="controls">
									<i class="icon-star"></i> 主分类号：<input type="text" name="mainClassNum"   placeholder="主分类号"  required "> <span class="help-inline"><form:errors path="mainClassNum" /></span>
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
						
						</div>
						<div class="span6"   >
						
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
									<i class="icon-star"></i> 公开号码：<input type="text" name="publishNum"   placeholder="公开号码" required> <span class="help-inline"><form:errors path="publishNum" /></span>
								</div>
							</div>
							
							<div class="control-group" id="applicationDate">
								<div class="controls">
									<i class="icon-star"></i> 申请日期：<input type="text" name="applicationDate"   placeholder="申请日期" class="Wdate" onClick="WdatePicker()" required> <span class="help-inline"><form:errors path="applicationDate" /></span>
								</div>
							</div>
							
							<div class="control-group" id="agency">
								<div  class="controls">
									<i class="icon-star"></i> 上传人名：<input type="text" name="agency"   placeholder="上传人"  required  > <span class="help-inline"><form:errors path="agency" /></span>
								</div>
							</div>
							
							<div class="control-group" id="agent">
								<div class="controls">
									<i class="icon-star"></i> 联系电话：<input type="text" name="agent"   placeholder="联系电话"  required > <span class="help-inline"><form:errors path="agent" /></span>
								</div>
							</div>
							
							<div class="control-group" id="address">
								<div class="controls">
									<i class="icon-star"></i> 联系地址：<input type="text" name="address"   placeholder="联系地址" required > <span class="help-inline"><form:errors path="address" /></span>
								</div>
							</div>
						</div>
					
						<div class="control-group" id="summary">
							<div class="controls span11 " >
								<i class="icon-star"></i> 专利摘要：<br>
								<textarea  style="width:680px;height:80px;"  name="summary"  placeholder="专利摘要"></textarea>
								<span class="help-inline"><form:errors path="summary" /></span>
							</div>
						</div>
						<div class="control-group">
						 	<div class="controls span11">
								<button   class="btn  btn-success offset5">上传</button> <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							</div>
						</div>	
						<br>
						</form>
					</div>
				</div>
				
				<div class="tab-pane " id="tech_tab">
					<div class="content row-fluid">
						<form onsubmit="return NoPost();" action= '<c:url value="/technology/add"></c:url>'  method="post"  id="technology_form" name="technology_post">
						<div class="span6" >
							<div class="control-group" id="techNum">
								<div class="controls">
									<i class="icon-star"></i> 项目名称：<input type="text" name="techName"   placeholder="项目名称" required "> <span class="help-inline"><form:errors path="techName" /></span>
								</div>
							</div>
							<div class="control-group"  > 
								<div class="controls" id="techField" >
								<i class="icon-star"></i> 所属领域：<select name="techField" >		  		
								  		<c:forEach items="${techField }" var="techField" >	
								  			<option value="${techField.value}" >${techField.value }</option>
									  </c:forEach>
									</select>
								</div>
							</div>
							
						<div class="control-group" id="department">
							<div class="controls">
								<i class="icon-star"></i> 完成单位：<input type="text" name="department"   placeholder="完成单位" required > <span class="help-inline"><form:errors path="department" /></span>
							</div>
						</div>
						
						<div class="control-group" id="department">
							<div class="controls">
								<i class="icon-star"></i> 所 有 人 ：<input type="text" name="inventer"   placeholder="所有人" required > <span class="help-inline"><form:errors path="inventer" /></span>
							</div>
						</div>
					
						<div class="control-group" id="phone">
							<div class="controls">
								<i class="icon-star"></i> 联系电话：<input type="text" name="phone"   placeholder="联系电话"  required " onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="phone" /></span>
							</div>
						</div>
							
					
						</div>
						
						<div class="span6"   >
							<div class="control-group" id="techType">
								<div class="controls">
									 项 目 类 型 ：<input type="text" name="techType"   placeholder="如：863计划"  > <span class="help-inline"><form:errors path="techType" /></span>
								</div>
							</div>
						
							<div class="control-group" id="maturity">
								<div class="controls">
									成 熟 程 度 ：<input type="text" name="maturity"   placeholder="成熟程度"   > <span class="help-inline"><form:errors path="maturity" /></span>
								</div>
							</div>
							
						<div class="control-group" id="progress">
							<div class="controls">
								 进 展 程 度 ：<input type="text" name="progress"   placeholder="进展程度" > <span class="help-inline"><form:errors path="progess" /></span>
							</div>
						</div>
							
						<div class="control-group" id="applyArea">
							<div class="controls">
								<i class="icon-star"></i> 应用前景：<input type="text" name="applyArea"   placeholder="应用前景" required "> <span class="help-inline"><form:errors path="applyArea" /></span>
							</div>
						</div>
						
						<div class="control-group" id="cooperation">
							<div class="controls">
							<i class="icon-star"></i> 合作方式：<input type="text" name="cooperation"   placeholder="合作方式" required "> <span class="help-inline"><form:errors path="cooperation" /></span>
							</div>
						</div>

						</div>
					
						<div class="control-group" id="contents">
							<div class="controls span11 " >
								<i class="icon-star"></i> 项目简介：<br>
								<textarea  style="width:680px;height:80px;"  name="contents"  placeholder="项目简介"></textarea>
								<span class="help-inline"><form:errors path="content" /></span>
							</div>
						</div>
						
						<div class="control-group" id="advantage">
							<div class="controls span11 " >
								<i class="icon-star"></i> 产品优点：<br>
								<textarea  style="width:680px;height:80px;"  name="advantage"  placeholder="产品优点"></textarea>
								<span class="help-inline"><form:errors path="advantage" /></span>
							</div>
						</div>
						
						<div class="control-group" id="achievement">
							<div class="controls span11 " >
								<i class="icon-star"></i> 获奖情况：（如有专利，请注明专利名称、专利号、申请人）<br>
								<textarea  style="width:680px;height:80px;"  name="achievement"  placeholder="获奖情况"></textarea>
								<span class="help-inline"><form:errors path="achievement" /></span>
							</div>
						</div>
						
						<div class="control-group">
						 	<div class="controls span11">
								<button   class="btn  btn-success offset5">上传</button> <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							</div>
						</div>	
						<br>
						</form>
					</div>
				</div>
				
				<div class="tab-pane " id="patent_require_tab">
					<div class="content row-fluid">
						<form onsubmit="return NoPost();" action= '<c:url value="/admin/patent/add"></c:url>'  method="post" style="" id="patent_form" name="patent_post">
						<div class="span6" >
						<div class="control-group" id="title">
							<div class="controls">
									<i class="icon-star"></i> 需求名称： <input type="text" name="title" placeholder="需求名称"  required > <span class="help-inline"><form:errors path="title" /></span>
							</div>
						</div>	
						<div class="control-group" id="requirementField">
							<div class="controls">
									<i class="icon-star"></i> 所属领域： <input type="text" name="requirementField" placeholder="所属领域"  required > <span class="help-inline"><form:errors path="requirementField" /></span>
							</div>
						</div>	
						<div class="control-group" id="patentType">
							<i class="icon-star"></i> 专利类型：
								<select name="patentType" >
							  			<c:forEach items="${patentTypeList }" var="typeList">
							  				<option value="${typeList.id }" >${typeList.typeName }</option>
							  			</c:forEach>
								</select>
						</div>
						<div class="control-group" id="cooperation">
							<div class="controls">
									<i class="icon-star"></i> 合作方式： <input type="text" name="cooperation" placeholder="合作方式"  required> <span class="help-inline"><form:errors path="cooperation" /></span>
							</div>
						</div>	
						<div class="control-group" id="money">
							<div class="controls">
								<i class="icon-star"></i> 拟定资金： <input type="text" name="money" placeholder="拟定资金"  required> <span class="help-inline"><form:errors path="money" /></span>
							</div>
						</div>
						</div>
						<div class="span6" >
						<div class="control-group" id="company">
							<div class="controls">
								<i class="icon-star"></i> 公司名称： <input type="text" name="company" placeholder="公司名称"  required> <span class="help-inline"><form:errors path="company" /></span>
							</div>
						</div>
						<div class="control-group" id="contact">
							<div class="controls">
									<i class="icon-star"></i> 联系人士： <input type="text" name="contact" placeholder="联系人士"  required> <span class="help-inline"><form:errors path="contact" /></span>
							</div>
						</div>
						
						<div class="control-group" id="phone">
							<div class="controls">
									<i class="icon-star"></i> 联系电话： <input type="text"  required name="phone" placeholder="联系电话" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="phone" /></span>
							</div>
						</div>
						<div class="control-group" id="fax">
							<div class="controls">
									<i class="icon-star"></i> 联系传真： <input type="text"  required name="fax" placeholder="联系传真" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="fax" /></span>
							</div>
						</div>
						<div class="control-group" id="email">
							<div class="controls">
									<i class="icon-star"></i> 联系邮箱： <input type="text"  required name="email" placeholder="联系邮箱" > <span class="help-inline"><form:errors path="email" /></span>
							</div>
						</div>
						</div>
						<div class="control-group" id="content">
							<div class="controls span11">
								<i class="icon-star"></i>需求简介：<br>
								<textarea  style="width:680px;height:80px;"  name="content"  placeholder="需求简介"></textarea>
								<span class="help-inline"><form:errors path="content" /></span>
							</div>
						</div>
						<div class="control-group">
						 	<div class="controls span11">
								<button   class="btn  btn-success offset5">上传</button> <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							</div>
						</div>	
						<br>
						</form>
					</div>
				</div>
				
				<div class="tab-pane row-fluid" id="tech_require_tab">
					<div class="content row-fluid">
						<form onsubmit="return NoPost();" action= '<c:url value="/admin/requirement/add"></c:url>'  method="post"  id="requirement_new_form" name="requirement_post" >
						<div class="span6" >
								<div class="control-group" id="title">
									<div class="controls">
											<i class="icon-star"></i> 需求标题： <input type="text" name="title" placeholder="标题"   required> <span class="help-inline"><form:errors path="title" /></span>
									</div>
								</div>
								
								<div class="control-group" id="money">
									<div class="controls">
										<i class="icon-star"></i> 拟定资金： <input type="text" name="money" placeholder="拟定资金"   required> <span class="help-inline"><form:errors path="title" /></span>
									</div>
								</div>
								
								<div class="control-group" id="title">
									<div class="controls">
											<i class="icon-star"></i> 截止日期： <input type="text"  required name="endTime" placeholder="截止日期" class="Wdate" onClick="WdatePicker()"> <span class="help-inline"><form:errors path="title" /></span>
									</div>
								</div>
								
							<div class="control-group" id="company">
								<div class="controls">
									<i class="icon-star"></i> 公司名称： <input type="text" name="company" placeholder="公司名称"  required> <span class="help-inline"><form:errors path="company" /></span>
								</div>
							</div>
						
						</div>
						<div class="span6"   >
							<div class="control-group" id="name">
								<div class="controls">
										<i class="icon-star"></i> 联系人士： <input type="text" name="name" placeholder="联系人士"  required> <span class="help-inline"><form:errors path="name" /></span>
								</div>
							</div>
							
							<div class="control-group" id="phone">
								<div class="controls">
										<i class="icon-star"></i> 联系电话： <input type="text"  required name="phone" placeholder="联系电话" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="phone" /></span>
								</div>
							</div>
							<div class="control-group" id="address">
								<div class="controls">
										<i class="icon-star"></i> 联系地址： <input type="text"  required name="address" placeholder="联系地址"> <span class="help-inline"><form:errors path="address" /></span>
								</div>
							</div>
						</div>
					
						<div class="control-group" id="content">
							<div class="controls span11 " >
								<i class="icon-star"></i> 需求简介：<br>
								<textarea  style="width:680px;height:80px;"  name="content"  placeholder="需求简介"></textarea>
								<span class="help-inline"><form:errors path="content" /></span>
							</div>
						</div>
						<div class="control-group">
						 	<div class="controls span11">
								<button   class="btn  btn-success offset5">上传</button> <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							</div>
						</div>	
						<br>
						</form>
					</div>
				</div>
			</div>
		</div>
		<br>
 </div>



