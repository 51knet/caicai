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
 
$(document).ready(function(){
	$(".projectsbgcolor").mouseover(function(){
		  $(this).css("background-color","#f1f1f1");
	});
	$(".projectsbgcolor").mouseout(function(){
		  $(this).css("background-color","");
	});
});
//-->
</script>
<style>
.round{
	border-radius: 5px ;
}
.top_divs{
	width: 50%; float: left;padding: 10px 0px; height: 220px;
}
.top_divs>table{
	width: 100%; height:100%;  text-align: left;
}
.right_line{
	margin-left: 400px;margin-right: 20px;
}
.projects{
	margin: 0px 20px 30px 20px;;
	max-width: 96%;
	text-align: center;float: left;
}
.projects .detail{
	width: 290px; float:left; 
	margin: 20px 10px 0px 20px;
}
.projects .detail .info_div{
	 width:100%;  float: left;
	  background-color: #eeeeee; 
	  border: 1px solid #ccc; text-align: left;
}
.projects .detail .info_div .logopic{
	width: 30%; margin: 15px 10px 15px 15px;  float: left; 
}
.projects .detail .info_div .content_div{
	width: 60%; margin: 15px 0px 5px 0px; float: left; text-align: left;
}
.projects .detail .info_div .content_div .contlimit{
	max-width: 170px; max-height: 80px; margin-top:10px; overflow: hidden;
}
.projects .detail>a{
	width:100%;margin-top: 10px; border: 1px solid; font-size:15px; 
	float: left; clear: both; background-color: #486ca0;color: #fff;font-weight: bold; 
	padding: 7px 0px; text-decoration: none;
}
.projects .detail>a:hover{
	background-color: #6e8bb3;
}
.points{
	color: #6497ce;
	font-weight: bold;
}

.date{
	font-size: 12px;
	font-weight: normal;
}
._logo{
	width: 100px; 

}
.container.title.custom{
	margin: 40px 20px 0px 20px;
	max-width: 95%;
	background-repeat: no-repeat;
	padding-bottom:12px;
	border-bottom: 1.5px solid #ccc;
	background-color: #fff;
}


.container.title .innerRightTitle a{
	float: right;
	margin-top: 20px;
	margin-bottom: -20px;
}

.container.rzfh{
width: 100%; height:100px;text-align: left;float: left;
background-color: #bcc5ce; height: 400px;
}
.rzfh_btm{
	padding-left: 40px;
}
.rzjg_top{
	background: url("<c:url value='/resources/img/default/rzjg.png'></c:url>") no-repeat top left;
	padding: 20px 20px 22px 0px; text-align: right;margin-top: 10px;
}
.fhyq_top{
	background: url("<c:url value='/resources/img/default/fhyq.png'></c:url>") no-repeat top left;
	padding: 20px 20px 22px 0px;; text-align: right; margin-top: 10px;
}
.rzfh_logo{
width: 130px; border: 1px solid #ddd; 
padding: 2px 2px; margin: 10px 5px 10px 0px;
}

</style>
<br>
<div class="container projects" style="border-bottom: 1.5px solid #ccc; padding-bottom: 20px;">
		<div  class="projectsbgcolor top_divs">
			<table style="" cellpadding="5">
				<tbody>
					<tr>
						<td valign="middle" style="width: 180px; " align="center"><a href='<c:url value="/projects/about"></c:url>'><img  src="<c:url value='/resources/img/default/rzjs.png'></c:url> "></a></td>
						<td valign="top" ><img  src="<c:url value='/resources/img/default/rzjs-1.png'></c:url> "><br>
						
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div   class="projectsbgcolor top_divs">
			<table  cellpadding="5" >
				<tbody>
					<tr>
						<td valign="middle" style= "width: 180px;" align="center"><a href='<c:url value="/projects/guide"></c:url>'><img  src="<c:url value='/resources/img/default/xszd.png'></c:url> "></a></td>
						<td valign="top"  ><img  src="<c:url value='/resources/img/default/xszd-1.png'></c:url> "><br>
						
						</td>
					</tr>
				</tbody>
			</table>
		</div>
</div>

<div class="container projects"  >
		<div class="projectsbgcolor top_divs" >
			<table  cellpadding="5">
				<tbody>
					<tr>
						<td valign="middle" style="width: 180px;" align="center"><a href='<c:url value="/projects/rules"></c:url>'><img  src="<c:url value='/resources/img/default/rzgz.png'></c:url> "></a></td>
						<td valign="top" ><img  src="<c:url value='/resources/img/default/rzgz-1.png'></c:url> "><br>
						
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div   class="projectsbgcolor top_divs">
			<table  cellpadding="5" >
				<tbody>
					<tr>
						<td valign="middle" style="width: 180px;" align="center"> <a href="<c:url value='/fastupload'></c:url>"><img  src="<c:url value='/resources/img/default/ksfb.png'></c:url> "></a></td>
						<td valign="top" ><img  src="<c:url value='/resources/img/default/ksfb-1.png'></c:url> "><br>
						
						</td>
					</tr>
				</tbody>
			</table>
		</div>
