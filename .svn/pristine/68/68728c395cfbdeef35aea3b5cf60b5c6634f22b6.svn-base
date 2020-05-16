$(function(){
	//加载一级菜单
	$.ajax({
		type:"post",
		url:sysPath+"/product/queryType.do",
		data:{"type_id":0},
		dataType:"JSON",
		success:function(result){
			for(var i = 0;i<result.length;i++){
				var type = result[i];
				var str = "";
				var css="";
				if(typeId.length>0&&type.type_id==typeId){
					css="active";
				}
				//jquery对象
				var li = $("<li class="+css+"><a href='"+sysPath+"/productList.do?typeId="+type.type_id+"'>"+type.type_name+"</a></li>");
				$(".first").append(li);
				getSecondMenu(li,type.type_id);
			}
			
		}
	});
});
function getSecondMenu(li,typeId){
	$.ajax({
		type:"post",
		url:sysPath+"/product/queryType.do",
		data:{"type_id":typeId},
		dataType:"JSON",
		success:function(result){

			var ul=$("<ul class='second'></ul>");
			for(var i = 0;i<result.length;i++){
				var type = result[i];
				var str = "";
				//jquery对象
				var secLi = $("<li><a href='"+sysPath+"/productList.do?typeId="+typeId+"&sTypeId="+type.type_id+"'>"+type.type_name+"</a></li>");
				ul.append(secLi);
			}
			li.append(ul);
			$("li:has(ul)").hover(function(){
				$(this).addClass("active").siblings("li").removeClass("active");
				$(this).find(".second").slideDown();
				
			},function(){
				$(this).find(".second").slideUp();
				
				
			});
			/*$(".first>li").hover(function(){
				$(this).addClass("active").siblings("li").removeClass("active");
				$(".active").find(".second").css("display","block");
			},function(){
				$("li").find(".second").css("display","none");
			});
			*/
		}
	});
}