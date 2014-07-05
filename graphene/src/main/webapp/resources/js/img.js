function previewImage(file)
{
 	var MAXWIDTH  = 260;
  	var MAXHEIGHT = 190;
  var div = document.getElementById('preview');
  if (file.files && file.files[0])
  {
  	div.innerHTML = '<img id=showimg>';
  	var img = document.getElementById('showimg');
  	img.onload = function(){
  	  var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT);
  	  img.width = rect.width;
  	  img.height = rect.height;
  	  
  	}
  	var reader = new FileReader();
  	reader.onload = function(evt){img.src = evt.target.result;}
  	reader.readAsDataURL(file.files[0]);
  }
  else
  {
	    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
	    file.select();
	    var src = document.selection.createRange().text;
	    div.innerHTML = '<img id=showimg>';
	    var img = document.getElementById('showimg');
	    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
	    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT);
	    status =(rect.width+','+rect.height);
	    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;"+sFilter+src+"\"'></div>";
  }
}
function clacImgZoomParam( MAXWIDTH, MAXHEIGHT){
	var param = {width:MAXWIDTH, height:MAXHEIGHT};
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
	return param;
}