/**
 * 
 */
$(function(){
	$.ajax({
		type:"post",
		url:sysPath+"/front/getBanner.do",
		dataType:"JSON",
		success:function(result){
			var img = new Array();
			for(var i = 0 ; i < result.length;i++){
				img[i]={src:sysPath+"/"+result[i].sales_pic,href:sysPath+"/product/detail.do?id="+result[i].product_id};
			}
			$("#banner1").banner({
				imgs:img,
				width:1000
			});	
		}
	});
	
	
});
