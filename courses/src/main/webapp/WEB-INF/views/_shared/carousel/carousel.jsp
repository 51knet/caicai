<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
!function ($) {
	  $(function(){
	    // carousel demo
	    $('#myCarousel').carousel();
	   // $('#myUniversity').carousel();
	   // $('#myTeacher').carousel();
	  });
	}(window.jQuery);
</script>
<<style>
<!--
 .top{
	margin-top: 17px;
	margin-bottom: -5px;
}
-->
</style>
	<div id="myCarousel" class="carousel slide top" style="background-repeat: no-repeat;">
		  <div class="carousel-inner" >
		    <div class="item active" style='width:1024px; height:500px; background-image: url("<c:url value='/resources/img/advertise/banner1_bak.jpg'></c:url> "); background-repeat: no-repeat;'>
		    
		    </div>
		     <div class="item" style='width:1024px; height:500px; background-image: url("<c:url value='/resources/img/advertise/banner1_bak.jpg'></c:url> ");  background-repeat: no-repeat;'  >
		     
		    </div>
		    <div class="item" style='width:1024px; height:500px; background-image: url("<c:url value='/resources/img/advertise/banner1_bak.jpg'></c:url> "); background-repeat: no-repeat;'>
		      	
		    </div>
	  </div>
	   <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
	  <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
	</div>