<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery-1.12.3.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<form action="product/createMobuyProduct.action" method="post">
产品标题：<input id="name" size="130" name="product.name" type="text" value="${product.name}"/><br/><br/>
产品描述：<textarea id="description" rows="6" cols="100" name="product.description">${product.description}</textarea><br/><br/>
产品标签：<textarea id="tags" rows="2" cols="100" name="product.tags">${product.tags}</textarea><br/><br/>
价格与运送
<hr>
MSRP($):<input id="msrp" name="product.msrp" type="text" value="${product.msrp}"/>
价格($):<input id="price" name="product.price" type="text" value="${product.price}"/>
运费($):<input id="shipping" name="product.shipping" type="text" value="${product.shipping}"/>
库存(数字):<input id="quantity" name="product.quantity" type="text" value="${product.quantity}"/>
<br/>
产品图片
<hr>
<input name="product.extra_image_urls" type="hidden" value="${product.extra_image_urls}">
<input name="product.main_image_url" type="hidden" value="${images[0]}">
<s:iterator value="images" var="image">  
   <img width="120" height="120" src="${image}">
</s:iterator>
<br/>
类目<input id="category" name="product.category" type="text"/>
<hr>
<br/>
<br/>
尺寸<input id="size" type="text"/><input type="button" value="刷新SKU"/>
<hr>
<br/>
颜色<input id="color" type="text"/><input type="button" value="刷新SKU"/>
<hr>
<br/>
子产品
<hr>
<input type="hidden" name="product.parent_id" id="parent_id">
<table>
<tr><th><a onclick="onAddTR()" href="javascript:">SKU(一键生成SKU)</a></th><th>尺寸</th><th>颜色</th><th>MSRP($)</th><th>价格($)</th><th>重量<th>库存</th></tr>
</table>
<div id="subProduct"></div>
<br/>
<br/>
<input type="submit" value="发布" width="140px">
</form>
<script type="text/javascript">
        function onAddTR()
        {
			$("#subProduct").empty();
			var colors = new Array();
			var sizes = new Array();
         	colors = $("#color").val().split(',');
         	sizes = $("#size").val().split(',');
        	
         	var insertStr = "<table>";
        	//系统时间戳
        	var timestamp = parseInt(new Date().getTime()/1000);    // 当前时间戳
         	var sku = Math.floor(Math.random()*100);
        	var sku2 = Math.floor(Math.random()*100);
        	var i = 0;
        	var parent_id = timestamp +"-"+ sku + "-"+ sku2;
			$("#parent_id").val(parent_id);
         	if(colors.length !=0 && sizes.length != 0){// 有颜色也有尺寸

        		for(var color in colors){
        			//alert("color->"+color);
        			for(var size in sizes){
        				i++;
        				//alert("size->"+size);
                		insertStr = insertStr + "<tr>"
                		+"<td><input type=\"text\" name=\"skus\" value=\""+ parent_id +"-"+ i + "\"></td>"
                		+"<td><input type=\"text\" name=\"sizes\" value=\""+ sizes[size] +"\"></td>"
                		+"<td><input type=\"text\" name=\"colors\" value=\""+ colors[color] +"\"></td>"
                		+"<td><input type=\"text\" name=\"market_price\" value=\""+ $("#msrp").val() +"\"></td>"
                		+"<td><input type=\"text\" name=\"sell_price\" value=\""+$("#price").val()+"\"></td>"
                		+"<td><input type=\"text\" name=\"weights\" value=\"0.3\"></td>"
                		+"<td><input type=\"text\" name=\"inventory\" value=\""+$("#quantity").val()+"\"></td></tr>";
        			}
        		}
        		insertStr = insertStr + "</table>";
        	}
        	$("#subProduct").append(insertStr);
        }
</script>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
</body>
</html>