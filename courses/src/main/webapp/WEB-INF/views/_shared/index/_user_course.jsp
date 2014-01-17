<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
	.container .user-course{
		 background-image: url("<c:url value='/resources/img/default/carousel_bg.png'></c:url>");
		  height: 360px;
		  background-position: top center;
		  background-repeat: repeat-x;
		  text-align: center;
	}
	.bb{
		border-bottom: solid 1px #ccc;
	}
	
	.carouselbg{
		 background-image: url("<c:url value='/resources/img/default/maq_left_new.png'></c:url>");
		 height: 30px;
		 background-position: left top;
		 background-repeat: no-repeat;
		 /*background-color: #f6efe5;*/
	}
	.carouselbg .valign_center{
		margin-top: 6px;
	}
	.maq_left{
		font-size: 14px;
		color: #fff;
		font-weight: bold;
		text-align: center;
	}
	
	.maq_right_content{
		margin-right: 40px;
			font-size: 15px;
		font-weight: bold;
	}
	
	.typecss{
	text-align: left; width: 420px; 
	background-color: #718495; 
	margin-left:50px;
	}
	
	.fieldBg{
		background-image: url("<c:url value='/resources/img/default/fieldRightLine.png'></c:url>");
		background-position: right center;
		background-repeat: no-repeat;
	}
</style>
<script type="text/javascript">
window.onload = function (){
	var sc = new Scroll(document.getElementById("scroll"));  
};


function scroll(obj) {
	var tmp = (obj.scrollLeft)++;
	//当滚动条到达右边顶端时
	if (obj.scrollLeft==tmp) obj.innerHTML += obj.innerHTML;
	//当滚动条滚动了初始内容的宽度时滚动条回到最左端
	if (obj.scrollLeft>=obj.firstChild.offsetWidth) obj.scrollLeft=0;
}
setInterval("scroll(document.getElementById('scrollobj'))",30);
</script>
<div class="carouselbg" >
	<div class="row-fluid">
		<div class="span3 valign_center maq_left" style="width: 203px;">
			<div class="right dropdown" >
				<div class="dropdown-toggle"  data-toggle="dropdown"><a href="#" style="text-decoration: none; color: #fff;">专利类别</a></div>
				<div class="dropdown-menu typecss" style=" " role="menu" aria-labelledby="dropdownMenu">
						<table style="width:100%; " cellpadding="5" border="0">
							<c:forEach items="${patentFieldList }" var="patentField"  varStatus="status">
								<c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0 }">
									<tr >
								</c:if>
									<td align="center" <c:if test='${ (status.index+1) % 5 != 0}'>  class="fieldBg" </c:if> >
										<a href='<c:url value='/patent/${patentField.fieldName}/list' ></c:url> ' style="text-decoration: none;"><span style="color:#fff;">${patentField.fieldName}</span></a>
									</td>
								<c:if test="${status.count % 5 eq 0 || status.count eq 5}">
									</tr>
								</c:if>
							</c:forEach>
						</table>
				</div>
			</div>
		</div>
		<div class="span8 valign_center "  style="width:840px; margin-left: -30px;">
			 <DIV id="scrollobj" style="white-space:nowrap;overflow:hidden;width:840px;">
			 	<span class="maq_right_content">专利总数：${patentCount }</span><span class="maq_right_content">国内专利：${patentCNCount}</span><span class="maq_right_content">国外专利：${patentCount-patentCNCount}</span><span class="maq_right_content">专利成交总数：${patentTradeCount }</span><span class="maq_right_content">需求总数：${requirementCount }</span>
				<span class="maq_right_content">专家总数：${teacherCount }</span>
			 </DIV>
  		</div>
	</div>
</div>

