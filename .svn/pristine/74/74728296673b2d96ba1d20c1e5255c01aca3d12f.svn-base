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
 <form id="vipForm" class="form-horizontal" method="post" action="editVip.do" >
    	<input type="hidden" name="vip_id" value="${pd.vip_id}"/>
        <div class="box-body">
            <div class="form-group">
                <label for="product_name" class="col-xs-2 control-label">会员昵称</label>
                <div class="col-xs-2">
                    <input type="text" value="${pd.vip_name}" class="form-control" id="vip_name" name="vip_name" placeholder="会员昵称">
                </div>
               
                <label class="col-xs-2 control-label">真实姓名</label>
                <div class="col-xs-2">
                    <input type="text" value="${pd.vip_realname}" class="form-control" id="vip_realname" name="vip_realname" placeholder="真实姓名">
                </div>
                <label class="col-xs-1 control-label">性别</label>
                <div class="col-xs-2">
                    <input type="text" value="${pd.vip_sex}" class="form-control" id="vip_sex" name="vip_sex" placeholder="性别">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 control-label">会员帐号</label>
                <div class="col-xs-4">
                    <input type="text" value="${pd.vip_account}" class="form-control" id="vip_account" name="vip_account" placeholder="会员帐号">
                </div>
                <label class="col-xs-2 control-label">会员密码</label>
                <div class="col-xs-4">
                    <input type="text" value="${pd.vip_password}" class="form-control" id="vip_password" name="vip_password" placeholder="会员帐号">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 control-label">会员等级</label>
                <div class="col-xs-2">
                    <input type="text" value="${pd.vip_level}" class="form-control" id="vip_level" name="vip_level" placeholder="会员等级">
                </div>
                <label class="col-xs-2 control-label">会员积分</label>
                <div class="col-xs-2">
                    <input type="text" value="${pd.vip_score}" class="form-control" id="vip_score" name="vip_score" placeholder="会员积分">
                </div>
                <label class="col-xs-2 control-label">联系方式</label>
                <div class="col-xs-2">
                    <input type="text" value="${pd.vip_tel}" class="form-control" id="vip_tel" name="vip_tel" placeholder="联系方式">
                </div>
            </div>
             <div class="form-group">
                <label class="col-xs-2 control-label">会员邮箱</label>
                <div class="col-xs-4">
                    <input type="text" value="${pd.vip_email}" class="form-control" id="vip_email" name="vip_email" placeholder="会员邮箱">
                </div>
               <label class="control-label col-xs-2" for="sales_starttime ">会员出生时间</label>
                <div class="col-xs-3">
                 <div class="input-group date form_datetime input-large">
                  <input type="text" id="vip_birthday" name="vip_birthday" value="${pd.vip_birthday}"  size="16" readonly class="form-control">
                  <span class="input-group-btn">
                    <button class="btn default date-set" type="button">
                      <i class="fa fa-calendar"></i>
                    </button>
                  </span>
                 </div>
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
         minView: "month",
         format: "yyyy-mm-dd",
         pickerPosition: ("bottom-left")
     });
	function AcceptClick(){
		$("#vipForm").submit();
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