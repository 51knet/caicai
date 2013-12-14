<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!-- <script type="text/javascript" src="<c:url value="/resources/js/mytab.js" />"></script> -->
<style>
.container.search-bar {
	background-image: url("/courses/resources/img/default/search_bg.png");
	background-position:left top;
	background-repeat: no-repeat;
	height: 100px;
	text-align: center;
	margin: 40px 0px 0px 0px;
	width: 1024px;
}
ul, li {
	margin:0;
	padding:0;

}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.btn_font{
	margin-top: 4px; font-family:Arial,'Microsoft YaHei'; color: #808080;
}

#searchDropdown{
width: 385px; height: 100px; border: 1px solid #ccc; margin-top: -48px; margin-left: 355px; position: absolute; z-index: 100; background-color: #fff; display: none;
}
#searchDropdown>ul>li{
	line-height: 20px;
	padding: 0px 5px;
	list-style-type: none;
}

.searchLiCurrent{
	background-color: #ccc;
}
</style>

<div class="container search-bar">
		<div class="outer" style="margin-top: 10px;">
		    <ul id="tab">
		    <br>
		       <!-- 	<li class=" current" ><b>专利搜索</b></li>
		        <li class=" round <c:if test='${active == "patent" }' > current</c:if> " ><b>专利</b></li>
		        <li  class="round <c:if test='${active == "course"}' > current</c:if> "><b>课程</b></li>
		        <li class="round <c:if test='${active == "teacher" }' > current</c:if> "><b>教师</b></li> -->
		    </ul>
	    <div id="content">
	        <ul style="display:block;" >
	        	
	            <li><div style="position: relative;">
	            <form class="navbar-form"  action="<c:url value="/search/patent"></c:url>"  method="get" name="search_post_form">
						<select class="form-control" style="width: 100px; background-color: #a7c676; border: 0px; color: #fff; font-family:Arial,'Microsoft YaHei';" name="types">
							<option value="patentName" selected="selected">专利名</option>
							  <option value="patentNum" >专利号</option>
							  <option value="inventer">发明人</option>
						</select> <input type="text" name="searchParam" id="searchInput" style="width: 357px;" placeholder="搜索"  value="${searchParam }" > <button type="submit" class="btn btn_font" style=" ">普通搜索</button>	
							<div style="margin-left: 100px; margin-top: -5px; float: left; text-align: left; width: 460px;">
								<table width="100%" >
										<tr><td align="left">
											<label class="radio inline" >
												<input type="radio" name="patentType" value="1"   checked="checked"> 发明专利
											</label>
											<label class="radio inline" >
												<input type="radio" name="patentType" value="2" > 实用新型
											</label>
											<label class="radio inline" >
												<input type="radio" name="patentType" value="3"  > 外观设计
											</label>
											<label class="radio inline" >
												<input type="radio" name="patentType" value="4" > 发明授权
											</label>
										</td>
										<td align="right">	<a href="<c:url value='/search/patent/detail'></c:url> " class="btn btn-success btn-small  btn_font" style="color: #fff; font-size: 13px; margin: 10px 0px 0px 2px; width: 60px;">高级搜索</a></td>
										</tr>
								</table>		
							</div>
						
	            </form>
	            	</div> 
	            </li>
            	
	        </ul>
	        <!-- <ul style="<c:if test='${active == "course" }' >display:block;</c:if>" >
	            <li><form class="navbar-form" action="<c:url value="/search"></c:url>" method="post" style="margin-left: 100px;">
	              		   <input type="hidden" name="searchStyle" value="course">
					       <input type="text" name="searchParam"  style="width: 380px;" placeholder="搜索课程"  value="${searchParam }"><button type="submit" class="btn  btn_font">搜  索</button>
					     
					       </form></li>
	        </ul>
	        <ul style="<c:if test='${active == "teacher" }' >display:block;</c:if>" >
	            <li><form class="navbar-form" action="<c:url value="/search"></c:url>" method="post" style="margin-left: 100px;">
	             			 <input type="hidden" name="searchStyle" value="teacher">
					       <input type="text" name="searchParam"  style="width: 380px;" placeholder="搜索教师"  value="${searchParam }"><button type="submit" class="btn  btn_font" >搜  索</button>
					       </form></li>
	        </ul> -->
	    </div>
	  
	</div>
	<!-- 
	  <div style="width: 80px; height: 40px;  float: left; margin-top: 24px; margin-left: 20px;">
		<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=826619119&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:826619119:41" alt="点击这里给我发消息" title="点击这里给我发消息"/></a><br>
	
		</div>
 -->
</div>
<!-- 
<div id="searchDropdown"  >
	<ul id="searchDropdownUl"  >
		<li>耕机的动力1111</li>
		<li>1111</li>
		<li>耕机的动力</li>
		<li>耕机的动力222</li>
		<li>22222</li>
	</ul>
</div>
 -->
<script type="text/javascript">
(function($) {
	var patentSearchType = "${searchTypes}";
	$("select[name='types'] > option").each(function(){
		if($(this).val() == patentSearchType){
			$(this).attr("selected", "selected");
		}
	});
	
	$("#searchDropdownUl >li").each(function(){
		$(this).hover(
				function () {
				    $(this).addClass("searchLiCurrent");
				  },
				function () {
				    $(this).removeClass("searchLiCurrent");
				}
		);
		
		$(this).mouseover(function (){
			$("#searchInput").val($(this).html());
		});
	});
	
	$("#searchInput").click(function(){
		$("#searchDropdown").css("display","block");
	});
	
	$("#searchInput").blur(function(){
		$("#searchDropdown").css("display","none");
	});
	
	
})(jQuery);

</script>