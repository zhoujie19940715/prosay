<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>猿商</title>
<link href="${basePath}/static/superui/superui/min/css/superui.common.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${basePath}/static/content/ui/global/bootstrap/css/bootstrap.min.css">
<link href="${basePath}/static/content/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href="${basePath}/static/front/css/index.css" rel="stylesheet" type="text/css">
<link href="${basePath}/static/front/css/description.css" rel="stylesheet" type="text/css">
<link href="${basePath}/static/front/css/common.css" rel="stylesheet" type="text/css">
<link href="${basePath}/static/front/css/orderInfo.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="../header.jsp" %>
<section class="content main" >
<div class="index">
	<div class="mainContent">
		<form action="doOrder.do" method="post" id="form1" class="form-horizontal">
			<div class="form-body">
				<div class="form-group order-block">
				<div class="orderLabel">
					送货地址
				</div>
				<div class="addressLists">
					<c:forEach items="${address}" var="addr">
						<c:choose>
							<c:when test="${addr.addr_default eq 1}">
								<div class="defaultAddress">
									<label><input type="radio" checked value="${addr.addr_id}" name="address_id">${addr.addr_info }</label>
								</div>
								<a href="javascript:void(0)" class="hidshow">选择其他</a>
							</c:when>
							<c:otherwise>
								<div class="otherAdress">
									<label><input type="radio" value="${addr.addr_id}" name="address_id">${addr.addr_info }</label>
								</div>
								
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				</div>
				<div class="form-group order-block">
					<div class="orderLabel">
					商品列表
				</div>
				<div class="addressLists">
					 <div class="productInfo">
							<ul class="title">
								<li class="li-img">图片</li>
								<li class="li-text">商品名称</li>
								<li class="li-num">数量</li>
								<li class="li-price">单价</li>
								<li class="li-total">总价</li>
							</ul>
							<div class="clear"></div>
					</div>
					<c:set var="total" value="0"/>
					<c:forEach items="${products}" var="item">
					<c:set var="total" value="${total+item.product_total}"/>
						<div class="productInfo">
							<ul class="content">
								<li class="li-img"><img src="${basePath}/${item.product_pic}"></li>
								<li class="li-text">
			 						<div class="text">${item.product_name}</div>
		 						</li>
								<li class="li-num">${item.product_num}</li>
								<li class="li-price">
								 <fmt:formatNumber value="${item.product_price}" pattern="0.00"/>
								</li>
								<li class="li-total">
								 <fmt:formatNumber value="${item.product_total}" pattern="0.00"/>
								</li>
							</ul>
							<div class="clear"></div>
						</div>
					</c:forEach>
				</div>
				</div>
				
				<div class="form-group order-block">
					<div class="orderLabel">
					支付金额
					</div>
					<div class="total-count">
					 合计金额：<fmt:formatNumber value="${total}" pattern="0.00" />
					 <input type="hidden" name="total_money" value="${total}"/>
					</div>
				</div>
			</div>
			<div class="form-actions center">
				<button type="reset" class="btn default">重置</button>
				<button type="submit" class="btn green">提交</button>
			</div>
		</form>
	</div>
</div>
</section>
<%@include file="../footer.jsp" %>
<!-- 底部区域 END-->
	 <script src="${basePath}/static/content/ui/global/jQuery/jquery.min.js"></script>
	 <script src="${basePath}/static/content/ui/global/bootstrap/js/bootstrap.min.js"></script>
 	<script src="${basePath}/static/content/min/js/supershopui.common.js"></script>
	 <script src="${basePath}/static/front/js/banner/jquery.banner.js"></script>
	 <script src="${basePath}/static/front/js/header.js"></script>
<script type="text/javascript">
	$(function(){
		$(".hidshow").click(function(){
			$(".otherAdress").toggle();
		});
	});
</script>	 
</body>
</html>