<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="100%" border="0" cellspacing="" cellpadding="0">
<th>ID</th><th>产品名称</th><th>价格</th><th>运费</th><th>分类</th><th>源网址</th>

<s:iterator value="productList" id="product" > 
	<tr style="">
		<td>
			${product.uid}
		</td>
		<td>
			${product.product_name}
		</td>
		<td>
			${product.price}
		</td>
		<td>
			${product.shipping}
		</td>
		<td>
			${product.category}
		</td>
		<td>
			${product.category}
		</td>
		<td>
			
			<a href="${product.other_platform_product_url}">访问</a>
		</td>
	</tr>
</s:iterator>
</table>

</body>
</html>