<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<style>
.footer{
	 text-align:left;
	background-image: url(' <c:url value="/resources/img/default/footerbg.png" ></c:url> ' );
	 background-repeat:no-repeat; 
	 background-position: center top;
	 height:142px; 
	 color: #fff;
	 font-size: 12px;
	 line-height: 22px;
	
}
.footer a{
	color: #fff;
}
.footer a:hover{
	color: #cbcbcb;
	 text-decoration: none;
}

.footer .content{
	padding: 20px 0px 20px 20px;
}
</style>
<div class="footer" >
	<div class="row-fluid content">
		<div class="span5" >
			<a href='<c:url value="/about"></c:url>' >关于我们</a> | 
			<a href='<c:url value="/contact"></c:url>' >联系我们</a> | 
			<a href='<c:url value="#"></c:url>' >服务帮助</a> | 
			<a href='<c:url value="#"></c:url>' >法律声明</a>	
			<br><br>
			<div><p>版权所有 沪ICP备20110827 <br>
			CopyRight © 2006-2014</p></div>
		</div>
		<div class="span6" style=" width: 540px; text-align: right;">
			<div><img src="<c:url value='/resources/img/default/footer1.png'></c:url> " /></div>
			<div style="margin-top: 10px; padding-right: 10px;"> 
			<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=826619119&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:826619119:41" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></div>
		</div>
	</div>
	<div>
		<!--  <div style="display:none">
		<script language="javascript" type="text/javascript" src="http://js.users.51.la/15789894.js"></script>
		<noscript><a href="http://www.51.la/?15789894" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/15789894.asp" style="border:none" /></a></noscript>
		</div>  -->
	</div>
</div>