   $(function () {
        // create event
        $('.create').click(function () {
            showModal($(this).text());
            // 

        });
		$("#btn_query").click(function(){
			$("#formSearch").submit();
		});
       
        $(".form_datetime").datetimepicker({
            language: 'zh-CN',
            autoclose: true,
            minView: "month", //选择日期后，不会再跳转去选择时分秒
            format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
            pickerPosition: ("bottom-left")
        });
      
        
        //全选 反选
        $("#checkAll").click(function(){
        	var _this = $(this); // Jquery or Dom
        	var checked = _this.prop("checked");
        	$("input[name=checkId]").each(function(){
        		 var tmp = $(this);
        		 tmp.prop("checked",checked);
        	});
        	/* console.log(_this.prop("checked"));
        	console.log(_this.attr("checked")); */
        });
       	//商品批量删除
       	
    }); 
    $.fn.modalOpen = function (options) {
        var defaults = {
            id: null,
            title: '系统窗口',
            width: "100px",
            height: "100px",
            url: '',
            shade: 0.3,
            btn: ['确认', '关闭'],
            btnclass: ['btn btn-primary', 'btn btn-danger'],
            callBack: null
        };
        var options = $.extend(defaults, options);
        var _width = top.$(window).width() > parseInt(options.width.replace('px', '')) ? options.width : top.$(window).width() + 'px';
        var _height = top.$(window).height() > parseInt(options.height.replace('px', '')) ? options.height : top.$(window).height() + 'px';
        var tmp =  layer.open({
            id: options.id,
            type: 2,
            shade: options.shade,
            title: options.title,
            fix: false,
            area: [_width, _height],
            content: options.url,
            btn: options.btn,
            btnclass: options.btnclass,
            yes: function () {
                options.callBack("layui-layer-iframe"+tmp);
            }, cancel: function () {
            	$("#btn_query").click();
                return true;
            },btn2:function(){
            	$("#btn_query").click();
                return true;
            }
        });
    }
    $.fn.modalConfirm = function (content, callBack) {
        layer.confirm(content, {
            icon: "fa-exclamation-circle",
            title: "系统提示",
            btn: ['确认', '取消'],
            btnclass: ['btn btn-primary', 'btn btn-danger'],
        }, function () {
            callBack(true);
        }, function () {
            callBack(false)
        });
    }
    $.fn.modalAlert = function (content, type,callback) {
        var icon = "";
        var iconType = 0;
        if (type == 'success') {
            icon = "fa-check-circle";
            iconType = 1;
        }
        if (type == 'error') {
            icon = "fa-times-circle";
            iconType = 2;
        }
        if (type == 'warning') {
            icon = "fa-exclamation-circle";
            iconType = 3;
        }
        top.layer.alert(content, {
            icon: iconType,
            title: "系统提示",
            btn: ['确认'],
            btnclass: ['btn btn-primary'],
            yes:callback
        });
    }
    $.fn.modalMsg = function (content, type) {
        var iconType = 0;
        if (type != undefined) {
            var icon = "";
            if (type == 'success') {
                icon = "fa-check-circle";
                iconType = 1;
            }
            if (type == 'error') {
                icon = "fa-times-circle";
                iconType = 2;
            }
            if (type == 'warning') {
                icon = "fa-exclamation-circle";
                iconType = 3;
            }
            top.layer.msg(content, { icon: iconType, time: 4000, shift: 5 });
            top.$(".layui-layer-msg").find('i.' + iconType).parents('.layui-layer-msg').addClass('layui-layer-msg-' + type);
        } else {
            top.layer.msg(content);
        }
    }
    $.fn.modalClose = function () {
        var index = layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        var $IsdialogClose = top.$("#layui-layer" + index).find('.layui-layer-btn').find("#IsdialogClose");
        var IsClose = $IsdialogClose.is(":checked");
        if ($IsdialogClose.length == 0) {
            IsClose = true;
        }
        if (IsClose) {
            layer.close(index);
        } else {
            location.reload();
        }
    }
    
