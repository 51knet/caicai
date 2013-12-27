<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
.marketing .leftContainer{
	width: 750px; color: #353535;
 	font-family:Arial,'Microsoft YaHei';
 	float: left;
}
.marketing  .leftContainer .leftTitle {
		float: left;width: 100%;
		 background-image: url("<c:url value='/resources/img/default/leftTitleBg.png'></c:url>");
		 background-position:left top;background-repeat: no-repeat;
	  	 height: 37px;
	  
	}
.marketing  .leftContainer .leftTitle .left{
		font-size:20px; color: #fffdfa;
		line-height: 35px; font-weight:bold;
		margin-left: 70px; width: 110px;
		float: left;
	}
	
.marketing  .leftContainer .leftTitle .right{
		margin-left: 0px; width: 90px;
		float: left; background-color: #5f7e20;
		text-align: center; color: #fffdfa;
		font-size: 15px; line-height: 37px;
		font-weight: bold;
	}
	
	.marketing  .leftContainer .leftContent {
		float: left; width: 100%;
		background-color: #edf1e2;
		padding-bottom: 15px;
	}
	.marketing .leftContainer .leftContent >div{
		margin: 15px 20px 15px 70px;
		
	}
	
	.marketing .rightContainer{
		width: 248px; margin-left: 24px;
		float: left;
	}
	
.marketing .rightContainer .rightTitle {
		font-size:18px;background-color: #a7c676;
		color: #fffdfa;font-weight:bold;
	 	 float: left;background-color: #a7c676;
	 	 width: 100%;height: 37px;
		  background-image: url("<c:url value='/resources/img/default/rightTitleBg.png'></c:url>");
	 	 background-repeat: no-repeat;
	 	 border-right: 1px solid #e0e7c8;
	 	 border-left: 1px solid #e0e7c8;

	}
	
	.marketing .rightContainer .rightTitle >div{
		padding: 7px 20px;
	}
	
	.marketing .rightContainer .rightContent {
		float: left; width: 100%;
	  height: 329px;border: 1px solid #e0e7c8;
	  border-top: 0px;
	  
	}
	.marketing .rightContainer .rightContent >div{
		padding: 10px 20px;
	}
	
	.require{
		width: 1024px;
		height: 300px;
		margin: 0px 0px 15px 0px;
	}
	
	.require  .top{
		width: 100%;
		background-image: url("<c:url value='/resources/img/default/require_topbg.png'></c:url>");
		background-position: left top;background-repeat: no-repeat;
		background-color: #a7c676;height: 37px;
		font-size: 18px;color: #fff;font-weight: bold;
		
	}
	
	
	.require  .top >span{
		line-height:35px;
		padding: 7px 70px;
	}
	.require .leftDiv{
		width: 500px;
		height: 200px;
		float: left;
	
	}
	
	.require .leftDiv .leftContent{
		padding:15px 70px;
	
	}
	
	.require .centerDiv{
		width: 24px;
		float: left;
	}
	.require .centerDiv .centerContent{
		margin-top:55px;
		height: 290px;
		background-image: url("<c:url value='/resources/img/default/centerline.png'></c:url>");
		background-position: center top;
		background-repeat: repeat-y;
	}
	.require .rightDiv{
		width: 500px;
		height: 200px;
		float: left;
	}
	
	.require .rightDiv .rightContent{
		padding:15px 70px;
	
	}
	
	ul{
		list-style-type: none;
	}
	li{
		line-height:28px;
	}

	.fieldBg{
		background-image: url("<c:url value='/resources/img/default/filedRightLine.png'></c:url>");
		background-position: right center;
		background-repeat: no-repeat;
	}
	
	.actitle{
		 line-height:20px; position: absolute; padding:3px 5px; z-index: 100; margin-top:-5px;  font-size:13px;   border: 1px solid #ccc; background-color: #fff; display: none; max-width: 300px; max-height: 60px;
	}
