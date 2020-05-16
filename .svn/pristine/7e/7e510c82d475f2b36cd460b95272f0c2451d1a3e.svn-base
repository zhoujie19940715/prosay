<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>猿商</title>
<link href="${basePath}/static/superui/superui/min/css/superui.common.min.css" rel="stylesheet" />
    <!-- 全局主题样式 -->
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="${basePath}/static/content/ui/global/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link href="${basePath}/static/content/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Theme style -->
    <link href="${basePath}/static/content/min/css/supershopui.common.min.css" rel="stylesheet" />
    <link href="${basePath}/static/content/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}/static/front/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="../header.jsp"%>
<section class="content main" >
	<div class="mainContent">
		<form action="saveUser.do" id="form1" method="post" class="form-horizontal">
	        <div class="form-body">
	        	<div class="form-group">
	        		<label class="col-md-3 control-label">用户名</label>
	        		<div class="col-md-5">
	        			<input type="text" name="vip_account" value="${pd.vip_account}" id="vip_account" class="form-control" placeholder="请填写登录用户名" validate='[{"type":"reg","reg":"^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,19}$","msg":"必须以字母开头可以填写字母或者数字以及. _ 用户名必须是6-20位"},{"type":"ajax","msg":"用户名已注册！","url":"checkAccount.do"}]'/>
	        		</div>
	        		<span class="help-block" >*用户名可以大小写字母与数字</span>
	        	</div>
	        	<div class="form-group">
	        		<label class="col-md-3 control-label">昵称</label>
	        		<div class="col-md-5">
	        			<input type="text" class="form-control" value="${pd.vip_name}" name="vip_name" id="vip_name"  placeholder="请填网站昵称" validate='[{"type":"notNull","msg":"不能为空！"}]'/>
	        		</div>
	        		<span class="help-block" >*昵称可以是中文</span>
	        	</div>
	        	<div class="form-group">
	        		<label class="col-md-3 control-label">性别</label>
	        		<div class="col-md-5">
	                      <label class="radio-inline">
	                          <input type="radio" name="vip_sex" id="optionsRadios1" value="女" checked>女</label>
	                      <label class="radio-inline">
	                          <input type="radio" name="vip_sex" id="optionsRadios2" value="男">男</label>
	                      <label class="radio-inline">
	                          <input type="radio" name="vip_sex" id="optionsRadios3" value="保密 ">保密</label>
		        	</div>
		        	<span class="help-block" >*根据您的性别正确选择</span>
	        	</div>
	        	<div class="form-group">
	        		<label class="col-md-3 control-label">真实姓名</label>
	        		<div class="col-md-5">
	                      <input type="text" class="form-control" validate='[{"type":"notNull","msg":"不能为空！"}]' name="vip_realname" value="${pd.vip_realname}" id="vip_realname"  placeholder="请填写真实姓名" />
		        	</div>
		        	<span class="help-block" >*请填写您的真实姓名</span>
	        	</div>
	        	<div class="form-group">
		            <label class="control-label col-md-3" for="product_discount ">头像</label>
		            <div class="col-md-8">
		            	<input type="hidden" name="vip_header" id="vip_header" />
		                <input type="file" id="filePic"  onchange="doFileUp()" accept="image/*" style="display:none"/>
		                <a href="javascript:void(0)" onclick="doFileChoice()" ><img id="showPic" style="width:100px" src="${basePath}/static/img/upload.png"/></a>
		            </div>                                      
	 			</div>
	        	<div class="form-group">
	        		<label class="col-md-3 control-label">手机号码</label>
	        		<div class="col-md-5">
	                      <input type="text" class="form-control" value="${pd.vip_tel}"  name="vip_tel" id="vip_tel"  placeholder="请填写手机号码" validate='[{"type":"reg","reg":"^[1-9]{1}[0-9]{10}$","msg":"格式不正确！"}]' />
		        	</div>
		        	<span class="help-block" >*填写正确的手机号码</span>
	        	</div>
	        	<div class="form-group">
	        		<label class="col-md-3 control-label">生日</label>
	        		<div class="col-md-5">
	                      <input type="text" readonly class="form-control" value="${pd.vip_birthday}"  name="vip_birthday" id="vip_birthday"  placeholder="请选择生日" />
		        	</div>
		        	<span class="help-block" >*选择正确的出生日期</span>
	        	</div>
	        	<div class="form-group">
	        		<label class="col-md-3 control-label">邮箱</label>
	        		<div class="col-md-5">
	                      <input type="text" class="form-control"  value="${pd.vip_email}" name="vip_email" id="vip_email"  placeholder="请填写邮箱地址" validate='[{"type":"reg","reg":"^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$","msg":"格式不正确！"}]'/>
		        	</div>
		        	<span class="help-block"></span>
	        	</div>
	        	<div class="form-group">
	        		<label class="col-md-3 control-label">邮箱验证码</label>
	        		<div class="col-md-3">
	                      <input type="text" class="form-control" name="validateCode" id="validateCode" autocomplete="off"  placeholder="请填写收到的验证码" validate='[{"type":"ajax","url":"checkEmailCode.do","msg":"验证码不正确！"}]'/>
		        	</div>
	        		<div class="col-md-2">
		        	<a href="javascript:void(0)" class="btn btn-primary" id="sendCode" value="发送验证码">发送验证码</a>
		        	</div>      	
		        	<span class="help-block"></span>
	        	</div>
	        	<div class="form-group">
	        		<label class="col-md-3 control-label">密码</label>
	        		<div class="col-md-5">
	                      <input type="password" class="form-control" name="vip_password" id="regvip_password"  placeholder="请输入用于登录的密码" validate='[{"type":"notNull","msg":"密码不能为空！"}]'/>
		        	</div>
		        	<span class="help-block" >*请输入密码</span>
	        	</div>
	        	<div class="form-group">
	        		<label class="col-md-3 control-label">确认密码</label>
	        		<div class="col-md-5">
	                      <input type="password" class="form-control" id="sure_password"  placeholder="请输入确认密码" validate='[{"type":"equalsTo","msg":"两次输入的密码不一致！","target":"#regvip_password"},{"type":"notNull","msg":"密码不能为空！"}]'/>
		        	</div>
		        	<span class="help-block" >*请再次输入密码</span>
	        	</div>
	        </div>
	        <div class="form-actions center" style="text-align:center">
	       		<button type="reset" class="btn default">重置</button>
	        	<button type="submit" class="btn green">提交</button>
	        	
	        </div>
	    </form>                               
	</div>
