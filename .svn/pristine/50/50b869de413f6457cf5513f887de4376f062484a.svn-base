<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
    <title>猿商后台管理登陆</title>
    <link href="${basePath}/static/content/ui/css/layout.css" rel="stylesheet" />
    <link href="${basePath}/static/content/ui/css/login.css" rel="stylesheet"/>
    <style>
        .ibar {
            display: none;
        }
    </style>
</head>

<body class="login-bg">
    <div class="main ">
        <!--登录-->
        <div class="login-dom login-max">
            <div class="logo text-center">
                <a href="#">
                    <img src="${basePath}/static/content/ui/img/logo.png" width="180px" height="180px" />
                </a>
            </div>
            <div class="login container " id="login">
                <p class="text-big text-center logo-color">
                   猿商品质 用心服务
                </p>

                <p class="text-center margin-small-top logo-color text-small">
                  Prosay
                </p>
                <form class="login-form" action="doLogin.do" method="post" autocomplete="off">
                    <div class="login-box border text-small" id="box">
                        <div class="name border-bottom">
                            <input type="text" placeholder="账号" id="username" name="username" datatype="*" value="${username}" nullmsg="请填写帐号信息" />
                        </div>
                        <div class="pwd">
                            <input type="password" placeholder="密码" datatype="*" id="password" name="password" value="${password}" nullmsg="请填写帐号密码" />
                        </div>
                        <div class="valicode">
                            <input type="text" placeholder="验证码" datatype="*" id="code" name="code"  nullmsg="请填写验证码" /><img id="codeImg" src="${basePath}/admin/code.do"/>
                        </div>
                    </div>
                    <input type="submit" class="btn text-center login-btn" value="立即登录" />
                </form>
                <div class="forget">
                   
                </div>
            </div>
        </div>
        <div class="footer text-center text-small ie">
            Copyright 2017-2020 版权所有 ©Prosay 2015-2018
            <span class="margin-left margin-right">|</span>
            <script src="#" language="JavaScript"></script>
        </div>
        <div class="popupDom">
            <div class="popup text-default">
            </div>
        </div>
    </div>
</body>

<script src="${basePath}/static/content/ui/global/jQuery/jquery.min.js"></script>
<script type="text/javascript">
    function popup_msg(msg) {
        $(".popup").html("" + msg + "");
        $(".popupDom").animate({
            "top": "0px"
        }, 400);
        setTimeout(function () {
            $(".popupDom").animate({
                "top": "-40px"
            }, 400);
        }, 2000);
    }
	if("${msg}".length>0){
		popup_msg("${msg}");
	}
    /*动画（注册）*/
    $(document).ready(function (e) {
     	$("#codeImg").click(function(){
     		$(this).attr("src","code.do?"+Math.random());	
     		
     	});
     	$("#code").blur(function(){
     		var code = $(this).val();
     		$.ajax({
     			type:"post",
     			url:"validateCode.do",
     			data:{"code":code},
     			success:function(result){
     				if(result=="true"){
     					popup_msg("验证码填写正确！");
     				}else{
     					popup_msg("验证码填写错误！");
     				}
     			}
     		});
     	});

    });
</script>
</html>