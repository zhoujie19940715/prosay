<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>404</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<style>
	* { 
	margin:0;
	padding:0;
}
html, body { 
	height:100%;
}
body { 
	background:url(${basePath}/static/img/tail-top.gif) left top repeat-x #140100;
	font-family:Georgia, "Times New Roman", Times, serif;
	font-size:100%; 
	line-height:1.5em;
	color:#cbe0ff;
}

img {
	border:0; 
	vertical-align:top; 
	text-align:left;
}

ul, ol { 
	list-style:none;
}


.wrapper { 
	width:100%;
	overflow:hidden;
}


/*==== GLOBAL =====*/
#main {
	width:1000px; 
	margin:0 auto;
	background:url(${basePath}/static/img/main-bg.jpg) no-repeat left top;
	font-size:1.3125em;
	position:relative;
}

#header {
	height:190px;
}
#content {
	min-height:457px;
	height:auto !important;
	height:457px;
}
#footer {
	font-family:Tahoma, Geneva, sans-serif;
	color:#333;
	font-size:12px;
	text-align:center;
	padding:6px 0 0 0;
}
	#footer a {
		color:#333;
	}

p {
	margin-bottom:8px;
	text-align:center;
	font-size:21px;
	padding:0 35px;
}

.tail-left {
	position:absolute;
	left:0;
	top:0;
	width:50%;
	height:508px;
	background:url(${basePath}/static/img/tail-left.gif) left top repeat-x;
}

/*----- txt, links, lines, titles -----*/
a {
	color:#fff; 
	outline:none;
}
a:hover{
	text-decoration:none;
}

h1 {
	font-size:55px;
	line-height:1.2em;
	font-weight:normal;
	color:#cbe0ff;
	text-align:center;
	padding:62px 0 0 0;
	font-variant:small-caps;
	text-transform:capitalize;
}
	h1 span {
		display:block;
		font-size:20px;
		line-height:25px;
		font-variant:normal;
		text-transform:uppercase;
	}
	h1 strong {
		font-weight:normal;
		font-size:1.11em;
	}


/*===== content =====*/
#content .nav {
	position:relative;
	height:368px;
}
	#content .nav li {
		position:absolute;
		font-size:14px;
		line-height:1.2em;
		font-weight:bold;
		font-variant:small-caps;
		text-transform:capitalize;
	}
	#content .nav li.home {
		left:451px;
		top:300px;
		font-size:18px;
	}
	#content .nav li.site_map {
		left:273px;
		top:263px;
	}
	#content .nav li.search {
		right:230px;
		top:261px;
	}
		#content .nav li a {
			color:#fff;
			text-decoration:none;
		}
		#content .nav li a:hover {
			text-decoration:underline;
		}

/*==========================================*/
	
</style>
</head>

<body>
	<div class="tail-left"></div>
	<div id="main">
		<!-- header -->
		<div id="header">
			<h1>这里没有活着的页面!<span><strong>404</strong> error - 实在找不到！</span></h1>
		</div>
		<!-- content -->
		<div id="content">
			<ul class="nav">
            
         </ul>
        
		</div>
		<!-- footer -->
		<div id="footer">
      	
      </div>
	</div>
</body>
</html>