<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="">
	<form >
	  <legend>写博文</legend>
	  <div class="control-group" >
		  <label class="control-label" style="display:inline-block;width: 40px;">标题:</label>
		  <input type="text" name="title" placeholder="">
	  </div>
      <div class="control-group" >
		  <label class="control-label" style="display:inline-block; width: 40px;">分类:</label>
		  <select name="category">
		  	<option value="1">category 1</option>
		  	<option value="2">category 2</option>
		  	<option value="3">category 3</option>
		  	<option value="4">category 4</option>
		  </select>
	  </div>
	  <div class="clearfix"></div>
	  <div class="control-group">
	  	<textarea rows="20" cols="30" id="textarea" name="content" style="width: 98%;"></textarea>
	  </div>
	  <button type="submit" class="btn">Submit</button>
	</form>
</div>

<script type="text/javascript">
		$(document).ready(function() {
			$("#category_management").colorbox({inline:true, width:"50%", href:"#category_management_form"});
		});
</script>
	