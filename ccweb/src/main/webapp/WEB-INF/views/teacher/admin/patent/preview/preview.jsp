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


</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>专利预览</h4>
	</div>
	<div class="content">
			<table width="100%" height="" border="1" cellpadding="5" cellspacing="0"  class="blue">
			  <tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">申请号</td>
			    <td width="190">1234</td>
			    <td width="103" align="center" bgcolor="#f3f3f3">申请日</td>
			    <td width="169">1234</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">名称</td>
			    <td colspan="3">1234</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">公开号</td>
			    <td>1234</td>
			    <td align="center" bgcolor="#f3f3f3">公开日</td>
			    <td>1234</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">主分类号</td>
			    <td>1234</td>
			    <td align="center" bgcolor="#f3f3f3">分案原申请号</td>
			    <td>1234</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">分类号</td>
			    <td colspan="3">1234</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">颁证日</td>
			    <td>1234</td>
			    <td align="center" bgcolor="#f3f3f3">优先权</td>
			    <td>1234</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">申请(专利权)人</td>
			    <td colspan="3">1234</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">地址</td>
			    <td colspan="3">1234</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">发明(设计)人</td>
			    <td>1234</td>
			    <td align="center" bgcolor="#f3f3f3">国际申请</td>
			    <td>1234</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">国际公告</td>
			    <td>1234</td>
			    <td align="center" bgcolor="#f3f3f3">进入国家日期</td>
			    <td>1234</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">专利代理机构</td>
			    <td>1234</td>
			    <td align="center" bgcolor="#f3f3f3">代理人</td>
			    <td>1234</td>
			  </tr>
			</table>
			<br>
			<div style="width: 100%; background-color: #f3f3f3;text-align: left;border:1px solid #b1cf75;"><span style="margin: 5px 5px;">摘要</span></div>
			<div style="width: 100%; text-align: left; margin:5px 5px;">
				16546765132465
			</div>
	</div>
	<div class="content" >
		<a href="javascript:close();"  class="btn btn-success">返回编辑页面</a>&nbsp;&nbsp;
		<a href='#'  class="btn btn-success">发布并返回</a>
	</div>
</div>