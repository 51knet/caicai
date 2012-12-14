function addCommentAjax(formID){
	$form = $('#' + formID);
	$form.bind('submit', function(e) {
	var commentTitle =$("#commentTitle").val();
	var commentDesc=$("#commentDesc").text();
	var teachercourseid=$("#teachercourseid").val();
	var markValue=$('input:radio[name=mark][checked]').val();
	if(commentTitle==""){
		$("#commentTitleError").html("<font color='#ff0000'>" +"标题不能为空"+ "</font>");
		return false;
	}
	if(commentDesc==""){
		$("#commentDescError").html("<font color='#ff0000'>" +"评论内容不能为空"+ "</font>");
		return false;
	}
	$.ajax({
		   type:"post",
		   url: "addComment",
		   data: "commentTitle="+commentTitle+"&mark="+markValue+"&commentDesc="+commentDesc+"&teachercourseid="+teachercourseid,
		   dataType:"json",
		   success: function(pages,markNum,personNum,value){
			   if(value==1){
				   $("#commentDescError").html("你已评论过此教师资料,谢谢的关顾");
				   return false;
			   }else{
				   var page=eval("(" + pages + ")");
					 $("#comment").html(page);
					 $("#markNum").html(markNum);
					 $("#personNum").html(personNum);
			   }
			   
				/*+"<tr><td>&nbsp;&nbsp;&nbsp;昵称:"+${comment.user.name}+"&nbsp;&nbsp;&nbsp;本人评分数:"+${comment.mark}+"</td></tr>"
				+"<tr><td>&nbsp;&nbsp;&nbsp;标题:"+${comment.commentTitle}+"&nbsp;&nbsp;&nbsp;评论时间:"+${comment.commentDate}+"</td></tr>"
				+"<tr><td>&nbsp;&nbsp;&nbsp;评论信息:"+${comment.commentDesc}+"&nbsp;&nbsp;&nbsp;</td></tr>"}"</div>"*/
		   }
		});
});
}