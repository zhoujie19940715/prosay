<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="${basePath}/static/product/type.css" rel="stylesheet" />
<head>
        <meta charset="utf-8" />
        <title></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${basePath}/static/content/ui/global/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link href="${basePath}/static/content/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <!-- Theme style -->
        <link rel="stylesheet" href="${basePath}/static/content/adminlte/dist/css/AdminLTE.css">
        <link rel="stylesheet" href="${basePath}/static/content/adminlte/dist/css/skins/_all-skins.min.css">
        <link href="${basePath}/static/content/min/css/supershopui.common.min.css" rel="stylesheet" />
        <link href="${basePath}/static/content/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
         <link href="${basePath}/static/content/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
   		 <link href="${basePath}/static/product/type.css" rel="stylesheet" type="text/css" />
    <!--全局通用框架样式 end-->
</head>
<body>
    <!-- START Main content -->
    <section class="content">
        <div class="row">
             <!-- 列表展示开始 -->
             <div class="box box-default type-box panel-heading">
             	<c:forEach items="${page.data}" var="type">
             		<c:if test="${type.type_parent==0}">
             		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-group">
             			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 " >
             				<div class="callout callout-danger">
	             				<input id="check-parent" name="check-parent" value="${ type.type_id}" type="checkbox" style="display: none;"/>
	              				<span class="type-text" >${type.type_name}</span>
	              				<span class="icon-edit" onclick="edit(${type.type_id})"><i class="fa fa-edit " ></i></span>
	           	     			<span class="icon-edit" onclick="del(${type.type_id})"><i class="fa fa-times"></i></span>
          	     			</div>
             			</div>
             			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
	              		<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
		               		<c:forEach items="${page.data}" var="childType">
		               			<c:if test="${childType.type_parent==type.type_id}">
		               			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 btn btn-info type-btn" >
		            				<input id="check-child" parent="${childType.type_parent}" type="checkbox" name="check-child" value="${childType.type_id}" style="display: none;"/>
		       	     				<span class="type-text" >${childType.type_name}</span>
		       	     				<span class="icon-edit" onclick="edit(${childType.type_id})"><i class="fa fa-edit " ></i></span>
		       	     				<span class=" icon-edit" onclick="del(${childType.type_id})"><i class="fa fa-times"></i></span>
		            			</div>
		               			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
		               			</c:if>
		               		</c:forEach>
	              		</div>
             		 </div>
             		</c:if>
             	</c:forEach>
             </div>
             <!-- 列表展示结束 -->
        </div>
    </section>
    <!-- END Main content -->
    
<script src="${basePath}/static/content/ui/global/jQuery/jquery.min.js"></script>
<!-- Bootstrap 3.3.6 -->