</style>
<div class="container marketing">
	  <div class="leftContainer" >
	  	<div class="leftTitle ">
			<div class="left">热门资源</div>
			<div class="right dropdown" >
				<div class="dropdown-toggle"  data-toggle="dropdown"><a href="#" style="text-decoration: none; color: #fff;">专利</a></div>
				<div class="dropdown-menu" style="text-align: left; width: 500px; background-color: #5f7e20; margin-top: -1px;" role="menu" aria-labelledby="dropdownMenu">
						<table style="width:100%; " cellpadding="0" border="0">
							<c:forEach items="${patentFieldList }" var="patentField"  varStatus="status">
								<c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0 }">
									<tr >
								</c:if>
									<td align="center" <c:if test='${ (status.index+1) % 5 != 0}'> class="fieldBg "</c:if>>
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
	  	<div class="leftContent ">
	  		<div >
		  		<div class="span6">
		  			<ul>
		  			<!-- <li class="bb">国内专利</li> -->	
						<c:forEach items="${patentList }" var="patentList" begin="0" step="2" end="18"  >
							<li ><div id="contentlimit" style="width:310px; float: left;">
							<a class="a_color_ccc"  href="<c:url value="/patent/view?id=${patentList.patentNum }"></c:url>"><img src="<c:url value='/resources/img/default/icon.png'></c:url>" >  ${patentList.patentName }</a>
							</div></li>
						</c:forEach>
					</ul>
		  		</div>
		  		<div class="span6" >
		  			<ul>
		  		<!-- <li class="bb">国际专利</li> -->	
						<c:forEach items="${patentList }" var="patentList" begin="1" step="2" end="19">
							<li   ><div id="contentlimit" style="width:310px; float: left;">
								<a class="a_color_ccc"  href="<c:url value="/patent/view?id=${patentList.patentNum }"></c:url>"><img src="<c:url value='/resources/img/default/icon.png'></c:url>" >  ${patentList.patentName }</a>
							</div></li>
						</c:forEach>
					</ul>
					<br>
					<a class="a_color_green"  style="text-decoration: none; float: right;" href="<c:url value="/patent/list"></c:url>" >更多>></a>
		  		</div>
	  		</div>
	  	</div>	
	  </div>
	  <div class="rightContainer"  >
	  	<div class="rightTitle">
	  		<div>活动动态</div>
	  	</div>
	  	<div class="rightContent">
	  		<div>
	  			<c:if test="${activityList == null }">
					无内容
				</c:if>
				<ul>
					<c:forEach items="${activityList }" var="activityList" begin="0"  end="9">
						<li >
							<div id="contentlimit" class=" bLine_dash activities_title"  style="width:200px; position: relative;">
									<a class="a_color_ccc"  href="<c:url value="/activity/view/${ activityList.id}"></c:url>"><img src="<c:url value='/resources/img/default/icon.png'></c:url>" >${activityList.title }</a>
							</div>
							<div class="actitle">${activityList.title }</div>
						</li>
					</c:forEach>
				</ul>
					<br>
				<a class="a_color_green"  style="text-decoration: none; float: right;" href="<c:url value="/activity/list"></c:url>" >更多>></a>
	  		</div>
	  	</div>
	  </div>
</div>
<br>

<div class="container require" >
 	<div class="leftDiv">
 		<div class="top">
 			<span>专利需求</span>
 		</div>
 		<div class="leftContent">
			<ul>
					<c:forEach items="${patentRequire }" var="patentRequire"  begin="0" end="9">
					<li ><div id="contentlimit" style="width:360px; float: left;" class="bLine_dash">
					<img src="<c:url value='/resources/img/default/icon.png'></c:url>" >  <a class="a_color_ccc"  href="<c:url value="/requirement/patent/view/${ patentRequire.id}"></c:url>">${patentRequire.title }</a>
					</div></li>
				</c:forEach>
			</ul>
			<br>
			<a class="a_color_green"  style="text-decoration: none; float: right;" href="<c:url value="/requirement/patent/list"></c:url>" >更多>></a>
			<br>
 		</div>
 	</div>
 	<div class="centerDiv"><div class="centerContent"></div></div>
 	<div class="rightDiv">
 		<div class="top">
 			<span>技术需求</span>
 		</div>
	 	<div class="rightContent">
 			<ul>
				<c:forEach items="${technologyRequire}" var="technologyRequire"  begin="0"  end="9">
					<li ><div id="contentlimit" style="width:360px; float: left;" class="bLine_dash">
					<a class="a_color_ccc"  href="<c:url value="/requirement/technology/view/${ technologyRequire.id}"></c:url>"><img src="<c:url value='/resources/img/default/icon.png'></c:url>" >  ${technologyRequire.title }</a>
					</div></li>
				</c:forEach>
			</ul>
			<br>
			<a class="a_color_green"  style="text-decoration: none; float: right;" href="<c:url value="/requirement/technology/list"></c:url>" >更多>></a>	
	 	</div>
 	</div>
