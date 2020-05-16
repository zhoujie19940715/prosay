 $('li').find('img').click(function(){
	 var id = $(this).parent().parent().find(".id").val();
		$.ajax({
			type:"post",
			url:"getDescription.do",
			data:{"id":id},
			dataType:"JSON",
			success:function(result){
				//console.log(result)
				var product_detail = result.product_detail;
				var product_discount = result.product_discount;
				//var product_id = result.product_id;
				var product_lookamount = result.product_lookamount;
				var product_name = result.product_name;
				var product_outline = result.product_outline;
				var product_price = result.product_price;
				var product_storage = result.product_storage;
				var product_storetime = result.product_storetime;
				var product_pic = sysPath+"/"+result.product_pic;
				var html = "<div class='description-wrapper'>"+
				"<div class='d-wrapper'>"+
					"<img class='d-img' src='"+product_pic+"'/>"+
					"<span class='d-content'>"+
						"<mark>商品名称</mark>:"+
						"<div class='d-name'>"+product_name+"</div>"+
						"<mark>商品简介</mark>:"+
						"<div class='d-outline'>"+product_outline+"</div>"+
						"<hr/>"+
						"<span class='d-new-price'>现价:￥"+product_price*product_discount+"</span>"+
						"<span class='d-old-price'>原价:"+product_price+"</span>"+
						"<span class='d-storage'>库存:"+product_storage+"</span>"+
						"<hr/>"+
						"<span class='d-lookamount'>浏览次数:"+product_lookamount+"</span>"+
						"<a href='javascript:addProduct("+product_price+","+id+")' class='d-btn'>加入购物车</a>"+ 
					"</span>"+
					"<hr/>"+
				"</div>"+product_detail +"</div>";
				layer.closeAll();
				layer.open({
				      type: 1,
				      title: '商品详情',
				      shadeClose: true,
				      shade: false,
				      maxmin: true, //开启最大化最小化按钮
				      area: ['680px', '700px'],
				      resize: false,
				      content: html
				 });
			}
		});
});
	var carStr = $.cookie("carList");
	var proList = $(".proList");
 	function addProduct(p_price,p_id){
 		var price = p_price;
 		var productId = p_id;
 		var title = $('.d-name').html();
 		var imgSrc = $('.d-img').attr('src');
 		var car = $(".car");
 		var carImg = $(".fa-shopping-cart");
 		var num  = car.find(".num");
		num.html(parseInt(num.html())+1);
		var hasProduct = $("."+productId);
		if(hasProduct.length>0){
			var numProduct = parseInt(hasProduct.find(".car_num").html())+1;
			var product = carList.getProductById(productId);
			console.log(carList)
			product.product_num = numProduct;
			product.product_total =price*numProduct;
			carList[product.product_index] = product;
			hasProduct.find(".car_num").html(numProduct);
			hasProduct.find(".car_price").html(numProduct*price);
			//将js对象转换成json字符串
			var str = JSON.stringify(carList);
			$.cookie("carList",str,{expires:7});
			addItemToCar(product);
		}else{
			var product = {product_id:productId,product_name:title,product_index:carList.length,product_num:1,product_price:price,product_total:price,product_pic:imgSrc};
			carList[carList.length] = product;
			proList.append("<li class='"+productId+"'><img src='"+imgSrc+"'/><span class='car_num' >1</span>￥<span class='car_price'>"+price+"</span><a href='javascript:void(0)' class='remove' onclick='removeItem(\""+productId+"\")'>X</a></li>");
			var str = JSON.stringify(carList);
			addItemToCar(product);
			$.cookie("carList",str,{expires:7});
		}
 	}
/*购物车事件没写,等待通用方法*/