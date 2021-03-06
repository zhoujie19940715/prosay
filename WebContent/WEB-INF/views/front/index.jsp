<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>猿商</title>
<link href="${basePath}/static/superui/superui/min/css/superui.common.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${basePath}/static/content/ui/global/bootstrap/css/bootstrap.min.css">
<link href="${basePath}/static/content/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href="${basePath}/static/front/css/index.css" rel="stylesheet" type="text/css">
<link href="${basePath}/static/front/css/description.css" rel="stylesheet" type="text/css">
<link href="${basePath}/static/front/css/common.css" rel="stylesheet" type="text/css">
<link href="${basePath}/static/front/js/banner/banner.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@include file="header.jsp" %>
	<!-- 中间的内容区域 -->
	<section class="content main" >
		 <div id="banner1"></div>
		 <div class="index">
		 	<div class="mainContent">
		 		<!-- 新品推荐  start-->
		 		<div class="productList">
		 			<div class="header">
		 			<p><i class="fa fa-hand-o-right"></i>新品推荐</p></div>
		 			<div class="list">
		 				<ul>
		 					<c:forEach items="${newSales}" var="product">
		 					<li title="${product.sales_mark }">
		 					<input type="hidden" class="id" value="${product.product_id }"/>
		 						<a href="javascript:void(0)">
		 							<img src="${basePath}/${product.product_pic}" />
		 						</a>
		 						<p class="s_price">￥<span class="s_p"><fmt:formatNumber value="${product.product_price*product.product_discount}" pattern="0.00" /></span><s style="color:#666;font-size:16px">${product.product_price}</s></p>
		 						<p class="s_title">
		 						<c:if test="${fn:length(product.product_name)>30}">
		 							${fn:substring(product.product_name,0,30) }...
		 						</c:if>
		 						<c:if test="${fn:length(product.product_name)<=30}">
		 							${product.product_name}
		 						</c:if>
		 						</p>
		 						<a href="javascript:void(0)" class="s_btn">加入购物车</a>
		 					</li>
		 					</c:forEach>
		 					
		 					
		 				</ul>
		 			</div>
		 		</div>
		 		<div class="clear"></div>
		 		<!-- 新品推荐 END -->
		 		
		 		<!-- 热销商品 start-->
		 		<div class="productList">
		 			<div class="header">
		 			<p><i class="fa fa-hand-o-right"></i>热销商品</p></div>
		 			<div class="list">
		 				<ul>
		 						<c:forEach items="${hotSales}" var="product">
		 					<li title="${product.sales_mark }">
		 					<input type="hidden" class="id" value="${product.product_id }"/>
		 						<a href="javascript:void(0)">
		 							<img src="${basePath}/${product.product_pic}" />
		 						</a>
		 						<p class="s_price">￥<span class="s_p"><fmt:formatNumber value="${product.product_price*product.product_discount}" pattern="0.00" /></span><s style="color:#666;font-size:16px">${product.product_price}</s></p>
		 						<p class="s_title"><c:if test="${fn:length(product.product_name)>30}">
		 							${fn:substring(product.product_name,0,30) }...
		 						</c:if>
		 						<c:if test="${fn:length(product.product_name)<=30}">
		 							${product.product_name}
		 						</c:if></p>
		 						<a href="javascript:void(0)" class="s_btn">加入购物车</a>
		 					</li>
		 					</c:forEach>
		 				</ul>
		 			</div>
		 		</div>
		 		<div class="clear"></div>
		 		<!-- 热销商品 END -->
		 		<!-- 促销商品 start-->
		 		<div class="productList">
		 			<div class="header">
		 			<p><i class="fa fa-hand-o-right"></i>促销商品</p></div>
		 			<div class="list">
		 				<ul>
		 						<c:forEach items="${quickSales}" var="product">
		 					<li title="${product.sales_mark}">
		 					<input type="hidden" class="id" value="${product.product_id }"/>
		 						<a href="javascript:void(0)">
		 							<img src="${basePath}/${product.product_pic}" />
		 						</a>
		 						<p class="s_price">￥<span class="s_p"><fmt:formatNumber value="${product.product_price*product.product_discount}" pattern="0.00" /></span><s style="color:#666;font-size:16px">${product.product_price}</s></p>
		 						<p class="s_title">
		 						<c:if test="${fn:length(product.product_name)>30}">
		 							${fn:substring(product.product_name,0,30) }...
		 						</c:if>
		 						<c:if test="${fn:length(product.product_name)<=30}">
		 							${product.product_name}
		 						</c:if>
		 						</p>
		 						<a href="javascript:void(0)" class="s_btn">加入购物车</a>
		 					</li>
		 					</c:forEach>
		 				</ul>
		 			</div>
		 		</div>
		 		<div class="clear"></div>
		 		<!-- 促销商品 END -->
		 	</div>
		 </div>
	</section>
	<!-- 中间的内容区域 -->
	<!-- 底部区域 START -->
	<footer>
			<%@include file="footer.jsp" %>
	</footer>
	<div id="product_description">
	</div>
	<!-- 底部区域 END-->
	 <script src="${basePath}/static/content/ui/global/jQuery/jquery.min.js"></script>
	 <script src="${basePath}/static/content/ui/global/bootstrap/js/bootstrap.min.js"></script>
 	<script src="${basePath}/static/content/min/js/supershopui.common.js"></script>
	 <script src="${basePath}/static/front/js/banner/jquery.banner.js"></script>
	 <script src="${basePath}/static/front/js/index.js"></script>
	 <script src="${basePath}/static/front/js/header.js"></script>
	 <%@include file="car.jsp" %>
	 <script src="${basePath}/static/front/js/description.js"></script>
	 <script>
	
	 </script>
</body>
</html>