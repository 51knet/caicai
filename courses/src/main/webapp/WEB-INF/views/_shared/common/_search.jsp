<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!-- <script type="text/javascript" src="<c:url value="/resources/js/mytab.js" />"></script> -->
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
.right_navbar_content a{
	 color: #fff;
	 font-weight: bold;
	 font-size: 15px;
}
.right_navbar_content a:hover{
	 color: #cdcdcd;
	 font-weight: bold;
	 text-decoration: none;
}
.right_navbar_content  .detail{
	color: #fff;
	 font-weight: bold;
	 font-size: 13px;
}
.padding_left{
	padding-left: 30px;
}
.padding_right{
	padding-right: 30px;
}

</style>

<div class="container search-bar" >
	<div class="span7 padding_left"  style="width: 520px;">
	<div class="outer" >
	    <div id="content" >
	        <ul style="display:block;" >      	
	            <li><div style="position: relative;">
	            <form class="navbar-form"  action="<c:url value="/search/patent"></c:url>"  method="get" name="search_post_form" >
						<select class="form-control" style="width: 80px; background-color: #3d5b8d; color: #fff; " name="types">
							<option value="patentName" selected="selected">专利名</option>
							  <option value="patentNum" >专利号</option>
							  <option value="inventer">发明人</option>
						</select> 
						<input type="text" name="searchParam" id="searchInput" style="width: 310px;" placeholder="搜索"  value="${searchParam }" > <button type="submit" class="btn  btn_font" style=" ">普通搜索</button>	
						<div style=" margin-top: -5px; float: left; text-align: left; width: 494px; ">
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
									<td align="right">	<a href="<c:url value='/search/patent/detail'></c:url> " class="btn btn-primary btn-small  btn_font" style="color: #fff;  margin: 10px 0px 0px 2px; width: 60px;">高级搜索</a></td>
									</tr>
							</table>		
						</div>					
	            	</form>
	            	</div> 
	            </li>          	
	        </ul>
	    </div>  
	</div>
	
	<div title="flash">
		<div id="Layer1" style="position:absolute;margin-top:75px; width:500; height:250; z-index:1"> 
			<div align="center"> 
			<embed src="<c:url value='/resources/img/default/home.swf'></c:url> " width="500" height="250" wmode="transparent"> 
			</div>
		</div> 
		<div id="Layer2" style="position:absolute;margin-top:75px;width:500; height:250; z-index:2">
			
		</div>
	</div>
</div>
	
	<div class="span5 right_navbar_content" style=" width: 450px; ">
		<table cellpadding="5" width="90%" border="0"  style="margin:15px 0px 0px 20px;" >
			<tr>
				<td width="53%" height="140" valign="top"><a href="<c:url value="/activity/list"></c:url>">热门活动</a></td>
				<td width="47%" valign="top">
					<a href="<c:url value="/patent/list"></c:url>">专利展示</a><br>
					<img src="<c:url value='/resources/img/default/icon_new.png'></c:url> " /> <a href="<c:url value="/patent/list/china"></c:url>" class="detail">国内专利</a><br>
					<img src="<c:url value='/resources/img/default/icon_new.png'></c:url> " /> <a href="<c:url value="/patent/list/foreign"></c:url>"  class="detail">国际专利</a>
				</td>
			</tr>
			<tr>
				<td height="120" valign="top"><a href="<c:url value="/requirement/technology/list"></c:url>">技术需求</a></td>
				<td valign="top"><a href="<c:url value="/requirement/patent/list"></c:url>">专利需求</a></td>
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

</script>