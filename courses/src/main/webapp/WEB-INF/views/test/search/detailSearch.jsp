<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function selectType() {
		var sel = document.getElementById("type");
		var opt = sel.options;
		for ( var i = 0; i < opt.length; i++) {
			if (opt[i].selected) {
				var typeName = opt[i].innerHTML;
				//alert(typeName);
				window.location.href = '<c:url value="/course/list/type?detail='+ typeName + '"></c:url>';
			}
		}
	}
</script>


<div class="container title"  >

 </div>
 <div class="container user-course">
 			<form class="navbar-form"  action="<c:url value="/test/search/patent"></c:url>"  method="get">
				<select class="form-control" style="width: 100px;" name="types">
					  <option value="patentNum" selected="selected">专利号</option>
					  <option value="patentName">专利名</option>
					  <option value="inventer">发明人</option>
				</select> <input type="text" name="searchParam" class="span5" placeholder="输入姓名搜索"  value="${searchParam }">
		         &nbsp;&nbsp;<button type="submit" class="btn" style="margin-top: 4px; font-family:Arial,'Microsoft YaHei'; color: #808080; ">搜 索</button>
		    </form>
 </div>



