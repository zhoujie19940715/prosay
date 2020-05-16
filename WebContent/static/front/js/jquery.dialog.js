jQuery.dialog = function(opts){
	//先提供默认配置
	var defaults = {
		id:"prosayDialog",
		width:400,
		height:80,
		title:"猿说教育对话框组件",
		enterText:"确认",
		cancelText:"取消",
		enter:function(){
			//alert("窗体取消");
		},
		cancel:function(){
		},
		content:"请传递对话框内容"
	};
	//将用户传递的options 和 defaults 进行结合
	opts = $.extend({},defaults,opts);
	//对话框模板html代码
	var template="<div class=\"screen\" id='screen"+opts.id+"'></div><div class=\"dialog\" id='dialog"+opts.id+"'>"+
			"<div class=\"header\" id='header"+opts.id+"'>"+
				"<span>"+opts.title+"</span>"+
			"</div>"+
			"<div class=\"content\" id='content"+opts.id+"'>"+
			opts.content+
			"</div>"+
			"<div class=\"footer\">"+
				"<a href=\"javascript:void(0);\"id='enter"+opts.id+"' class=\"btn btn-primary btn-flat\">"+opts.enterText+"</a>"+
				"<a href=\"javascript:void(0);\" id='cancel"+opts.id+"' class=\"btn cancel\">"+opts.cancelText+"</a>"+
			"</div>"+
		"</div>";
	//将模板HTML代码添加到body中
	$("body").append(template);
	//根据配置设置对话框的宽度和高度
	$("#content"+opts.id).css("width",opts.width+"px");
	$("#content"+opts.id).css("height",opts.height+"px");
	//标记是否为拖动操作
	var canMove = false;
	//
	var _x ,_y;
	//鼠标在标题部分按下的时候 标记为拖动对话框
	$("#header"+opts.id).mousedown(function(event){
		canMove = true;
		_x = event.pageX-parseInt($("#dialog"+opts.id).css("left"));
		_y = event.pageY-parseInt($("#dialog"+opts.id).css("top"));
	});
	//鼠标在标题部分松开的时候，标记为释放拖动操作
	$("#header"+opts.id).mouseup(function(event){
		canMove = false;
	});
	$(document).mousemove(function(event){
			if(canMove){
				var x = event.pageX-_x;
				var y = event.pageY-_y;
				$("#dialog"+opts.id).css({left:x,top:y});
			}
	});
	//enter点击事件的绑定
	$("#enter"+opts.id).click(function(){
		//调用opts的enter方法，执行 回调
		opts.enter();
		
	});
	//cancel点击事件的绑定
	$("#cancel"+opts.id).click(function(){
		$("#dialog"+opts.id).hide(500,function(){
				$("#dialog"+opts.id).remove();
		});
		$("#screen"+opts.id).hide(500,function(){
			$("#screen"+opts.id).remove();
		});
		opts.cancel();
	});
	$("#screen"+opts.id).show();
	$("#dialog"+opts.id).show();
};
/*window.alert = function(msg){
	$.dialog({
		id:"alert",
		title:"警告",
		content:msg,
		width:200,
		height:25
	});
}*/