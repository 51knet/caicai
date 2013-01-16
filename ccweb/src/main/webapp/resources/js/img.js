function previewImage(file)
{
 	var MAXWIDTH  = 210;
  	var MAXHEIGHT = 110;
  	var MARGIN_LEFT=100;
  	var MARGIN_TOP=20;
  var div = document.getElementById('preview');
  if (file.files && file.files[0])
  {
  	div.innerHTML = '<img id=showimg>';
  	var img = document.getElementById('showimg');
  	img.onload = function(){
  	  var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, MARGIN_LEFT,MARGIN_TOP);
  	  img.width = rect.width;
  	  img.height = rect.height;
  	  img.style.marginLeft = MARGIN_LEFT;
  	  img.style.marginTop = MARGIN_TOP;
  	}
  	var reader = new FileReader();
  	reader.onload = function(evt){img.src = evt.target.result;}
  	reader.readAsDataURL(file.files[0]);
  }
  else
  {
	    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
	    file.select();
	    document.getElementById("courseCover").style.display="none";
	    var src = document.selection.createRange().text;
	    div.innerHTML = '<img id=showimg>';
	    var img = document.getElementById('showimg');
	    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
	    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, MARGIN_LEFT, MARGIN_TOP);
	    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
	    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";
  }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
	var param = {top:MARGIN_TOP, left:MARGIN_LEFT, width:width, height:height};
	/* if( width>maxWidth || height>maxHeight )
	{
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;
		
		if( rateWidth > rateHeight )
		{
			param.width =  maxWidth;
			param.height = Math.round(height / rateWidth);
		}else
		{
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	} */
	
	param.width = MAXWIDTH;
	param.height = MAXHEIGHT;
	param.left = MARGIN_TOP;
	param.top = MARGIN_TOP;
	return param;
}