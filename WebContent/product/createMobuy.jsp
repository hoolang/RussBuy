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
	<form id="submitForm" action="product/createMobuyProduct.action"
		method="post">
		产品标题：<input id="name" size="130" name="product.name" type="text"
			value="${product.name}" /><br />
		<br /> 产品描述：
		<textarea id="description" rows="6" cols="100"
			name="product.description">${product.description}</textarea>
		<br />
		<br /> 产品标签：
		<textarea id="tags" rows="2" cols="100" name="product.tags">${product.tags}</textarea>
		<input id="createTags" type="button" value="生成标签"> | <input
			id="translate" type="button" value="一键翻译"><br />
		<br /> 价格与运送
		<hr>
		MSRP($):<input id="msrp" name="product.msrp" type="text"
			value="${product.msrp/6*3}" /> 价格($):<input id="price"
			name="product.price" type="text" value="${product.price/6}" /> 运费($):<input
			id="shipping" name="product.shipping" type="text"
			value="${product.shipping}" /> 库存(数字):<input id="quantity"
			name="product.quantity" type="text" value="${product.quantity}" /> <br />
		产品图片
		<hr>
		<input name="product.extra_image_urls" type="hidden"
			value="${product.extra_image_urls}"> <input
			name="product.main_image_url" type="hidden" value="${images[0]}">
		<s:iterator value="images" var="image">
			<img width="120" height="120" src="${image}">
		</s:iterator>
		<br /> 类目<input id="category" name="product.category" type="text" />
		<hr>
		<br /> <br /> 尺寸<input id="size" type="text" /><input type="button"
			value="刷新SKU" />
		<hr>
		<br /> 颜色<input id="color" type="text" /><input type="button"
			value="刷新SKU" />
		<hr>
		<br /> 子产品
		<hr>
		<input type="hidden" name="product.parent_id" id="parent_id">
		<table>
			<tr>
				<th><a onclick="onAddTR()" href="javascript:">SKU(一键生成SKU)</a></th>
				<th>尺寸</th>
				<th>颜色</th>
				<th>MSRP($)</th>
				<th>价格($)</th>
				<th>重量
				<th>库存</th>
			</tr>
		</table>
		<div id="subProduct"></div>
		<br /> <br />
		<div id="result"></div>
		<input type="button" onclick="checkForSubmit()" value="发布"
			width="140px">
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#createTags").click(function() {
				var url = '../pjson/createTags.action';
				//获取表单值，并以json的数据形式保存到params中  
				var params = {
					name : $("#name").val(),
				}
				$.ajax({
					type : 'POST',
					url : url,
					data : params,
					success : function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据  
						$("#tags").val(data.tags);
					},
					dataType : 'json'
				});
			});
			
			//使用 Ajax 的方式 判断登录  
			$("#translate").click(function() {
				
				if($("#name") ==  null || $("#name").val().length == 0){
					alert("标题不能为空");
					return;
				}else if($("#description") ==  null || $("#description").val().length == 0){
					alert("描述不能为空");
					return;
				}else if($("#tags") ==  null || $("#name").val().length == 0){
					alert("标签不能为空");
					return;
				}
				
				var url = '../pjson/translate.action';
				//获取表单值，并以json的数据形式保存到params中  
				var params = {
					name : $("#name").val(),
					tags : $("#tags").val(),
					description : $("#description").val(),
				}
				$.ajax({
					type : 'POST',
					url : url,
					data : params,
					success : function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据  
						$("#name").val(data.name);
						$("#tags").val(data.tags);
						$("#description").val(data.description);
					},
					dataType : 'json'
				});

			});
		});
		function checkForSubmit() {
			if ($("#price").val().length == 0) {
				alert("请填写商品价格");
				return;
			} else if ($("#quantity").val().length == 0) {
				alert("请填写库存");
				return;
			} else if ($("#category").val().length == 0) {
				alert("类目不能为空");
				return;
			} else if ($("#size").val().length == 0) {
				alert("尺寸不能为空（暂时颜色和尺寸都要填写）");
				return;
			} else if ($("#color").val().length == 0) {
				alert("颜色不能为空（暂时颜色和尺寸都要填写）");
				return;
			}

			$("#submitForm").submit();

		}
		function onAddTR() {
			$("#subProduct").empty();
			var colors = new Array();
			var sizes = new Array();
			colors = $("#color").val().split(',');
			sizes = $("#size").val().split(',');

			var insertStr = "<table>";
			//系统时间戳
			var timestamp = parseInt(new Date().getTime() / 1000); // 当前时间戳
			var sku = Math.floor(Math.random() * 100);
			var sku2 = Math.floor(Math.random() * 100);
			var i = 0;
			var parent_id = timestamp + "-" + sku + sku2;
			$("#parent_id").val(parent_id);
			if (colors.length != 0 && sizes.length != 0) {// 有颜色也有尺寸

				for ( var color in colors) {
					//alert("color->"+color);
					for ( var size in sizes) {
						i++;
						//alert("size->"+size);
						insertStr = insertStr
								+ "<tr>"
								+ "<td><input type=\"text\" name=\"skus\" value=\""+ parent_id +""+ i +"-"+ sizes[size] +"-"+ colors[color]+"\"></td>"
								+ "<td><input type=\"text\" name=\"sizes\" value=\""+ sizes[size] +"\"></td>"
								+ "<td><input type=\"text\" name=\"colors\" value=\""+ colors[color] +"\"></td>"
								+ "<td><input type=\"text\" name=\"market_price\" value=\""
								+ $("#msrp").val()
								+ "\"></td>"
								+ "<td><input type=\"text\" name=\"sell_price\" value=\""
								+ $("#price").val()
								+ "\"></td>"
								+ "<td><input type=\"text\" name=\"weights\" value=\"0.3\"></td>"
								+ "<td><input type=\"text\" name=\"inventory\" value=\""
								+ $("#quantity").val() + "\"></td></tr>";
					}
				}
				insertStr = insertStr + "</table>";
			}
			$("#subProduct").append(insertStr);
		}
	</script>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>