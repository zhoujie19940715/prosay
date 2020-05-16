<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品新增</title>
 <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${basePath}/static/content/ui/global/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link href="${basePath}/static/content/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <!-- Theme style -->
        <link rel="stylesheet" href="${basePath}/static/content/adminlte/dist/css/AdminLTE.css">
        <link rel="stylesheet" href="${basePath}/static/content/adminlte/dist/css/skins/_all-skins.min.css">
        <link href="${basePath}/static/content/min/css/supershopui.common.min.css" rel="stylesheet" />
</head>
<body>
 <!-- Horizontal Form -->
                <div class="box box-info">

                    <!-- form start -->
                    <form id="form" class="form-horizontal" method="post" action="editProduct.do" >
                    	<input type="hidden" name="product_id" value="${pd.product_id}"/>
                        <div class="box-body">
                            <div class="form-group">
                                <label for="product_name"  class="col-xs-2 control-label">商品名称</label>
                                <div class="col-xs-4">
                                    <input type="text" value="${pd.product_name}" class="form-control" id="product_name" name="product_name" placeholder="商品名称">
                                </div>
                                <label for="inputPassword3" class="col-xs-2 control-label">商品类型</label>
                                <div class="col-xs-4">
                                    <select id="typeFirst" name="type_pid">
                                    	
                                    </select>
                                    <select id="typeSecond" name="type_id">
                                    	
                                    </select>
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="product_storage" class="col-xs-2 control-label">商品库存</label>
                                <div class="col-xs-4">
                                    	<input type="text" value="${pd.product_storage}" class="form-control" id="product_storage" name="product_storage" placeholder="商品库存">
                                </div>
                                <label for="product_price" class="col-xs-2 control-label">商品价格</label>
                                <div class="col-xs-4">
                                    	<input type="text" value="${pd.product_price}" class="form-control" id="product_price" name="product_price" placeholder="商品价格">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="product_discount" class="col-xs-2 control-label">商品折扣</label>
                                <div class="col-xs-4">
                                    	<input type="text" value="${pd.product_discount}" class="form-control" id="product_discount" name="product_discount" placeholder="商品折扣">
                                </div>
                                <label for="product_outline" class="col-xs-2 control-label">商品简介</label>
                                <div class="col-xs-4">
                                    	<input type="text"  value="${pd.product_outline}"  class="form-control" id="product_outline" name="product_outline" placeholder="商品简介">
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="product_pic" class="col-xs-2 control-label">商品展示图</label>
                                <div class="col-xs-10">
                                		<input type="hidden" name="product_pic"  id="product_pic" value="${pd.product_pic}"/>
                                		<input type="file" id="imgup" onchange="imgUp()" style="display:none" id="filePic" accept="image/*"/>
                                    	<a href="javascript:void(0)" onclick="imgChoice()"><img id="imgShow" style="width:100px" <c:if test="${empty pd.product_pic}">src="${basePath}/static/img/upload.png"</c:if><c:if test="${not empty pd.product_pic}">src="${basePath}/${pd.product_pic}"</c:if>></a>
                                </div>
                            </div>
                             <div class="form-group">
                                <div class="col-xs-12">
                                	<input type="hidden" id="product_detail" name="product_detail"/>
                                	<div id="detail">${pd.product_detail}</div>
                                </div>
                            </div>
                            
                        </div>
                       
                    </form>
                </div>
<script src="${basePath}/static/content/ui/global/jQuery/jquery.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${basePath}/static/content/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/content/min/js/supershopui.common.js"></script>
<script type="text/javascript" src="${basePath}/static/content/plugins/wangEditor/wangEditor.min.js"></script>
<script type="text/javascript">
	var editor = null;
	$(function(){
		var flag = true;//表示是第一次
		var E = window.wangEditor
		editor = new E('#detail');
		editor.customConfig.onchange = function (html) {
            // 监控变化，同步更新到 textarea
            $("#product_detail").val(html)
        }
		//后端的jieshoudizhi
		editor.customConfig.uploadImgServer="${basePath}/imgUpload.do";
		var msg = "${msg}";
		if(msg.length>0){
			if(msg.indexOf("成功")!=-1){
				$.fn.modalAlert(msg,"success");
				if("${isClose}".length>0){
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				}
			}else{
				$.fn.modalAlert(msg,"error");
			}
			
		}
		editor.create();
		//加载一级商品类别
		$.ajax({
			type:"post",
			url:"queryType.do",
			data:{"type_id":0},
			dataType:"JSON",
			success:function(result){
				for(var i = 0;i<result.length;i++){
					var type = result[i];
					var str = "";
					if(flag){
						if(type.type_id=="${pd.type_pid}"){
							str="selected";
						}
					}
					$("#typeFirst").append("<option value='"+type.type_id+"' "+str+">"+type.type_name+"</option>");
					
				}
				$("#typeFirst").change();
			}
		});
	
		$("#typeFirst").change(function(){
			var _this = $(this);
			$("#typeSecond").empty();
			$.ajax({
				type:"post",
				url:"queryType.do",
				data:{"type_id":_this.val()},
				dataType:"JSON",
				success:function(result){
					for(var i = 0;i<result.length;i++){
						var type = result[i];
						var str = "";
						if(flag){
							if(type.type_id=="${pd.type_id}"){
								str="selected";
								flag = false;
							}
						}
						$("#typeSecond").append("<option value='"+type.type_id+"' "+str+">"+type.type_name+"</option>");
					}
				}
			});
		});
		
	});
	function AcceptClick(){
		$("#product_detail").val(editor.txt.html());
		$("#form").submit();
	}
	function imgChoice(){
		$("#imgup").click();
	}
	function imgUp(){
		var xhr = new XMLHttpRequest();
		var formData = new FormData();
		var file = $("#imgup")[0].files[0];
		formData.append(file.name,file);
		xhr.open("post","${basePath}/imgUp.do",true);
		xhr.onreadystatechange = function(){
					if(xhr.readyState==4 && xhr.status==200){
						var result = xhr.responseText;
						var info = result.split("|");
						if(result.indexOf("error")!=-1){
							$.fn.modalAlert(info[1],"error");
						}else{
							$("#product_pic").val(info[1]);
							$("#imgShow").attr("src","${basePath}/"+info[1]);
						}
					}
		}
		xhr.send(formData);
	}
	$.fn.modalAlert = function (content, type) {
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
	           btnclass: ['btn btn-primary']
	       });
	   }
</script>
</body>
</html>