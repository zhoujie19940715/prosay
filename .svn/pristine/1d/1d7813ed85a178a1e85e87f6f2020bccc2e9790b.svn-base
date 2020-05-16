var carList = new Array();
$(function(){
	$(".car .tool a").click(function(){
		$(".con").toggle("slow");
	});
	var carStr = $.cookie("carList");
	var proList = $(".proList");
	queryProductsFromCar();
	var flag = false;
	if(carList.length>0){
		flag = true;
	}
	if(carStr){
		
		var tmpList = JSON.parse(carStr);
		for(var i = 0 ;i<tmpList.length;i++){
			var product = tmpList[i];
			var dbProduct = carList.getProductById(product.product_id);
			if(dbProduct!=null){
				product.product_num += dbProduct.product_num;
				product.product_total = product.product_num*product.product_price;
				carList[carList.getIndexById(product.product_id)]=product;
			}else{//如果数据库中不存在则直接添加进入CarList
				carList[carList.length] = product;
			}
			addItemToCar(product);
			
			
		}
		
	}
	var num = 0;
	for(var i = 0 ;i<carList.length;i++){
		var product = carList[i];
		num+=product.product_num;
		proList.append("<li class='"+product.product_id+"'><img src='"+product.product_pic+"'/><span class='car_num' >"+product.product_num+"</span>￥<span class='car_price'>"+product.product_total+"</span><a href='javascript:void(0)' class='remove' onclick='removeItem(\""+product.product_id+"\")'>X</a></li>");
		if(flag){
		$.cookie("carList","",{expires:7});
		}
	}
	$(".car .tool .num").html(num);
	$(".s_btn").click(function(event){
		var _this = $(this);
		var price = _this.parent().find(".s_p").html();
		var title = _this.parent().find(".s_title").html();
		var productId=_this.parent().find(".id").val();
		var imgSrc = _this.parent().find("img").attr("src");
		var car = $(".car");
		var flyImg = $("<img src='"+imgSrc+"' style='width:50px;height:50px;border-radius:50%;border:1px solid red;z-index:99'/>");
		var carImg = $(".fa-shopping-cart");
		var num  = car.find(".num");
		flyImg.fly({
			start:{left:event.clientX,top:event.clientY},
			end:{left:carImg.offset().left,top:carImg[0].offsetTop},
			onEnd:function(){
				flyImg.remove();
				num.html(parseInt(num.html())+1);
				var hasProduct = $("."+productId);
				if(hasProduct.length>0){
					var numProduct = parseInt(hasProduct.find(".car_num").html())+1;
					
					var product = carList.getProductById(productId);
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
					console.log(imgSrc+":"+sysPath);
					var product = {product_id:productId,product_name:title,product_index:carList.length,product_num:1,product_price:price,product_total:price,product_pic:imgSrc.replace(sysPath,"")};
					carList[carList.length] = product;
					proList.append("<li class='"+productId+"'><img src='"+imgSrc+"'/><span class='car_num' >1</span>￥<span class='car_price'>"+price+"</span><a href='javascript:void(0)' class='remove' onclick='removeItem(\""+productId+"\")'>X</a></li>");
					var str = JSON.stringify(carList);
					addItemToCar(product);
					$.cookie("carList",str,{expires:7});
				}
			}
		});
		
	});
	
	$("#pay").click(function(){
		window.location.href=sysPath+"/order/goOrder.do";
	});
});

function queryProductsFromCar(){
	$.ajax({
		type:"post",
		url:sysPath+"/car/carList.do",
		dataType:"JSON",
		async:false,
		success:function(result){
			console.log(result);
			carList =result;
		}
		
	});
}
//移除商品
function removeItem(id){
	var carNum = $(".car .tool .num");
	carNum.html(parseInt(carNum.html())-parseInt($("."+id).find(".car_num").html()));
	$("."+id).remove();
	carList.removeProduct(id);
	delItemToCar(id);
}
//根据产品ID查找产品的对象
Array.prototype.getProductById=function(id){
	for(var i = 0;i<this.length;i++){
		if(this[i].product_id==id){
			return this[i];
		}
	}
	return null;
}
Array.prototype.getIndexById=function(id){
	for(var i = 0;i<this.length;i++){
		if(this[i].product_id==id){
			return i;
		}
	}
	return -1;
}
Array.prototype.removeProduct=function(id){
	var index = this.getIndexById(id);
	if(index > -1){
		this.splice(index,1);
	}
	
}
//添加商品元素到购物车保存到后台数据库
function addItemToCar(product){
	$.ajax({
		type:"post",
		url:sysPath+"/car/add.do",
		data:product,
		success:function(result){
			if(result=="true"){
				console.log("保存成功!");
			}else{
				console.log("保存失败！");
			}
		}
	});
}
//移除商品元素到购物车保存到后台数据库
function delItemToCar(productId){
	$.ajax({
		type:"post",
		url:sysPath+"/car/delete.do",
		data:{"product_id":productId},
		success:function(result){
			if(result=="true"){
				console.log("删除成功!");
			}else{
				console.log("删除失败！");
			}
		}
	});
}
