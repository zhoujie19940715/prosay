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
        <link href="${basePath}/static/product/type.css" rel="stylesheet" type="text/css" />
</head>
<body>
 <!-- Horizontal Form -->
                <div class="box box-info">

                    <!-- form start -->
                    <form id="form" class="form-horizontal" method="post" action="typeEdit.do" >
                    	<input type="hidden" name="type_id" value="${pd.type_id}"/>
                        <div class="box-body edit-body">
                        <div class="form-group ">
                                <label for="type_name"  class="control-label">类型名称</label>
                                
                                    <input type="text" value="${pd.type_name}" class="form-control" id="type_name" name="type_name" placeholder="类型名称">
                               
                         </div>
                        <div class="form-group">
                                <label>商品父类型</label>
                                <select id="typeParent" name="type_parent" class="form-control">
                                  <option value="0">无父类型</option>
                                </select>
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
				if(type.type_id=="${pd.type_parent}"){
					str="selected";
				}
				$("#typeParent").append("<option value='"+type.type_id+"' "+str+">"+type.type_name+"</option>");
				
			}
		}
	});
	
	
	function AcceptClick(){
		$("#form").submit();
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
	
	
</script>
</body>
</html>