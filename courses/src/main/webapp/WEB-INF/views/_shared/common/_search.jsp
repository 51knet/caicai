<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<link href="<c:url value='/resources/js/jquerysolid/css/jquery.slideBox.css'></c:url>" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="<c:url value="/resources/js/jquerysolid/js/jquery.slideBox.min.js" />"></script> 
<style>
.container.search-bar {
	height: 330px;
	margin: 67px 0px 0px 0px;
	width: 1024px;
}
ul, li {
	margin:0;
	padding:0;
	list-style-type: none;

}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.btn_font{
	margin-top: 4px; font-family:Arial,'Microsoft YaHei'; color: #808080;
}
.right_navbar_content{
	 text-align:left;
	background-image: url(' <c:url value="/resources/img/default/right_bg_block.png" ></c:url> ' );
	 background-repeat:no-repeat; 
	 background-position: center top;
	 height:330px; 
	 color: #fff;
	 font-weight: bold;
	 font-size: 14px;
	 line-height: 25px;
}

.right_navbar_content>table{
	margin:15px 0px 0px 25px;
}
.right_navbar_content a{
	 color: #fff;
	 font-size: 16px;
}
.right_navbar_content a:hover{
	 color: #cdcdcd;
	 text-decoration: none;
	 
}
.right_navbar_content  .detail{
	color: #fff;
	 font-size: 12px;
}

.right_navbar_content a:hover .detail{
	color: #cdcdcd;
}
.padding_left{
	padding-left: 30px;
}
.padding_right{
	padding-right: 30px;
}

.select_style{
width: 80px; background-color: #3d5b8d; color: #fff; 
}

.search_type_style{
 margin-top: -5px; float: left; text-align: left; width: 494px;
}
.highsearch_button{
	color: #fff;  margin: 10px 0px 0px 2px; width: 60px;
}
</style>

<div class="container search-bar" >
	<div class="span7 padding_left"  style="width: 520px;">
	<div class="outer" >
	    <div id="content" >
	        <ul style="display:block;" >      	
	            <li><div style="position: relative;">
	            <form class="navbar-form"  action="<c:url value="/search/patent"></c:url>"  method="get" name="search_post_form" >
						<select class="form-control select_style"  name="types">
							<option value="patentName" selected="selected">专利名</option>
							  <option value="patentNum" >专利号</option>
							  <option value="inventer">发明人</option>
						</select> 
						<input type="text" name="searchParam" id="searchInput" style="width: 310px;" placeholder="搜索"  value="${searchParam }" > <button type="submit" class="btn  btn_font" style=" ">普通搜索</button>	
						<div class="search_type_style">
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
									<td align="right">	<a href="<c:url value='/search/patent/detail'></c:url> " class="btn btn-primary btn-small  highsearch_button" >高级搜索</a></td>
									</tr>
							</table>		
						</div>					
	            	</form>
	            	</div> 
	            </li>          	
	        </ul>
	    </div>  
	</div>
	
	<div style="margin-top: 75px;">
		<div id="demo1" class="slideBox">
		  <ul class="items">
		    <c:choose>
		    	<c:when test="${activityCount <= 0 }">
		    		<li><a href="#" ><img src="<c:url value='/resources/img/default/home_pic.jpg'></c:url>"  ></a></li>
				    <li><a href="#" ><img src="<c:url value='/resources/img/default/home_pic1.jpg'></c:url>"  ></a></li>
				    <li><a href="#" ><img src="<c:url value='/resources/img/default/home_pic2.jpg'></c:url>"  ></a></li>
				    <li><a href="#" ><img src="<c:url value='/resources/img/default/home_pic3.jpg'></c:url>"  ></a></li>
		    	</c:when>
		    	<c:otherwise>
			    	<c:forEach items="${activitys.content }" var="activity" begin="0" end="4">
		    			<li><a href="#" ><img src="<c:url value='${url}/${activity.filePath }'></c:url>"  ></a></li>
		    		</c:forEach>
		    	</c:otherwise>
		    </c:choose>
		  </ul>
		</div>
	</div>
</div>
	
	<div class="span5 right_navbar_content" style=" width: 450px; ">
		<table cellpadding="5" width="90%" border="0"  style="" >
			<tr>
				<td width="53%" height="140" valign="top"><a href="<c:url value="/projects/list"></c:url>">融资
					<br><span class="detail">技术与资金的完美结合，解决<br>您的融资困境</span>
						<img src="<c:url value="/resources/img/default/featured-arrow.png"></c:url>" >
					</a>
				</td>
				<td width="47%" valign="top">
					<a href="<c:url value="/patent/list"></c:url>">专利<br><!-- 
					<img src="<c:url value='/resources/img/default/icon_new.png'></c:url> " /> <a href="<c:url value="/patent/china/list"></c:url>" class="detail">国内专利</a><br>
					<img src="<c:url value='/resources/img/default/icon_new.png'></c:url> " /> <a href="<c:url value="/patent/foreign/list"></c:url>"  class="detail">国际专利</a> -->
					<span class="detail">汇集最全、最新全球专利大数据资源，提供技术转移和专利购买服务</span>
					<img src="<c:url value="/resources/img/default/featured-arrow.png"></c:url>" >
					</a>
				</td>
			</tr>
			<tr>
				<td height="120" valign="top"><a href="<c:url value="/requirement/technology/list"></c:url>">需求<br>
					<span class="detail"> 解决技术难题、汇集专利需求</span>
					<img src="<c:url value="/resources/img/default/featured-arrow.png"></c:url>" >
					</a>
				</td>
				<td valign="top"><a href="<c:url value="/technology/list"></c:url>">成果
					<br><span class="detail">发布科技成果的供需信息，实现高校、企业、政府的技术转移</span>
					<img src="<c:url value="/resources/img/default/featured-arrow.png"></c:url>" >
					</a>
				</td>
			</tr>
		</table>
	</div>
</div>

<script type="text/javascript">
(function($) {
	var patentSearchType = "${searchTypes}";
	$("select[name='types'] > option").each(function(){
		if($(this).val() == patentSearchType){
			$(this).attr("selected", "selected");
		}
	});	
})(jQuery);

jQuery(function($){
	$('#demo1').slideBox({
		hideClickBar : false
	});
});


</script>