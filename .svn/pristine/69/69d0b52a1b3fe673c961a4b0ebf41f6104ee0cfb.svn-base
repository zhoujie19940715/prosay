<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>没有访问权限</title>
	<style>
		*{
				margin: 0;
				padding: 0;
			}
			body{
				background: #d2f5f1;
			}
			.mainBox{
				width: 100%;
				margin:0 auto;
				position: relative;
				overflow: hidden;
			}
			.mainBox>img{
				position: absolute;
			}
			.mainBox .yun0{
				right: -140px;
				top: 30px;
				animation: yun0Animation 50s;
			}
			.mainBox .yun1{
				left: 5%;
				top: 48%;
				animation: yun1Animation 60s;
			}
			.mainBox .yun2{
				left: 16%;
				top: 35%;
				animation: yun2Animation 50s;
			}
			.mainBox .bird{
				left: 27%;
				top: 15%;
				animation: birdAnimation 3s infinite;
			}
			.mainBox .san{
				left: 10%;
				top: 20%;
				animation: sanAnimation 3s 0s;
			}
			.mainBox .tipInfo{
				position: absolute;
			    z-index: 999;
			    width: 360px;
			    margin-left: 150px;
			    background: rgba(192, 237, 232, 0.7);
			    border: 4px solid #c0ece7;
			    border-radius: 5px;
			    border-color: rgba(192, 237, 232, 0.7);
			}
			.mainBox .tipInfo .in{
			    padding: 0 10%;
				background: #fff;
			}
			.mainBox .tipInfo .in .textThis h2{
				font-size: 30px;
			    color: #e94c3c;
			    border-bottom: 1px dashed #aacdd5;
			    padding: 18px 0;
			    line-height: 50px;
			}
			.mainBox .tipInfo .in .textThis p{
				padding: 30px 0 50px 0;
			    text-align: center;
			    color: #289575;
			}
			.mainBox .tipInfo .in .textThis p span{
				margin:0 20px;
			}
			.mainBox .tipInfo .in .textThis p span a{
				margin: 0 10px;
    			color: #e94c3c;
			}
			@keyframes sanAnimation{
				0%{
					opacity: 0;
					transform: translateY(-400px);
				}
				100%{
					opacity: 1;
					transform: translateY(0px);
				}
			}
			@keyframes yun0Animation{
				0%{
					right: -140px;
				}
				100%{
					right: 118%;
				}
				
			}
			@keyframes yun1Animation{
				0%{
					left:10%;
				}
				100%{
					left:100%;
				}
			}
			@keyframes yun2Animation{
				0%{
					left:18%;
				}
				100%{
					left:-18%;
				}
			}
			@keyframes birdAnimation{
				0%{
					margin-top: 0px;
				}
				50%{
					margin-top: 6px;
				}
				100%{
					margin-top: 0px;
				}
			}
	</style>
	<script src="${basePath}/static/content/ui/global/jQuery/jquery.min.js"></script>
	<script type="text/javascript">
           $(function() {
               var h = $(window).height();
               //alert(h);
               $('body').height(h);
               $('.mainBox').height(h);
               centerWindow(".tipInfo");
           });

           //2.将盒子方法放入这个方，方便法统一调用
           function centerWindow(a) {
               center(a);
               //自适应窗口
               $(window).bind('scroll resize',
                       function() {
                           center(a);
                       });
           }

           //1.居中方法，传入需要居中的标签
           function center(a) {
               var wWidth = $(window).width();
               var wHeight = $(window).height();
               var boxWidth = $(a).width();
               var boxHeight = $(a).height();
               var scrollTop = $(window).scrollTop();
               var scrollLeft = $(window).scrollLeft();
               var top = scrollTop + (wHeight - boxHeight) / 2;
               var left = scrollLeft + (wWidth - boxWidth) / 2;
               $(a).css({
                   "top": top,
                   "left": left
               });
           }
	</script>
</head>
<body>
<div class="mainBox">
           <img src="${basePath}/static/img/yun0.png" alt="" class="yun yun0" />
           <img src="${basePath}/static/img/yun1.png" alt="" class="yun yun1" />
           <img src="${basePath}/static/img/yun2.png" alt="" class="yun yun2" />
           <img src="${basePath}/static/img/bird.png" alt="" class="bird" />
           <img src="${basePath}/static/img/san.png" alt="" class="san" />
           <div class="tipInfo">
               <div class="in">
                   <div class="textThis">
                       <h2>您没有访问权限</h2>
                       <p>
                       	<span>
                       		页面自动
                       		<a id="href" href="javascript:top.window.location.href='${basePath}${url}'">跳转</a>
                       	</span>
                       	<span>
                       		等待
                       		<b id="wait">6</b>秒
                       	</span>
                       </p>
                       
                   </div>
               </div>
           </div>
       </div>
       <script type="text/javascript">                            
       	(function() {
               var wait = document.getElementById('wait'), href = document.getElementById('href').href;
               var interval = setInterval(function() {
                   var time = --wait.innerHTML;
                   if (time <= 0) {
                       top.window.location.href="${basePath}${url}";
                       clearInterval(interval);
                   }
                   ;
               }, 1000);
           })();
       </script>
	</body>
</html>