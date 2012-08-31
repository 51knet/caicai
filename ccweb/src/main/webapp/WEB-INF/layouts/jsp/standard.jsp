<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
	<meta name="description" content=""/>
	<meta name="author" content=""/>
	<link rel="icon" type="image/png" href="<c:url value="/resources/img/icon.png" />" />
      
	<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
		<title>
			<tiles:getAsString name="title" />
		</title>
		<style type="text/css" media="screen">
			@import url("<c:url value="/resources/bootstrap/css/bootstrap.min.css" />");
			@import url("<c:url value="/resources/css/standard.css" />");
		</style>
		<!-- Add jQuery library -->
		<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.8.0.js" />"></script>
		
	</head>
	<body>
	    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">Sidebar</li>
              <li class="active"><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
          <tiles:insertAttribute name="body" />

        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; Company 2012</p>
      </footer>

    </div><!--/.fluid-container-->
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			/*button apply: show input fields when click apply*/
			$('#button_apply').bind('click',function(){
				$(this).css('display','none');
				$('#payment_details').css('display','block');
			});
			
		});
	</script>
	</body>
</html>