</section>

 <script src="${basePath}/static/content/ui/global/jQuery/jquery.min.js"></script>
 <script src="${basePath}/static/front/js/header.js"></script>
 <script src="${basePath}/static/content/ui/global/bootstrap/js/bootstrap.min.js"></script>
 <script src="${basePath}/static/content/min/js/supershopui.common.js"></script>
 <script src="${basePath}/static/content/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
 <script src="${basePath}/static/content/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
 <script src="${basePath}/static/front/js/jquery.myvalidate.js"></script>
<script type="text/javascript">
$(function(){
	if("${errorMsg}".length>0){
		top.layer.alert("注册失败！", {
            icon:"fa-times-circle",
            title: "系统提示",
            btn: ['确认'],
            btnclass: ['btn btn-primary']
        });
	}
	$("#form1").prosayVali();
	$("#vip_birthday").datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        pickerPosition: ("bottom-left")
    });
	var flag = true;
	$("#sendCode").click(function(){
		if(!flag){
			return;
		}
		flag = false;
		var email = $("#vip_email").val();
		$.ajax({
			type:"post",
			url:"sendEmailCode.do",
			data:{"email":email},
			dataType:"JSON",
			success:function(content){
				if(content.result){
					showSuccess($("#sendCode"),"验证码发送成功，请查收！");
					var step = 30;
					$("#sendCode").prop("disabled",true);
					var tmp = setInterval(function(){
						step--;
						$("#sendCode").html(step+"秒后重新发送！");
						if(step<0){
							flag = true;
							clearInterval(tmp);
							$("#sendCode").html("发送验证码");
							$("#sendCode").prop("disabled",false);
						}
					},1000);
				}else{
					showErrorInfo($("#sendCode"),"验证码发送失败，请检查邮箱或与管理员取得联系！");
					flag = true;
				}
			}
		});
	});
});
function doFileChoice(){
	$("#filePic").click();
}
function doFileUp(){
	var xhr = new XMLHttpRequest();
	var formData = new FormData();
	var file = $("#filePic")[0].files[0];
	formData.append(file.name,file);
	xhr.open("post","${basePath}/imgUp.do",true);
	xhr.onreadystatechange = function(){
				if(xhr.readyState==4 && xhr.status==200){
					var result = xhr.responseText;
					var info = result.split("|");
					if(result.indexOf("error")!=-1){
						$.fn.modalAlert(info[1],"error");
					}else{
						$("#vip_header").val(info[1]);
						$("#showPic").attr("src","${basePath}/"+info[1]);
					}
				}
	}
	xhr.send(formData);
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
</script>
<%@include file="../footer.jsp" %>
</body>
</html>