</div>

<div class="container title custom"   >
		<div class="innerLeftTitle" >
			 <img  src="<c:url value='/resources/img/default/jjht.png'></c:url> ">
		</div>
		<div class="innerRightTitle " >
			<a href="<c:url value='/projects/list/uncomplete'></c:url>">  <img  src="<c:url value='/resources/img/default/more_blue.png'></c:url> "></a>
		</div>
 </div>
 
 <div class="container projects" >
 	<c:forEach items="${upList}" var="upList"  begin="0" end="2">
 			<div class="detail" >
 				<div  class="round info_div">
 					<div class=" logopic" >
 						<a href="<c:url value='/projects/view/${upList.id }'></c:url>"><img src="<c:url value='${p_url}${upList.logoPath }'></c:url> " class="_logo"></a>
 					</div>
 					<div class="content_div">
 						<div style="width: 170px;" id="contentlimit" ><a href="<c:url value='/projects/view/${upList.id }'></c:url>"><h4>${upList.projectName }</h4></a></div>
 						<div  class="date contlimit">
								${upList.content }
							</div>
 					</div>
					<div style="width: 90%; margin:10px 10px;  float: left;" class="points">
						<h4>${upList.location }<span style="margin-left: 20px;">${upList.industry }</span></h4>
					</div>
 				</div>
 				<a href="<c:url value='/projects/view/${upList.id }'></c:url>">
 					继 续 查 看 > >
 				</a>
 			</div>
	</c:forEach>
 </div>


 <div class="container title custom"  >
 <br>
		<div class="innerLeftTitle" >
			 <img  src="<c:url value='/resources/img/default/zxjr.png'></c:url> ">
		</div>
		<div class="innerRightTitle " >
			<a href="<c:url value='/projects/list/uncomplete' ></c:url>" >  <img  src="<c:url value='/resources/img/default/more_blue.png'></c:url> "></a>
		</div>
 </div>

 <div class="container projects">
 	<c:forEach items="${npList}" var="npList"  begin="0" end="2">
 			<div class="detail" >
 				<div  class="round info_div">
 					<div class=" logopic" >
 						<a href="<c:url value='/projects/view/${npList.id }'></c:url>"><img src="<c:url value='${p_url}${npList.logoPath }'></c:url> " class="_logo"></a>
 					</div>
 					<div class="content_div">
 						<div style="width: 190px;" id="contentlimit" ><a href="<c:url value='/projects/view/${npList.id }'></c:url>"><h4>${npList.projectName }</h4></a></div>
 						<div  class="date contlimit">
								${npList.content }
							</div>
 					</div>
					<div style="width: 90%; margin:10px 10px ; float: left;" class="points pName">
						<h4>${npList.location }<span style="margin-left: 20px;">${npList.industry }</span></h4>
					</div>
 				</div>
 				<a href="<c:url value='/projects/view/${npList.id }'></c:url>">
 					继 续 查 看 > >
 				</a>
 			</div>
	</c:forEach>
 </div>
 
 <div class="container  rzfh" >
	<div class="span6" >
		<div class="rzjg_top">
				<a  href='<c:url value="/rzfh/list/rzjg"></c:url>' ><img  src="<c:url value='/resources/img/default/more_white.png'></c:url> "  class=" rzfh_more"></a>
		</div>
		<div class="rzfh_btm">
			<c:forEach items="${rzList}" var="rzList"  begin="0" end="5">
 	  				<img alt="${rzList.name }"  src="<c:url value="${p_url }${rzList.logoPath }"></c:url>" class="rzfh_logo">
 	  		</c:forEach>
		</div>
	</div>
	
	<div class="span6">
		<div class="fhyq_top">
				<a  href='<c:url value="/rzfh/list/fhyq"></c:url>' ><img  src="<c:url value='/resources/img/default/more_white.png'></c:url> " class=" rzfh_more"></a>
		</div>
		<div class="rzfh_btm">
			<c:forEach items="${fhList}" var="fhList"  begin="0" end="5">
 	  				<img alt="${fhList.name }"  src="<c:url value="${p_url }${fhList.logoPath }"></c:url>" class="rzfh_logo">
 	  		</c:forEach>
		</div>
	</div>
	
 </div>

 <div class="container">
	<img src="<c:url value='/resources/img/default/hbpic.png'></c:url>  ">
 </div> 



