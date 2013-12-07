window.onload = function ()
{	
	
	mytab("tab","content");
	mytab("tab_req","content_req");
	
}

function mytab(tab_id, content_id){
	var oLi = document.getElementById(tab_id).getElementsByTagName("li");
	var oUl = document.getElementById(content_id).getElementsByTagName("ul");
	for(var i = 0; i < oLi.length; i++)
	{alert("111");
		oLi[i].index = i;
		alert(oLi[i].index);
		oLi[i].onclick = function ()
		{	
			for(var n = 0; n < oLi.length; n++) {
				oLi[n].className="";	
			}
			this.className = "current";
			for(var n = 0; n < oUl.length; n++) {
				oUl[n].style.display = "none";
			}
			oUl[this.index].style.display = "block";
		};	
	}
}