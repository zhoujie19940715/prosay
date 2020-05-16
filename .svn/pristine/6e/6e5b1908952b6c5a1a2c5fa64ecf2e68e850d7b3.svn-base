<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>营销</title>
 <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${basePath}/static/content/ui/global/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link href="${basePath}/static/content/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <!-- Theme style -->
        <link rel="stylesheet" href="${basePath}/static/content/adminlte/dist/css/skins/_all-skins.min.css">
        <link href="${basePath}/static/content/min/css/supershopui.common.min.css" rel="stylesheet" />
</head>
<body>
 <form id="saleForm" class="form-horizontal" method="post" action="editProductSales.do" >
    	<input type="hidden" name="sales_id" value="${pd.sales_id}"/>
        <div class="box-body">
            <div class="form-group">
                <label for="product_name" class="col-xs-2 control-label">商品名称</label>
                <div class="col-xs-4">
                    <input type="text" value="${pd.product_id}" class="form-control" id="product_id" name="product_id" placeholder="商品id">
                </div>
               
                <label class="col-xs-2 control-label">营销类型</label>
                <div class="col-xs-4">
                    <select id="type" name="sales_type">
                    	<option value=0 <c:if test="${pd.sales_type eq 0}">selected="selected"</c:if>>新品推荐</option>
		                <option value=1 <c:if test="${pd.sales_type eq 1}">selected="selected"</c:if>>促销商品</option>
		                <option value=2 <c:if test="${pd.sales_type eq 2}">selected="selected"</c:if>>热销商品</option>
		                <option value=4 <c:if test="${pd.sales_type eq 3}">selected="selected"</c:if>>banner推荐</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="product_storage" class="col-xs-2 control-label">营销语</label>
                <div class="col-xs-8">
                    	<input type="text" value="${pd.sales_mark}" class="form-control" id="sales_mark" name="sales_mark" placeholder="营销语">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-2" for="sales_starttime ">营销开始时间</label>
                <div class="col-xs-3">
                 <div class="input-group date form_datetime input-large">
                  <input type="text" id="sales_starttime" name="sales_starttime" value="${pd.sales_starttime}"  size="16" readonly class="form-control">
                  <span class="input-group-btn">
                    <button class="btn default date-set" type="button">
                      <i class="fa fa-calendar"></i>
                    </button>
                  </span>
                 </div>
                </div>
                <label class="control-label col-xs-2" for="sales_endtime">营销结束时间</label>
                <div class="col-xs-3">
             		 <div class="input-group date form_datetime input-large">
                	  <input type="text" id="sales_endtime" name="sales_endtime" value="${pd.sales_endtime}"  size="16" readonly class="form-control">
                	  <span class="input-group-btn">
                  		 <button class="btn default date-set" type="button">
                    		 <i class="fa fa-calendar"></i>
                 	 	 </button>
                	  </span>
                	 </div>
                </div>
            </div>            
             <div class="form-group">
                <label for="sales_pic" class="col-xs-2 control-label">商品展示图</label>
                <div class="col-xs-10">
                		<input type="hidden" name="sales_pic"  id="sales_pic" value="${pd.sales_pic}"/>
                		<input type="file" id="imgup" onchange="imgUp()" style="display:none" id="filePic" accept="image/*"/>
                    	<a href="javascript:void(0)" onclick="imgChoice()"><img id="imgShow" style="width:100px" <c:if test="${empty pd.product_pic}">src="${basePath}/static/img/upload.png"</c:if><c:if test="${not empty pd.sales_pic}">src="${basePath}/${pd.sales_pic}"</c:if>></a>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="${basePath}/static/content/ui/global/jQuery/jquery.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${basePath}/static/content/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/content/min/js/supershopui.common.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">

 	$(".form_datetime").datetimepicker({
         language: 'zh-CN',
         autoclose: true,
         minView: "hour",
         format: "yyyy-mm-dd hh:ii",
         pickerPosition: ("bottom-left")
     });
	function AcceptClick(){
		$("#saleForm").submit();
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
							$("#sales_pic").val(info[1]);
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