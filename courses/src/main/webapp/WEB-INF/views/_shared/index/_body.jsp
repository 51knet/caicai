<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>	
	ul{
		list-style-type: none;
	}
	li{
		line-height:25px;
	}
	
	.big_content{
		margin-top: 10px;
	}
	.actitle{
		 line-height:20px; position: absolute; 
		 padding:3px 5px; z-index: 100; 
		 margin-top:-5px;  font-size:13px;   
		 border: 1px solid #ccc; background-color: #fff; 
		 display: none; max-width: 500px; max-height: 60px;
	}
	
	.right_bottom_content{
		padding: 10px 20px 10px 10px;
		height: 90px;
	}
</style>
<div class="container big_content padding_left" >
	<div class="span7 "  style="width: 500px;">
		<div class="row-fluid">
	  		<div class="span6">
	  			<ul>
	  			<li class="bLine_dash">
	  				<a href="<c:url value="/patent/list/china"></c:url>" ><img src="<c:url value='/resources/img/default/chinesepatent.png'></c:url> " /></a>
	  				<a class="a_color_blue"  style="text-decoration: none; float: right;" href="<c:url value="/patent/list/china"></c:url>" >更多>></a>
	  			</li> 
				<li style="margin-top: 10px;"> </li>
				<c:forEach items="${chinaPatentList }" var="chinaPatentList" begin="0"  end="9"  varStatus="i">
					<li ><div id="contentlimit" style="width:240px; float: left;">
						<a class="a_color_ccc"  href="<c:url value="/patent/view?id=${chinaPatentList.patentNum }"></c:url>"><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 
						${chinaPatentList.patentName } <c:if test="${i.index<3 }">...<img src="<c:url value='/resources/img/default/new-icon.gif'></c:url>" > </c:if>
						</a>
						</div>
					</li>
				</c:forEach>
				</ul>
	  		</div>
	  		<div class="span6" >
	  			<ul>
	  			<li class="bLine_dash">
	  				<a href="<c:url value="/patent/list/foreign"></c:url>" ><img src="<c:url value='/resources/img/default/foreignpatent.png'></c:url> " /></a>
	  				<a class="a_color_blue"  style="text-decoration: none; float: right;" href="<c:url value="/patent/list/foreign"></c:url>" >更多>></a> 
	  			</li> 
	  			<li  style="margin-top: 10px;"> </li>
				<c:forEach items="${foreignPatentList }" var="foreignPatentList" begin="0" end="9" varStatus="i">
						<li   ><div id="contentlimit" style="width:240px; float: left;">
							<a class="a_color_ccc"  href="<c:url value="/patent/view?id=${foreignPatentList.patentNum }"></c:url>">
							<img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > ${foreignPatentList.patentName } <c:if test="${i.index<3 }">...<img src="<c:url value='/resources/img/default/new-icon.gif'></c:url>" > </c:if></a>
						</div></li>
				</c:forEach>
				</ul>
	
				 
	  		</div>
  		</div>
  		<br>
	  	<div class="row-fluid">
			<ul>
  			<li class="bLine_dash"><img src="<c:url value='/resources/img/default/patentrequire.png'></c:url> " /> 
  				 <a class="a_color_blue"  style="text-decoration: none; float: right;"  href="<c:url value="/requirement/patent/list"></c:url>" >更多>></a>
  			</li> 
			<li >
				<table width="100%" style="margin-top: 10px;">
					<tbody>
						<tr>
							<td valign="top">
								<ul>
									<c:forEach items="${patentRequire }" var="patentRequire"  begin="0" end="6" varStatus="i">
										<li ><div id="contentlimit" style="width:350px; float: left;" >
										<img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" >  <a class="a_color_ccc"  href="<c:url value="/requirement/patent/view/${ patentRequire.id}"></c:url>">${patentRequire.title} 
										<c:if test="${i.index<3 }">...<img src="<c:url value='/resources/img/default/new-icon.gif'></c:url>" > </c:if>
										</a>
										</div></li>
									</c:forEach>
								</ul>
								<br>
							</td>
							<td valign="top">
									<img src="<c:url value='/resources/img/default/prpic1.png'></c:url>" >
									<img style="margin-top: 10px;" src="<c:url value='/resources/img/default/prpic2.png'></c:url>" >
							</td>
						</tr>
						</tbody>
					</table>
				</li> 
			</ul>  	
	  	</div>
	  <br>
	  	  <div class="row-fluid">
			<ul>
  			<li class="bLine_dash">
  				<img src="<c:url value='/resources/img/default/techrequire.png'></c:url> " /> 
  				 <a class="a_color_blue"  style="text-decoration: none; float: right;"  href="<c:url value="/requirement/technology/list"></c:url>" >更多>></a>
  			</li> 
			<li >
					<table width="100%" style="margin-top: 10px;">
					<tbody>
						<tr>
							<td valign="top">
								<ul>
								<c:forEach items="${technologyRequire}" var="technologyRequire"  begin="0"  end="6" varStatus="i">
									<li ><div id="contentlimit" style="width:350px; float: left;">
									<a class="a_color_ccc"   href="<c:url value="/requirement/technology/view/${technologyRequire.id}"></c:url>">
									<img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" >  ${technologyRequire.title } <c:if test="${i.index<3 }">...<img src="<c:url value='/resources/img/default/new-icon.gif'></c:url>" > </c:if></a>
									</div></li>
								</c:forEach>
								</ul>
								<br>
							</td>
							<td valign="top">
									<img src="<c:url value='/resources/img/default/trpic1.png'></c:url>" >
									<img style="margin-top: 10px;" src="<c:url value='/resources/img/default/trpic2.png'></c:url>" >
							</td>
						</tr>
						</tbody>
					</table>
			</li> 
			</ul>  	
	  	</div>
	</div>
	
	
	<div class="span5 " style=" width: 440px;  ">
		<div class="row-fluid">
			<!-- <a class="destoryPatentPostBtn" href="#destoryPatentPostModal" role="button" data-toggle="modal" data-target="#destoryPatentPostModal"> -->
			<a href="<c:url value='/fastupload'></c:url>">
				<img src="<c:url value='/resources/img/default/upload.png'></c:url> " /> 
			</a>
		</div><br>
		
		<div class="row-fluid bLine_dash"></div><br>
		
		<div class="row-fluid">
			<a href="<c:url value="/activity/list"></c:url>" ><img src="<c:url value='/resources/img/default/rt1.png'></c:url> " /> </a>
			<div class="right_bottom_content">
				<ul>
					<c:forEach items="${activityList }" var="activityList" begin="0"  end="2">
						<li >
							<div id="contentlimit" class=" activities_title"  style="width:400px; position: relative;">
									<a class="a_color_ccc"  href="<c:url value="/activity/view/${ activityList.id}"></c:url>"><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > ${activityList.title }</a>
							</div>
							<div class="actitle">${activityList.title }</div>
						</li>
					</c:forEach>
				</ul>
				<a style=" float: right;" href="<c:url value="/activity/list"></c:url>" >
					<img src="<c:url value='/resources/img/default/read_all.png'></c:url> " />
				</a>
			</div>
		</div>
		<br>
		<div class="row-fluid bLine_dash"></div><br>
		
		<div class="row-fluid">
			<a href="<c:url value="/technology/list"></c:url>"><img src="<c:url value='/resources/img/default/rt2.png'></c:url> " /> </a>
			<div class="right_bottom_content">
					<ul>
				<c:forEach items="${technologys}" var="technologys" begin="0"  end="2"  >
						<li ><div id="contentlimit" style="width:400px; float: left;">
						<a class="a_color_ccc"  href="<c:url value="/technology/view/${technologys.id }"></c:url>"><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > ${technologys.techName }</a>
						</div></li>
					</c:forEach>
				</ul>
				<a   style="float: right;" href="<c:url value="/technology/list"></c:url>" >
					<img src="<c:url value='/resources/img/default/read_all.png'></c:url> " />
				</a>
			</div>
		</div>
		<br>
		<div class="row-fluid bLine_dash"></div><br>
		
		<div class="row-fluid">
			<a href="<c:url value="/patent/list"></c:url>"><img src="<c:url value='/resources/img/default/rt3.png'></c:url> " /> </a>

			<div  class="right_bottom_content">
				<ul>
					<c:forEach items="${cpList }" var="cpList" begin="0"  end="2"  >
						<li ><div id="contentlimit" style="width:400px; float: left;">
						<a class="a_color_ccc"  href="<c:url value="/projects/view/${cpList.id}"></c:url>"><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > ${cpList.projectName }</a>
						</div></li>
					</c:forEach>
				</ul>
				<a   style=" float: right;" href="<c:url value="/projects/list/complete"></c:url>" >
					<img src="<c:url value='/resources/img/default/read_all.png'></c:url> " />
				</a>
			</div>
		</div>
		<br>
		<div class="row-fluid bLine_dash"></div><br>
		
		<div class="row-fluid scrollbg">
			<div class="shell">
			<div style="margin: 5px 0px; "><a href="<c:url value='/teacher/list'></c:url>" style="color:#fff; font-weight: bold; font-size: 15px; ">热门专家</a></div>
	 	 		 <div class="teacherInfo core" id="myflash">
		 	 	 	<div style="height: 90px;">
		 	 	 		<c:forEach items="${teacherLists}" var="t" begin="0" end="5">
							<div class="span2">
								<c:choose>
									<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
										<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="${url}${t.user.photo_url }"></c:url>' class="img-circle" style="width: 55px; height:55px;"  /></a>
									</c:when>
									<c:otherwise>
										<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' class="img-circle" style="width:55px; height:55px;"  /></a>
									</c:otherwise>
								</c:choose>
								<div style="margin-top: 3px; text-align: center; "> 
									<c:choose>
									<c:when test="${t.user.name==null||t.user.name==''}">
									<span id="contentlimit" style="width:60px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>尚未添加</a></span>
									</c:when>
									<c:otherwise>
									<span id="contentlimit" style="width:60px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a></span> 
									</c:otherwise>
									</c:choose>
								</div>
							</div>
							</c:forEach>
						</div>
						
						<div  style="height: 90px;">
			 	 	 		 <c:forEach items="${teacherLists}" var="t" begin="6" end="11">
								<div class="span2">
									<c:choose>
										<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
											<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="${url}${t.user.photo_url }"></c:url>' style="width: 55px; height:55px;" class="img-circle" /></a>
										</c:when>
										<c:otherwise>
											<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 55px; height:55px;" class="img-circle" /></a>
										</c:otherwise>
									</c:choose>
									<div style="margin-top: 3px; text-align: center; "> 
										<c:choose>
										<c:when test="${t.user.name==null||t.user.name==''}">
										<span id="contentlimit" style="width:60px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>尚未添加</a></span>
										</c:when>
										<c:otherwise>
										<span id="contentlimit" style="width:60px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a></span> 
										</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:forEach>
						</div>
						
						<div  style="height: 90px;">
			 	 	 		 <c:forEach items="${teacherLists}" var="t" begin="12" end="17">
								<div class="span2">
									<c:choose>
										<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
											<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="${url}${t.user.photo_url }"></c:url>' style="width: 55px; height:55px;" class="img-circle" /></a>
										</c:when>
										<c:otherwise>
											<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 55px; height:55px;" class="img-circle" /></a>
										</c:otherwise>
									</c:choose>
									<div style="margin-top: 3px; text-align: center; "> 
										<c:choose>
										<c:when test="${t.user.name==null||t.user.name==''}">
										<span id="contentlimit" style="width:60px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>尚未添加</a></span>
										</c:when>
										<c:otherwise>
										<span id="contentlimit" style="width:60px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a></span> 
										</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:forEach>
						</div>
						
						<div  style="height: 90px;">
			 	 	 		 <c:forEach items="${teacherLists}" var="t" begin="18" end="23">
								<div class="span2">
									<c:choose>
										<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
											<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="${url}${t.user.photo_url }"></c:url>' style="width: 55px; height:55px;" class="img-circle" /></a>
										</c:when>
										<c:otherwise>
											<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 55px; height:55px;" class="img-circle" /></a>
										</c:otherwise>
									</c:choose>
									<div style="margin-top: 3px; text-align: center; "> 
										<c:choose>
										<c:when test="${t.user.name==null||t.user.name==''}">
										<span id="contentlimit" style="width:60px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>尚未添加</a></span>
										</c:when>
										<c:otherwise>
										<span id="contentlimit" style="width:60px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a></span> 
										</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:forEach>
						</div>
						
						<div  style="height: 90px;">
			 	 	 		 <c:forEach items="${teacherLists}" var="t" begin="6" end="11">
								<div class="span2">
									<c:choose>
										<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
											<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="${url}${t.user.photo_url }"></c:url>' style="width: 55px; height:55px;" class="img-circle" /></a>
										</c:when>
										<c:otherwise>
											<a href='<c:url value="/teacher/${t.id}"></c:url>'><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 55px; height:55px;" class="img-circle" /></a>
										</c:otherwise>
									</c:choose>
									<div style="margin-top: 3px; text-align: center; "> 
										<c:choose>
										<c:when test="${t.user.name==null||t.user.name==''}">
										<span id="contentlimit" style="width:60px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>尚未添加</a></span>
										</c:when>
										<c:otherwise>
										<span id="contentlimit" style="width:60px; " ><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a></span> 
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
</div>


<br>
<style>
.shell{
        width:400px;
        margin-left: 25px;
      
}
.shell a{
	color: #fff;
}
.shell a:hover{
	color: #cbcbcb;
	text-decoration: none;
}
.scrollbg{
	     background-image: url(' <c:url value="/resources/img/default/teacher_scroll.png" ></c:url> ' );
	 background-repeat:no-repeat; 
	 background-position: center top;
	 height: 120px;
}
.core{
        height:80px;
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
            var stop=box.scrollTop%90==0&&!can;
            if(!stop){
                    var set=n>0?[max,0]:[0,max];
                    box.scrollTop==set[0]?box.scrollTop=set[1]:box.scrollTop+=n;
            };
            setTimeout(arguments.callee,box.scrollTop%90?fq:w);
    };
};


</script>