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
                    <form id="form" class="form-horizontal" method="post" action="editArea.do" >
                    	<input type="hidden" name="area_id_bak" value="${pd.area_id}"/>
                        <div class="box-body">
                             <div class="form-group"> 
                                <label for="area_id"  class="col-xs-2 control-label">区域编码</label>
                                <div class="col-xs-4">
                                    <input type="text" value="${pd.area_id}" class="form-control" id="area_id" name="area_id" placeholder="区域编码">
                                </div>
                             </div>
                             <div class="form-group"> 
                                <label for="area_name"  class="col-xs-2 control-label">区域名称</label>
                                <div class="col-xs-4">
                                    <input type="text" value="${pd.area_name}" class="form-control" id="area_name" name="area_name" placeholder="区域名称">
                                </div>
                             </div>
                             <div class="form-group"> 
                                <label for="area_name"  class="col-xs-2 control-label">排序</label>
                                <div class="col-xs-4">
                                    <input type="text" value="${pd.area_sort}" class="form-control" id="area_sort" name="area_sort" placeholder="排序">
                                </div>                               
                             </div> 
                             <div class="form-group">
                                <label for="area_storage" class="col-xs-2 control-label">上级区域编码</label>
                                <div class="col-xs-4">
                                    	<input type="text" value="${pd.parent_id}" class="form-control" id="parent_id" name="parent_id" placeholder="上级区域编码">
                                </div>  
                             </div>                              
                             <div class="form-group">
                              	 <label for="area_storage" class="col-xs-2 control-label">两级联动</label>
	                             <div class="col-xs-4">
	                                   <select id="areaFirst" name="parent_id">
	                                   	
	                                   </select>
	                                   <select id="areaSecond" name="parent_id">
	                                   	
	                                   </select>
	                                    <select id="areaThird" name="parent_id">
	                                   	
	                                   </select>
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
            $("#area_detail").val(html)
        }
		var msg = "${msg}";
		if(msg.length>0){
			if(msg.indexOf("成功")!=-1){
				$.fn.modalAlert(msg,"success");
				if("${isClose}".length>0){
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
					//location.reload();
				}
			}else{
				$.fn.modalAlert(msg,"error");
			}
			
		}
		editor.create();
		//加载一级商品类别
		 $.ajax({
			type:"post",
			url:"queryArea.do",
			data:{"parent_id":0},
			dataType:"JSON",
			success:function(result){
				for(var i = 0;i<result.length;i++){
					var area = result[i];
					var str = "";
					/*if(flag){
						if(type.type_id=="${pd.type_pid}"){
							str="selected";
						}
					}*/
					$("#areaFirst").append("<option value='"+area.area_id+"' "+str+">"+area.area_name+"</option>");
					
				}
				$("#areaFirst").change();
			}
		}); 
	
		 $("#areaFirst").change(function(){
			var _this = $(this);
			$("#areaSecond").empty();
			$("#areaThird").empty();
			$.ajax({
				type:"post",
				url:"queryArea.do",
				data:{"parent_id":_this.val()},
				dataType:"JSON",
				success:function(result){
					for(var i = 0;i<result.length;i++){
						var area = result[i];
						var str = "";
						if(flag){
							if(area.area_id=="${pd.area_id}"){
								str="selected";
								flag = false;
							}
						}
						$("#areaSecond").append("<option value='"+area.area_id+"' "+str+">"+area.area_name+"</option>");
						$("#areaSecond").change();
					}
				}
			});
		});
		 $("#areaSecond").change(function(){
				var _this = $(this);
				$("#areaThird").empty();
				$.ajax({
					type:"post",
					url:"queryArea.do",
					data:{"parent_id":_this.val()},
					dataType:"JSON",
					success:function(result){
						for(var i = 0;i<result.length;i++){
							var area = result[i];
							var str = "";
							if(flag){
								if(area.area_id=="${pd.area_id}"){
									str="selected";
									flag = false;
								}
							}
							$("#areaThird").append("<option value='"+area.area_id+"' "+str+">"+area.area_name+"</option>");
						}
					}
				});
			}); 
	});
	function AcceptClick(){
		$("#area_detail").val(editor.txt.html());
		$("#form").submit();
	}
	function imgChoice(){
		$("#imgup").click();
	}
	/* function imgUp(){
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
							$("#area_pic").val(info[1]);
							$("#imgShow").attr("src","${basePath}/"+info[1]);
						}
					}
		}
		xhr.send(formData);
	} */
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