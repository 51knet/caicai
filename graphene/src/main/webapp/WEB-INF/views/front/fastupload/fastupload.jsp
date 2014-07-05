<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<style>
.fstconts{
	width: 710px;
}
</style>
<div class="cont_block">
 	<div class="_titles">
 		<img  src="<c:url value='/resources/img/default/fasttitle.png' ></c:url>">
 	</div>
	<table style=" width: 95%; "  cellpadding="5" align="center">
		<thead>
			<tr>
				<th align="left">
						<h5><i class="icon-star"></i><i>必须填写项</i></h5>
 						<h5><i class="icon-star"></i><i>填写的邮箱为登录账号，请牢记。密码会自动发至邮箱</i></h5>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<div class="tabbable">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#patent_tab" data-toggle="tab">上传专利</a></li> 
					    	<li ><a href="#tech_tab" data-toggle="tab">上传成果</a></li>
					    	<li ><a href="#tech_require_tab" data-toggle="tab">技术需求</a></li> 
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="patent_tab">
								<div class="content row-fluid">
									<form   action= '<c:url value="/patent/add"></c:url>'  method="post" style="" id="patent_form" name="patent_post">
									<div class="span6" >
										<div class="control-group" id="patentNum">
											<div class="controls">
												<i class="icon-star"></i> 专利号码：<input type="text" name="patentNum"   placeholder="专利号码"  > <span class="help-inline"><form:errors path="patentNum" /></span>
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
									  					<c:forEach items="${pFieldList}" var="fieldList">
									  						<option value="${fieldList.fieldName }" >${fieldList.fieldName }</option>
									  					</c:forEach>
												</select>
										</div>
									
										<div class="control-group" id="patentName">
											<div class="controls">
												<i class="icon-star"></i> 专利名称：<input type="text" name="patentName"   placeholder="专利名称"   > <span class="help-inline"><form:errors path="patentName" /></span>
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
												<i class="icon-star"></i> 主分类号：<input type="text" name="mainClassNum"   placeholder="主分类号"   "> <span class="help-inline"><form:errors path="mainClassNum" /></span>
											</div>
										</div>
										
										<div class="control-group" id="classNum">
											<div class="controls">
												<i class="icon-star"></i> 分类号码：<input type="text" name="classNum"   placeholder="分类号码"  > <span class="help-inline"><form:errors path="classNum" /></span>
											</div>
										</div>
										
										<div class="control-group" id="applicant">
											<div class="controls">
												<i class="icon-star"></i> 申请人士：<input type="text" name="applicant"   placeholder="申请人士"  > <span class="help-inline"><form:errors path="applicant" /></span>
											</div>
										</div>
									
									</div>
									<div class="span6"   >
									
										<div class="control-group" id="patentInventer">
											<div class="controls">
												<i class="icon-star"></i> 发明人士：<input type="text" name="patentInventer"   placeholder="发明人士" > <span class="help-inline"><form:errors path="patentInventer" /></span>
											</div>
										</div>
										
										<div class="control-group" id="publishDate">
											<div class="controls">
												<i class="icon-star"></i> 公开日期：<input type="text" name="publishDate"   placeholder="公开日期" class="Wdate" onClick="WdatePicker()" > <span class="help-inline"><form:errors path="publishDate" /></span>
											</div>
										</div>
										
										<div class="control-group" id="publishNum">
											<div class="controls">
												<i class="icon-star"></i> 公开号码：<input type="text" name="publishNum"   placeholder="公开号码" > <span class="help-inline"><form:errors path="publishNum" /></span>
											</div>
										</div>
										
										<div class="control-group" id="applicationDate">
											<div class="controls">
												<i class="icon-star"></i> 申请日期：<input type="text" name="applicationDate"   placeholder="申请日期" class="Wdate" onClick="WdatePicker()" > <span class="help-inline"><form:errors path="applicationDate" /></span>
											</div>
										</div>
										
										<div class="control-group" id="agency">
											<div  class="controls">
												代 理 机 构 ：<input type="text" name="agency"   placeholder="代理机构"    > <span class="help-inline"><form:errors path="agency" /></span>
											</div>
										</div>
										
										<div class="control-group" id="agent">
											<div class="controls">
												代 理 人 名 ：<input type="text" name="agent"   placeholder="代理人名"   > <span class="help-inline"><form:errors path="agent" /></span>
											</div>
										</div>
										<div class="control-group" id="patentAddress">
											<div class="controls">
												<i class="icon-star"></i> 联系地址：<input type="text" name="patentAddress"   placeholder="联系地址"  > <span class="help-inline"><form:errors path="patentAddress" /></span>
											</div>
										</div>
										
									<div class="control-group" id="patentPhone">
										<div class="controls">
												<i class="icon-star"></i> 联系电话：<input type="text"   name="patentPhone" placeholder="联系电话" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="patentPhone" /></span>
										</div>
									</div>
									
									<div class="control-group" id="patentEmail">
										<div class="controls">
											<i class="icon-star"></i> 联系邮箱：<input type="text" name="patentEmail"   placeholder="联系邮箱"  > <span class="help-inline"><form:errors path="patentEmail" /></span>
										</div>
									</div>
									</div>
								
									<div class="control-group" id="summary">
										<div class="controls span11 " >
											<i class="icon-star"></i> 专利摘要：<br>
											<textarea   name="summary"  placeholder="专利摘要" class="fstconts"></textarea>
											<span class="help-inline"><form:errors path="summary" /></span>
										</div>
									</div>
									<div class="control-group">
									 	<div class="controls span11">
											<button   class="btn  btn-primary offset5">上传</button> <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
										</div>
									</div>	
									<br>
									</form>
								</div>
							</div>
							
							<div class="tab-pane " id="tech_tab">
								<div class="content row-fluid">
									<form  action= '<c:url value="/technology/add"></c:url>'  method="post"  id="tech_form" name="technology_post">
									<div class="span6" >
										<div class="control-group" id="techName">
											<div class="controls">
												<i class="icon-star"></i> 技术名称：<input type="text" name="techName"   placeholder="技术名称"  "> <span class="help-inline"><form:errors path="techName" /></span>
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
											<i class="icon-star"></i> 完成单位：<input type="text" name="department"   placeholder="完成单位"  > <span class="help-inline"><form:errors path="department" /></span>
										</div>
									</div>
									
									<div class="control-group" id="techInventer">
										<div class="controls">
											<i class="icon-star"></i> 所 有 人 ：<input type="text" name="techInventer"   placeholder="所有人"  > <span class="help-inline"><form:errors path="techInventer" /></span>
										</div>
									</div>
								
									<div class="control-group" id="techPhone">
										<div class="controls">
											<i class="icon-star"></i> 联系电话：<input type="text" name="techPhone"   placeholder="联系电话"    onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="techPhone" /></span>
										</div>
									</div>
									
									<div class="control-group" id="techEmail">
										<div class="controls">
											<i class="icon-star"></i> 联系邮箱：<input type="text" name="techEmail"   placeholder="联系邮箱"    > <span class="help-inline"><form:errors path="techEmail" /></span>
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
											<i class="icon-star"></i> 应用前景：<input type="text" name="applyArea"   placeholder="应用前景"  "> <span class="help-inline"><form:errors path="applyArea" /></span>
										</div>
									</div>
									
									<div class="control-group" id="cooperation">
										<div class="controls">
										<i class="icon-star"></i> 合作方式：<input type="text" name="cooperation"   placeholder="合作方式"  "> <span class="help-inline"><form:errors path="cooperation" /></span>
										</div>
									</div>
									
									<div class="control-group" id="demand">
										<div class="controls">
										<i class="icon-star"></i> 合作要求：<input type="text" name="demand"   placeholder="合作要求"  "> <span class="help-inline"><form:errors path="demand" /></span>
										</div>
									</div>
			
									</div>
								
									<div class="control-group" id="techContents">
										<div class="controls span11 " >
											<i class="icon-star"></i> 项目简介：<br>
											<textarea  class="fstconts"  name="techContents"  placeholder="项目简介"></textarea>
											<span class="help-inline"><form:errors path="techContents" /></span>
										</div>
									</div>
									
									<div class="control-group" id="advantage">
										<div class="controls span11 " >
											<i class="icon-star"></i> 产品优点：<br>
											<textarea  class="fstconts"  name="advantage"  placeholder="产品优点"></textarea>
											<span class="help-inline"><form:errors path="advantage" /></span>
										</div>
									</div>
									
									<div class="control-group" id="achievement">
										<div class="controls span11 " >
											<i class="icon-star"></i> 获奖情况：（如有专利，请注明专利名称、专利号、申请人）<br>
											<textarea  class="fstconts" name="achievement"  placeholder="获奖情况"></textarea>
											<span class="help-inline"><form:errors path="achievement" /></span>
										</div>
									</div>
									
									<div class="control-group">
									 	<div class="controls span11">
											<button   class="btn  btn-primary offset5">上传</button> <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
										</div>
									</div>	
									<br>
									</form>
								</div>
							</div>
							
							<div class="tab-pane " id="patent_require_tab">
								<div class="content row-fluid">
									<form  action= '<c:url value="/patentrequire/add"></c:url>'  method="post" style="" id="patent_require_form" name="patent_require_post">
									<div class="span6" >
									<div class="control-group" id="patentReqTitle">
										<div class="controls">
												<i class="icon-star"></i> 需求名称： <input type="text" name="patentReqTitle" placeholder="需求名称"   > <span class="help-inline"><form:errors path="patentReqTitle" /></span>
										</div>
									</div>	
									<div class="control-group" id="patentReqField">
										<div class="controls">
												<i class="icon-star"></i> 所属领域： <input type="text" name="patentReqField" placeholder="所属领域"   > <span class="help-inline"><form:errors path="patentReqField" /></span>
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
									<div class="control-group" id="patentReqCooperation">
										<div class="controls">
												<i class="icon-star"></i> 合作方式： <input type="text" name="patentReqCooperation" placeholder="合作方式"  > <span class="help-inline"><form:errors path="patentReqCooperation" /></span>
										</div>
									</div>	
									<div class="control-group" id="patentReqMoney">
										<div class="controls">
											<i class="icon-star"></i> 拟定资金： <input type="text" name="patentReqMoney" placeholder="拟定资金"  > <span class="help-inline"><form:errors path="patentReqMoney" /></span>
										</div>
									</div>
									</div>
									<div class="span6" >
									<div class="control-group" id="patentReqCompany">
										<div class="controls">
											<i class="icon-star"></i> 公司名称： <input type="text" name="patentReqCompany" placeholder="公司名称"  > <span class="help-inline"><form:errors path="patentReqCompany" /></span>
										</div>
									</div>
									<div class="control-group" id="patentReqContact">
										<div class="controls">
												<i class="icon-star"></i> 联系人士： <input type="text" name="patentReqContact" placeholder="联系人士"  > <span class="help-inline"><form:errors path="patentReqContact" /></span>
										</div>
									</div>
									
									<div class="control-group" id="patentReqPhone">
										<div class="controls">
												<i class="icon-star"></i> 联系电话： <input type="text"   name="patentReqPhone" placeholder="联系电话" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="patentReqPhone" /></span>
										</div>
									</div>
									<div class="control-group" id="patentReqFax">
										<div class="controls">
												<i class="icon-star"></i> 联系传真： <input type="text"   name="patentReqFax" placeholder="联系传真" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="patentReqFax" /></span>
										</div>
									</div>
									<div class="control-group" id="patentReqEmail">
										<div class="controls">
												<i class="icon-star"></i> 联系邮箱： <input type="text"   name="patentReqEmail" placeholder="联系邮箱" > <span class="help-inline"><form:errors path="patentReqEmail" /></span>
										</div>
									</div>
									</div>
									<div class="control-group" id="patentReqContent">
										<div class="controls span11">
											<i class="icon-star"></i>需求简介：<br>
											<textarea class="textareas"  name="patentReqContent"  placeholder="需求简介"></textarea>
											<span class="help-inline"><form:errors path="patentReqContent" /></span>
										</div>
									</div>
									<div class="control-group">
									 	<div class="controls span11">
											<button   class="btn  btn-primary offset5">上传</button> <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
										</div>
									</div>	
									<br>
									</form>
								</div>
							</div>
							
							<div class="tab-pane row-fluid" id="tech_require_tab">
								<div class="content row-fluid">
									<form  action= '<c:url value="/techrequire/add"></c:url>'  method="post"  id="tech_require_form" name="requirement_post" >
										<div class="span6" >
											<div class="control-group" id="techReqTitle">
												<div class="controls">
														<i class="icon-star"></i> 需求标题： <input type="text" name="techReqTitle" placeholder="需求标题"   > <span class="help-inline"><form:errors path="techReqTitle" /></span>
												</div>
											</div>
											
											<div class="control-group" id="techReqMoney">
												<div class="controls">
													<i class="icon-star"></i> 拟定资金： <input type="text" name="techReqMoney" placeholder="拟定资金"   > <span class="help-inline"><form:errors path="techReqMoney" /></span>
												</div>
											</div>
											
											<div class="control-group" id="techReqEndTime">
												<div class="controls">
														<i class="icon-star"></i> 截止日期： <input type="text"   name="techReqEndTime" placeholder="截止日期" class="Wdate" onClick="WdatePicker()"> <span class="help-inline"><form:errors path="techReqEndTime" /></span>
												</div>
											</div>
											
										<div class="control-group" id="techReqCompany">
											<div class="controls">
												<i class="icon-star"></i> 公司名称： <input type="text" name="techReqCompany" placeholder="公司名称"  > <span class="help-inline"><form:errors path="techReqCompany" /></span>
											</div>
										</div>
									
									</div>
									<div class="span6"   >
										<div class="control-group" id="techReqName">
											<div class="controls">
													<i class="icon-star"></i> 联系人士： <input type="text" name="techReqName" placeholder="联系人士"  > <span class="help-inline"><form:errors path="techReqName" /></span>
											</div>
										</div>
										
										<div class="control-group" id="techReqPhone">
											<div class="controls">
													<i class="icon-star"></i> 联系电话： <input type="text"   name="techReqPhone" placeholder="联系电话" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="techReqPhone" /></span>
											</div>
										</div>
										<div class="control-group" id="techReqAddress">
											<div class="controls">
													<i class="icon-star"></i> 联系地址： <input type="text"   name="techReqAddress" placeholder="联系地址"> <span class="help-inline"><form:errors path="techReqAddress" /></span>
											</div>
										</div>
										
										<div class="control-group" id="techReqEmail">
											<div class="controls">
													<i class="icon-star"></i> 联系邮箱： <input type="text"   name="techReqEmail" placeholder="联系邮箱"> <span class="help-inline"><form:errors path="techReqEmail" /></span>
											</div>
										</div>
									</div>
								
									<div class="control-group " id="techReqContent">
										<div class="controls " >
											<i class="icon-star"></i> 需求简介：<br>
											<textarea  class="fstconts"  name="techReqContent"  placeholder="需求简介"></textarea>
											<span class="help-inline"><form:errors path="techReqContent" /></span>
										</div>
									</div>
									<div class="control-group">
									 	<div class="controls span11">
											<button   class="btn  btn-primary offset5">上传</button> <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
										</div>
									</div>	
									<br>
									</form>
								</div>
							</div>							
						</div>
					</div>
				</td>
			</tr>
		</tbody>		 
	</table>
	<br>
 </div>
<script type="text/javascript">
		$(document).ready(function() {
			$("#patent_form").submit(function(){
				return checkEmptyAjax("patent_form","<c:url value='/fastupload/patentInfoAJAX'> </c:url>");
			});
			$("#tech_form").submit(function(){
				return checkEmptyAjax("tech_form","<c:url value='/fastupload/techInfoAJAX'> </c:url>");
			});

			$("#tech_require_form").submit(function(){
				return checkEmptyAjax("tech_require_form","<c:url value='/fastupload/techRequireAJAX '> </c:url>" );
			});
	    });
	
</script>


