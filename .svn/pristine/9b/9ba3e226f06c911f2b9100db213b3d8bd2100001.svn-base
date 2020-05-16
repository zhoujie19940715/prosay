/**
 * 验证框架 
 * 对象级框架
 * 常见要提供的验证方法有哪些:
 * notNull rangeLength minLength maxLength equalsTo reg ajax
 * 
 */
(function($){
	$.fn.prosayVali = function(callback){
		//对象级插件的主函数中this是当前的对象
		var form = this;//当前调用的form表单对象 jquery的代理对象
		form.submit(function(event){
			var flag = true;
			form.find("input").each(function(){
				var obj = $(this);
				if(!obj.attr("validate")){
					return;
				}
				//将validate中的json字符串转换成js对象（json对象）
				var validates = $.parseJSON(obj.attr("validate"));
				for(var i = 0 ; i < validates.length ; i++){
					var validate = validates[i];
					//将一段字符串当作js代码运行
					var func = eval(validate.type);
					if(!validate.success){
						validate.success="校验通过";
					}
					if(func(obj,validate)){//校验成功
						showSuccess(obj,validate.success);
					}else{
						flag=false;
						showErrorInfo(obj,validate.msg);
						return;
					}
				}
			});
			return flag;
		});
		//这个this 他是jquery对象还是form对象
		//遍历form表单中的所有的input标签
		form.find("input").each(function(){
			var obj = $(this);
			init(obj);
		});
		function init(obj){
			if(!obj.attr("validate")){
				console.log(obj.attr("name")+":未注册验证规则！");
				return;
			}
			//通过失去焦点来做校验
			obj.blur(function(){
				//将validate中的json字符串转换成js对象（json对象）
				var validates = $.parseJSON(obj.attr("validate"));
				for(var i = 0 ; i < validates.length ; i++){
					var validate = validates[i];
					//将一段字符串当作js代码运行
					var func = eval(validate.type);
					if(!validate.success){
						validate.success="校验通过";
					}
					if(func(obj,validate)){//校验成功
						showSuccess(obj,validate.success);
					}else{
						showErrorInfo(obj,validate.msg);
						return;
					}
				}
				
			});
		}
		/*
		 * 你需要什么参数
		 * notNull
		 * */
		//非空校验
		function notNull(obj,validate){
			var value = $.trim(obj.val());
			if(value.length==0){
				return false;
			}
			return true;
		}
		/**
		 * 校验长度是否在某个范围之内
		 * 最小值 和 最大值
		 * validate minLength  maxLength
		 */
		function rangeLength(obj,validate){
			var value = $.trim(obj.val());
			if(value.length>=validate.minLength&&value.length<validate.maxLength){
				return true;
			}
			return false;
		}
		/**
		 * 校验长度不能少于多少
		 * validate minLength
		 */
		function minLength(obj,validate){
			var value = $.trim(obj.val());
			if(value.length>=validate.minLength){
				return true;
			}
			return false;
		}
		/**
		 * 校验长度不能大于多少
		 * validate maxLength
		 */
		function maxLength(obj,validate){
			var value = $.trim(obj.val());
			if(value.length<validate.maxLength){
				return true;
			}
			return false;
		}
		/**
		 * 校验当前的form组件值是否与另一个相等
		 * validate target jquery选择表达式
		 */
		function equalsTo(obj,validate){
			var value = $.trim(obj.val());
			var target = $(validate.target);
			var valueTo = $.trim(target.val());
			if(value==valueTo){
				return true;
			}
			return false;
		}
		/**
		 * 正则表达式校验
		 * validate reg 正则表达式的字符串
		 */
		function reg(obj,validate){
			var regMatch = new RegExp(validate.reg);
			if(regMatch==null||regMatch=="null"){
				return true;
			}
			var value = $.trim(obj.val());
			if(!regMatch.test(value)){
				return false;
			}
			return true;
		}
		function ajax(obj,validate){
			var result = true;
			$.ajax({
				type:"post",
				url:validate.url,
				data:{"value":obj.val()},
				dataType:"JSON",
				async:false,
				success:function(msg){
					result = msg.result;
				}
			});
			return result;
		}
		function showErrorInfo(obj,msg){
			var helpBlock = obj.parent().parent().find(".help-block");
			obj.parent().parent().addClass("has-error");
			obj.parent().parent().removeClass("has-success");
			helpBlock.html("<i class='fa fa-remove'></i>"+msg);
		}
		function showSuccess(obj,msg){
			var helpBlock = obj.parent().parent().find(".help-block");
			obj.parent().parent().addClass("has-success");
			obj.parent().parent().removeClass("has-error");
			helpBlock.html("<i class='fa fa-check'></i>"+msg);
		}
	}
})(jQuery);