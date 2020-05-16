<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 顶部区域 START -->
<script type="text/javascript">var sysPath = "${basePath}";var typeId="${typeId}";</script>
<header>
	<section class="content">
		<h1 class="logo">猿商</h1>
		<nav>
			<ul class="first">
				<li><a href="/">首页</a></li>
			</ul>
		</nav>
		<ul class="userInfo">
		
			<li id="login" <c:if test="${user != null }">style="display:none"</c:if>><a href="javascript:doLogin()">登陆</a>|<a href="${basePath}/user/reg.do">注册</a></li>
			
			
			<li id="userInfo"<c:if test="${user eq null }">style="display:none"</c:if>><img id="userImg" style="width:50px;height:50px;border-radius:50%;" src="${basePath}/${user.vip_header}"><span id="userName">${user.vip_name}</span>|<a href="${basePath}/user/logout.do">退出登陆</a></li>
	
		</ul>
	</section>
</header>
<div id="overDiv"></div>
<!-- 用户登陆的表单 START-->
<div  id="loginBox" style="display:none">
	<form class="form-horizontal">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">帐号</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="vip_account" id="vip_account" placeholder="帐号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" name="vip_password" id="vip_password" placeholder="密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"> 记住我
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
</div>
<!-- 用户登陆的表单 END-->
<script type="text/javascript">
 document.onready=function(){
	var css = $("<link href=\""+sysPath+"/static/front/css/dialog.css\" rel=\"stylesheet\" type=\"text/css\">");
	 $("head").append(css);
	var js =$("<script src=\""+sysPath+"/static/front/js/jquery.dialog.js\"><\/script>");
	$("body").append(js);
	if("${regMsg}".length>0){
		top.layer.msg("${regMsg}", {"fa-check-circle":"2", time: 2000, shift: 5},function(){
			doLogin();
		});
	}
 };
 function doLogin(){
	
	 $.dialog({
			id:"loginDiv",
			title:"用户登陆",
			content:$("#loginBox").html(),
			enterText:"登陆",
			cancelText:"取消",
			enter:function(){
				$.ajax({
					type:"post",
					url:sysPath+"/user/login.do",
					data:{"userName":$(".dialog .content #vip_account").val(),"password":$(".dialog .content #vip_password").val()},
					dataType:"JSON",
					success:function(msg){
						if(msg.result){
							 top.layer.msg("登陆成功", {"fa-check-circle":"2", time: 2000, shift: 5},function(){
								 $("#dialogloginDiv").hide(500,function(){
										$("#dialogloginDiv").remove();
								});
								$("#screenloginDiv").hide(500,function(){
									$("#screenloginDiv").remove();
								});
							 });
					         top.$(".layui-layer-msg").find('i.2').parents('.layui-layer-msg').addClass('layui-layer-msg-success');
					         /*
					         	
					         */
					        $("#login").hide();
					        $("#userInfo").show();
					        $("#userImg").attr("src",sysPath+"/"+msg.headerImg);
					        $("#userName").html(msg.userName);
						}else{
							top.layer.msg("登陆失败", {"fa-times-circle":"2", time: 4000, shift: 5});
					         top.$(".layui-layer-msg").find('i. fa-check-circle').parents('.layui-layer-msg').addClass('layui-layer-msg-error');
						}
					}
				});
			},
			cancel:function(){
				
			},
			width:380,
			height:250
		});
 }
</script>
	<!-- 顶部区域 END -->