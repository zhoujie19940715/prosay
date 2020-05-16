/**
*banner组件
*@author:jame
*@company 猿说教育
*@date 2017/9/8
*/
jQuery.fn.banner = function(opts){
	var defaults = {
		width:590,//banner组件宽度大小
		height:340,//banner组件高度大小
		speed:1000,//图片的切换间隔
		delay:1000,//图片的切换速度
		imgs:[]//banner图的列表 imgs里面的对象应该是一个json对象{img.src img.href}
	};
	opts = jQuery.extend({},defaults,opts);
	//主模板
	var template = "<div class=\"pannel\">"+
			"<div class=\"scroll\">";
	//下面的按钮列表
	var btnTemplate= "";
	for(var i = 0 ; i < opts.imgs.length ; i++){
		template+="<a href='"+opts.imgs[i].href+"' target=\"_blank\"><img src=\""+opts.imgs[i].src+"\" style='width:"+opts.width+"px'/></a>";
		btnTemplate+="<span></span>";
	}
	template+="</div><div class=\"btns\">"+btnTemplate+"</div></div><a href=\"javascript:void(0)\" class=\"prev\"></a><a href=\"javascript:void(0)\" class=\"next\"></a>";
	$(this).addClass("banner");
	$(this).append(template);
	//console.dir($(this).find("img")[0].clientWidth);
	$(this).css({width:opts.width,height:opts.height});
	$(this).find(".pannel .scroll").css({width:opts.width*opts.imgs.length,height:opts.height});
	$(this).find(".prev").css({top:((opts.height-70)/2)+"px"});
	$(this).find(".next").css({top:((opts.height-70)/2)+"px"});
	$(this).find(".pannel .btns span:first").addClass("active");
	var index = 0;
	var timer ;

	$(this).find(".btns span").mouseover(function(){
		index = $(this).index();//拿到当前span标签的序号，下标
		//-5*width
		$(".scroll").animate({left:-index*590+"px"},1000);	
		$(this).siblings("span").removeClass("active");
	});
	//定义一个全局的_this
	var _this = $(this);
	$(this).find(".prev").click(function(){
			index--;
			if(index<0){
				 index = opts.imgs.length-1;
			}
			play();
	});
	$(this).find(".next").click(function(){
				index++;
				if(index>opts.imgs.length-1){
					 index = 0;
				}
				play();
	});
		function play(){
			//_this 实际上是jquery这个对象，而在这里的this代表的是这个function
			$(_this).find(".pannel .scroll").animate({left:-index*opts.width+"px"},opts.delay);	
			$(_this).find(".btns span").eq(index).addClass("active").siblings("span").removeClass("active");
		}
		function startPlay(){
			timer = setInterval(function(){
				index++;
				if(index>opts.imgs.length-1){
					index = 0;
				}
				play();
			},3000);
		}
		$(this).find(".banner").mouseover(function(){
			clearInterval(timer);
		});
		$(this).find(".banner").mouseout(function(){
			startPlay();
		});
		startPlay();
}


