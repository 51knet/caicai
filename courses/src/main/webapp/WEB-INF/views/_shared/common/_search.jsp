<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<script type="text/javascript" src="<c:url value="/resources/js/mytab.js" />"></script>
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
</style>

<div class="container search-bar">
		<div class="outer">
		    <ul id="tab">
		        <li class=" round  current" ><b>专利搜索</b></li>
		        <!-- 
		        <li class=" round <c:if test='${active == "patent" }' > current</c:if> " ><b>专利</b></li>
		        <li  class="round <c:if test='${active == "course"}' > current</c:if> "><b>课程</b></li>
		        <li class="round <c:if test='${active == "teacher" }' > current</c:if> "><b>教师</b></li> -->
		    </ul>
	    <div id="content">
	        <ul style="display:block;" >
	        	
	            <li>
	            <form class="navbar-form"  action="<c:url value="/search/patent"></c:url>"  method="get">
						<select class="form-control" style="width: 100px; background-color: #a7c676; border: 0px; color: #fff; font-family:Arial,'Microsoft YaHei';" name="types">
							  <option value="patentNum" selected="selected">专利号</option>
							  <option value="patentName">专利名</option>
							  <option value="inventer">发明人</option>
						</select> <input type="text" name="searchParam" style="width: 380px;" placeholder="搜索"  value="${searchParam }"><button type="submit" class="btn btn_font" style=" ">搜 索</button>	
						<a href="<c:url value='/search/patent/detail'></c:url> " class="btn btn-success  btn_font" style="color: #fff;">高级搜索</a>
							<div style="margin-left: 100px; margin-top: -5px;">
							<!--<c:forEach items="${patentTypeList }" var="patentTypeList">
								<label class="radio inline" >
									<input type="radio" name="patentType" value="${patentTypeList.id}"  <c:if test="${patentTypeList.id ==1 }"> checked</c:if>  > ${patentTypeList.typeName }
								</label>
							</c:forEach>-->
								<label class="radio inline" >
									<input type="radio" name="patentType" value="1"   checked> 发明专利
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
							</div>
	            </form>
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


</div>
<script>

</script>
