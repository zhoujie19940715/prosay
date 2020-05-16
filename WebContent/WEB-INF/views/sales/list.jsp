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
             营销信息列表
            <small>营销信息的查询与管理</small>
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
                                        <label class="control-label col-sm-1" for="sale_type">营销类型</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="sale_type" value="${page.pd.sale_type}" id="sale_type">
                                        </div>
                                        <label class="control-label col-sm-1" for="txt_search_statu">营销时间</label>
                                        <div class="col-sm-2">
                                             <div class="input-group date form_datetime input-large">
                                                    <input type="text" name="chosed_time" value="${page.pd.chosed_time}"  size="16" readonly class="form-control">
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
                            <button id="btn_delete" type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                            </button>
                        </div>
                        <div class="table-scrollable  fixed-table-container">
                            <table class="table-striped table-hover table" id="table">
                                <thead>
                                    <tr>
                                        <th data-checkbox="true"><input id="checkAll" type="checkbox"/></th>
                                        <th>商品id</th>
                                        <th>营销语</th>
                                        <th>营销类型</th>
                                        <th>商品图片</th>
                                        <th>营销开始时间</th>
                                        <th>结束时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.data}" var="sales">
                                <tr>
                                	<td><input type="checkbox" name="checkId" value="${sales.sales_id}"/></td>
                                	<td>${sales.product_id}</td>
                                	<td>${sales.sales_mark}</td>
                                	<td>${sales.sales_type}</td>
                                	<td><img style="width:100px;" src="${basePath}/${sales.pic}"/></td>
                                	<td>${sales.sales_starttime}</td>
                                	<td>${sales.sales_endtime}</td>
                                	<td>
                                	<a href="javascript:edit(${sales.sales_id});" class="btn btn-icon-only purple"><i class="fa fa-edit"></i></a>
                                	<a href="javascript:del(${sales.sales_id});" class="btn btn-icon-only red"> <i class="fa fa-times"></i></a>
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
<script src="${basePath}/prosay/common.js" type="text/javascript"></script>
</body>
<script type="text/javascript">
function edit(id){
 	 $.fn.modalOpen({
          id: "Form",
          title: '营销信息修改',
          url: 'productSalesEdit.do?sales_id='+id,
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
				url:"delProductSales.do",
				data:{"sales_id":id},
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
$("#btn_add").click(function(){
	  $.fn.modalOpen({
       id: "saleForm",
       title: '营销信息新增',
       url: 'productSalesAdd.do',
       width: "750px",
       height: "550px",
       callBack: function (iframeId) {
           var obj = document.getElementById(iframeId);
           obj.contentWindow.AcceptClick();
       }
   });
});
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
	   $.fn.modalAlert("没有选择任何信息！","warning");
	   return ;
  }
  ids = ids.substring(0,ids.length-1);
	  $.fn.modalConfirm("确定删除已选吗？",function(result){
	   if(result){
		   $.ajax({
				type:"post",
				url:"batchDelSales.do",
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
</script>

</html>