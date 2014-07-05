<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<style>
.footer{
	 text-align:center;
	background:url(<c:url value="/resources/img/default/footerbg.jpg" />);
	 background-repeat:no-repeat; 
	 background-position: right top;
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
		<div  >
			<a href='<c:url value="#"></c:url>' >关于我们</a> | 
			<a href='<c:url value="#"></c:url>' >联系我们</a> | 
			<a href='<c:url value="#"></c:url>' >服务帮助</a> | 
			<a href='<c:url value="#"></c:url>' >法律声明</a>	
			<br><br>
			<div><p>指导单位：内蒙古石墨烯材料研究院、泰州石墨烯研究与检测平台</p></div>
		</div>
	</div>
	<div>
	</div>
</div>