</div>

<div class="container teacher" style="margin-top: 80px;">
	  <div class="container title">
		 <table >
		 	<tr>
		 		<td width="15%" align="center"><h4 style="color: #fff;">推荐专家 </h4></td>
		 		<td align="right"><span class="count">共${teacherCount }名专家</span>
		 		 <a  href='<c:url value="/teacher/list"></c:url>'  >全部专家</a></td>
		 	</tr>
		 </table>
	 </div>
  	<div class="bgimg">
	    <c:if test="${fn:length(teacherLists)==0}">
		       <div class="teacherInfo">
		       	<h3>暂无数据</h3>
		       </div>
	 	 </c:if>
	 	 <div class="shell">
	 	 	 <div class="teacherInfo core" id="myflash">
	 	 	 	<div style="height: 130px;">
	 	 	 		<c:forEach items="${teacherLists}" var="t" begin="0" end="6">
						<div class="span2">
							<c:choose>
								<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
									<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="${url}${t.user.photo_url }"></c:url>' style="width: 90px; height:90px;" /></a>
								</c:when>
								<c:otherwise>
									<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 90px; height:90px;" /></a>
								</c:otherwise>
							</c:choose>
							<div style="margin-top: 3px; "> 
								<c:choose>
								<c:when test="${t.user.name==null||t.user.name==''}">
								<span id="contentlimit" style="width:90px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>尚未添加</a></span>
								</c:when>
								<c:otherwise>
								<span id="contentlimit" style="width:90px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a></span> 
								</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:forEach>
				</div>
				<div  style="height: 130px;">
	 	 	 		 <c:forEach items="${teacherLists}" var="t" begin="7" end="13">
						<div class="span2">
							<c:choose>
								<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
									<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="${url}${t.user.photo_url }"></c:url>' style="width: 90px; height:90px;" /></a>
								</c:when>
								<c:otherwise>
									<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 90px; height:90px;" /></a>
								</c:otherwise>
							</c:choose>
							<div style="margin-top: 3px; "> 
								<c:choose>
								<c:when test="${t.user.name==null||t.user.name==''}">
								<span id="contentlimit" style="width:90px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>尚未添加</a></span>
								</c:when>
								<c:otherwise>
								<span id="contentlimit" style="width:90px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a></span> 
								</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
	 	 </div>
	</div>
</div>
<style>
.shell{
        width:1024px;
       margin-top: 10px;
}
.core{
        height:130px;
        overflow:hidden;
}
</style>
<script type="text/javascript">

/*
window.onload = function ()
{	
	
	mytab("tab","content");
	mytab("tab_req","content_req");
	
}

function mytab(tab_id, content_id){
	var oLi = document.getElementById(tab_id).getElementsByTagName("li");
	var oUl = document.getElementById(content_id).getElementsByTagName("ul");
	for(var i = 0; i < oLi.length; i++)
	{
		oLi[i].index = i;
		oLi[i].onclick = function ()
		{	
			for(var n = 0; n < oLi.length; n++) {
				oLi[n].className="";	
			}
			this.className = "current";
			for(var n = 0; n < oUl.length; n++) {
				oUl[n].style.display = "none";
			}
			oUl[this.index].style.display = "block";
		};	
	}
}*/
window.onload = function (){
	$(".activities_title").mouseover(function (){
		$(this).next().css("display","block");
	});
	$(".activities_title").mouseout(function (){
		$(this).next().css("display","none");
	});
	myFlash('myflash',2000,-1);
	var sc = new Scroll(document.getElementById("scroll"));  
	
};
function myFlash(id,w,n){
    var box=document.getElementById(id),can=true,w=w||1500,fq=fq||10,n=n==-1?-1:1;
    box.innerHTML+=box.innerHTML;
    box.onmouseover=function(){can=false};
    box.onmouseout=function(){can=true};
    var max=parseInt(box.scrollHeight/2);
    new function (){
            var stop=box.scrollTop%130==0&&!can;
            if(!stop){
                    var set=n>0?[max,0]:[0,max];
                    box.scrollTop==set[0]?box.scrollTop=set[1]:box.scrollTop+=n;
            };
            setTimeout(arguments.callee,box.scrollTop%130?fq:w);
    };
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