<script src="${basePath}/static/content/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/content/min/js/supershopui.common.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
<script>

    var API_URL = 'http://' + location.host + ':3001/list/';

    var 
        $modal = $('#modal').modal({ show: false }),
        $alert = $('.alert').hide();

    function edit(type_id){
    	 $.fn.modalOpen({
             id: "Form",
             title: '类型编辑',
             url: 'typeEditShow.do?type_id='+type_id,
             width: "700px",
             height: "400px",
             callBack: function (iframeId) {
                 var obj = document.getElementById(iframeId);
                 obj.contentWindow.AcceptClick();
             }
         });
    }
    function del(type_id){
 	   $.fn.modalConfirm("确定删除吗？",function(result){
 		   if(result){
 			   $.ajax({
 					type:"post",
 					url:"delType.do",
 					data:{"type_id":type_id},
 					success:function(result){
 						var strs = result.split("|");
 						if(result.indexOf("error")!=-1){
 							$.fn.modalAlert(strs[1],"error");
 						}else{
 							$.fn.modalAlert(strs[1],"success",function(){
 								top.layer.closeAll();
								$("#btn_query").click();
								return true;
 							});
 							
 						}
 					}
 				});
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
    $.fn.modalAlert = function (content, type, callback) {
	       var icon = "";
	       var iconType = 0;
	       if (type == 'success') {
	           icon = "fa-check-circle";
	           iconType = 1;
	       }else   if (type == 'error') {
	           icon = "fa-times-circle";
	           iconType = 2;
	       }else  if (type == 'warning') {
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
    $(function () {
        // create event
        $('.create').click(function () {
            showModal($(this).text());
        });
		$("#btn_query").click(function(){
			$("#formSearch").submit();
		});
        $modal.find('.submit').click(function () {
            var row = {};

            $modal.find('input[name]').each(function () {
                row[$(this).attr('name')] = $(this).val();
            });

        });
        $(".form_datetime").datetimepicker({
            language: 'zh-CN',
            autoclose: true,
            minView: "month", //选择日期后，不会再跳转去选择时分秒
            format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
            pickerPosition: ("bottom-left")
        });
        
        $("#btn_add").click(function(){
      	  $.fn.modalOpen({
                id: "Form",
                title: '类型新增',
                url: 'typeAdd.do',
                width: "700px",
                height: "400px",
                callBack: function (iframeId) {
                    var obj = document.getElementById(iframeId);
                    obj.contentWindow.AcceptClick();
                }
            });
      	});
        $("#btn_delete").click(function(){
        	$(".icon-edit").css("display","none");
        	$("#btn_delete").css("display","none");
        	$("input[name=check-parent]").css("display","inline-block");
        	$("input[name=check-child]").css("display","inline-block");
        	$("#btn_delete_all").css("display","inline-block");
        	$("#btn_cancel").css("display","inline-block");
        });
        $("#btn_cancel").click(function(){
        	$(".icon-edit").css("display","inline-block");
        	$("#btn_delete").css("display","inline-block");
        	$("input[name=check-parent]").css("display","none");
        	$("input[name=check-child]").css("display","none");
        	$("#btn_delete_all").css("display","none");
        	$("#btn_cancel").css("display","none");
        });
        $("input[name=check-parent]").click(function(){
        	var _this = $(this); // Jquery or Dom
        	var checked = _this.prop("checked");
        	 $("input[name=check-child]").each(function(){
        		 var parent = $(this).attr("parent");
             	if(_this.val() == parent){
             		$(this).prop("checked",checked);
             	}
        	 });
        	
        });
        $("input[name=check-child]").click(function(){
        	var parent = $(this).attr("parent");
        	var checked = $(this).prop("checked");
        	if(!checked){
        		$("input[name=check-parent]").each(function(){
            		if($(this).val() == parent){
            			$(this).removeProp("checked");
            		}
    	       	 });
        	}else{
        		var flag =0;
        		 $("input[parent="+parent+"]").each(function(){
        			 if($(this).prop("checked") != checked){
        				 flag ++;
        			 }
        		 })
        		 $("input[name=check-parent]").each(function(){
        			 if($(this).val() == parent){
        				 if(flag != 0){
                  			$(this).removeProp("checked");
                  		}else{
                  			$(this).prop("checked",checked);
                  		}
        			 }
             		
     	       	 });
        	}
        	
        });
        $("#btn_delete_all").click(function(){
        	var ids = "";
        	
			   //遍历获取所有的已经选择id
			 $("input[name=check-parent]").each(function(){
	   			 var tmp = $(this);
	   			
			   	if(tmp.prop("checked")){	
		   			ids+=tmp.val()+",";	
		   		 }
	   		
				});
			 $("input[name=check-child]").each(function(){
	   				var tmp_child = $(this);
	   				if(tmp_child.prop("checked")){	
	   					ids += tmp_child.val()+",";
	   				}
	   			});
			   console.log(ids+":"+ids.length);
			   if(ids.length<=0){
				   $.fn.modalAlert("没有选择任何商品！","warning");
				   return ;
			   }
			   ids = ids.substring(0,ids.length-1);
	    	  $.fn.modalConfirm("确定删除已选吗？",function(result){
			   if(result){
				   $.ajax({
						type:"post",
						url:"batchTypeDel.do",
						data:{"ids":ids},
						success:function(result){
							var strs = result.split("|");
							if(result.indexOf("error")!=-1){
								$.fn.modalAlert(strs[1],"error");
							}else{
								$.fn.modalAlert(strs[1],"success",function(index){
									top.layer.closeAll();
									$(".icon-edit").css("dispaly","inline-block");
									$("#btn_query").click();
									return true;
								});
							}
						}
					});
			   }
		   });
	        });
    });
    
    $.fn.modalOpen = function (options) {
        var defaults = {
            id: null,
            title: '系统窗口',
            width: "100px",
            height: "60px",
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
</script>
</html>        