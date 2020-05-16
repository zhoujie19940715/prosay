<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.prosayedu.com/prosay/core" prefix="page" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

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
    <!--全局通用框架样式 end-->
        </head>
<body>
    <section class="content-header">
        <h1>
             商品列表
            <small>商品的查询与管理</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">

        <div class="row">
                <!-- BEGIN SAMPLE TABLE PORTLET-->
                <div class="col-md-12">
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="box-body" style="padding-bottom:0px;">
                        <div class="panel panel-default">
                            <div class="panel-heading">查询条件</div>
                            <div class="panel-body">
                                <form id="formSearch" class="form-horizontal">
                                	<input type="hidden" name="currentPage" id="currentPage"/>
                                    <div class="form-group" style="margin-top:15px">
                                        <label class="control-label col-sm-1" for="TXT_PRODUCT_NAME">商品名称</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="PRODUCT_NAME" value="${page.pd.PRODUCT_NAME }" id="TXT_PRODUCT_NAME">
                                        </div>
                                        <label class="control-label col-sm-1" for="txt_search_statu">上架日期</label>
                                        <div class="col-sm-2">
                                             <div class="input-group date form_datetime input-large">
                                                    <input type="text" name="STARTTIME" value="${page.pd.STARTTIME }"  size="16" readonly class="form-control">
                                                    <span class="input-group-btn">
                                                        <button class="btn default date-set" type="button">
                                                            <i class="fa fa-calendar"></i>
                                                        </button>
                                                    </span>
                                                </div>
                                        </div>
                                        <div class="col-sm-2">
                                        	 <div class="input-group date form_datetime input-large">
                                                    <input type="text" name="ENDTIME" value="${page.pd.ENDTIME }"  size="16" readonly class="form-control">
                                                    <span class="input-group-btn">
                                                        <button class="btn default date-set" type="button">
                                                            <i class="fa fa-calendar"></i>
                                                        </button>
                                                    </span>
                                                </div>
                                        </div>
                                        <div class="col-sm-3" style="text-align:left;">
                                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary">查询</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div id="toolbar" class="btn-group">
                            <button id="btn_add" type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                            </button>
                            <button id="btn_edit" type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                            </button>
                            <button id="btn_delete" type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                            </button>
                        </div>
                        <div class="table-scrollable  fixed-table-container">
                            <table class="table-striped table-hover table" id="table">
                                <thead>
                                    <tr>
                                        <th data-checkbox="true"><input id="checkAll" type="checkbox"/></th>
                                        <th>商品图片</th>
                                        <th>商品名称</th>
                                        <th>商品库存量</th>
                                        <th>商品单价</th>
                                        <th>商品简介</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.data}" var="product">
                                <tr>
                                	<td><input type="checkbox" name="checkId" value="${product.product_id}"/></td>
                                	<td><img style="width:100px;" src="${basePath}/${product.product_pic}"/></td>
                                	<td>${product.product_name}</td>
                                	<td>${product.product_storage}</td>
                                	<td>${product.product_price}</td>
                                	<td>${product.product_detail}</td>
                                	<td>
                                	<a href="javascript:edit(${product.product_id});" class="btn btn-icon-only purple"><i class="fa fa-edit"></i></a>
                                	<a href="javascript:del(${product.product_id});" class="btn btn-icon-only red"> <i class="fa fa-times"></i></a>
                                	</td>
                                </tr>
                                </c:forEach>
                                <tr>
                                	<td colspan=7 align=right><page:page pageSize="${page.pageSize}" total="${page.total }" currentPage="${page.currentPage}" formId="formSearch"/></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->

                </div>


        </div>
       
    </section>
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
      
        $("#btn_add").click(function(){
        	  $.fn.modalOpen({
                  id: "Form",
                  title: '商品新增',
                  url: 'productAdd.do',
                  width: "750px",
                  height: "550px",
                  callBack: function (iframeId) {
                      var obj = document.getElementById(iframeId);
                      obj.contentWindow.AcceptClick();
                  }
              });
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
       	$("#btn_delete").click(function(){
	       	 	var ids = "";
				   //遍历获取所有的已经选择id
				 $("input[name=checkId]").each(function(){
	      		 var tmp = $(this);
	      		 if(tmp.prop("checked")){
	      			ids+=tmp.val()+",";	
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
	   					url:"batchDel.do",
	   					data:{"ids":ids},
	   					success:function(result){
	   						var strs = result.split("|");
	   						if(result.indexOf("error")!=-1){
	   							$.fn.modalAlert(strs[1],"error");
	   						}else{
	   							$.fn.modalAlert(strs[1],"success",function(index){
	   								top.layer.closeAll();
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
    function edit(id){
   	 $.fn.modalOpen({
            id: "Form",
            title: '商品修改',
            url: 'productEdit.do?id='+id,
            width: "750px",
            height: "550px",
            callBack: function (iframeId) {
                var obj = document.getElementById(iframeId);
                obj.contentWindow.AcceptClick();
            }
        });
   }
   function del(id){
	   $.fn.modalConfirm("确定删除吗？",function(result){
		   if(result){
			   $.ajax({
					type:"post",
					url:"delProduct.do",
					data:{"id":id},
					success:function(result){
						var strs = result.split("|");
						if(result.indexOf("error")!=-1){
							$.fn.modalAlert(strs[1],"error");
						}else{
							$.fn.modalAlert(strs[1],"success",function(index){
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
</script>
</html>